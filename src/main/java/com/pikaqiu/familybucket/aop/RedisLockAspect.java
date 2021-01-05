package com.pikaqiu.familybucket.aop;

import com.pikaqiu.familybucket.annotation.RedisLockAnnotation;
import com.pikaqiu.familybucket.enums.RedisLockTypeEnum;
import com.pikaqiu.familybucket.redis.entity.RedisLockDefinitionHolder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @Author qiujian
 * @Date 2021/1/4
 */
@Aspect
@Component
public class RedisLockAspect {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockAspect.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存储目前有效的key定义
     */
    private static ConcurrentLinkedQueue<RedisLockDefinitionHolder> holderList = new ConcurrentLinkedQueue();

    /**
     * 线程池，维护keyAliveTime
     */
    private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("redisLock-schedule-pool").daemon(true).build());

    {
        // 两秒执行一次「续时」操作（构造代码块）
        SCHEDULER.scheduleAtFixedRate(() -> {
            // 这里记得加 try-catch，否者报错后定时任务将不会再执行=-=
            Iterator<RedisLockDefinitionHolder> iterator = holderList.iterator();
            while (iterator.hasNext()) {
                RedisLockDefinitionHolder holder = iterator.next();
                // 判空
                if (holder == null) {
                    iterator.remove();
                    continue;
                }

                // 判断 key 是否还有效，无效的话进行移除
                if (redisTemplate.opsForValue().get(holder.getBusinessKey()) == null) {
                    iterator.remove();
                    continue;
                }

                // 超时重试次数，超过时给线程设定中断
                if (holder.getCurrentCount() > holder.getTryCount()) {
//                    holder.getCurrentTread().interrupt();
                    logger.info("当前线程===》{}", Thread.currentThread().getName()+ Thread.currentThread().getId());
                    iterator.remove();
                    continue;
                }
                // 判断是否进入最后三分之一时间
                long curTime = System.currentTimeMillis();
                boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
                if (shouldExtend) {
                    holder.setLastModifyTime(curTime);
                    redisTemplate.expire(holder.getBusinessKey(), holder.getLockTime(), TimeUnit.SECONDS);
                    logger.info("businessKey : [" + holder.getBusinessKey() + "], try count : " + holder.getCurrentCount());
                    holder.setCurrentCount(holder.getCurrentCount() + 1);
                }
            }
        }, 0, 2, TimeUnit.SECONDS);

    }

    /**
     * @annotation 中的路径表示拦截特定注解
     */
    @Pointcut("@annotation(com.pikaqiu.familybucket.annotation.RedisLockAnnotation)")
    public void redisLock() {
    }

    @Around(value = "redisLock()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 解析参数
        Method method = resolveMethod(pjp);
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        RedisLockTypeEnum typeEnum = annotation.typeEnum();
        Object[] params = pjp.getArgs();
        String ukString = params[annotation.lockFiled()].toString();
        // 省略很多参数校验和判空
        String businessKey = typeEnum.getUniqueKey(ukString);
        String uniqueValue = UUID.randomUUID().toString();
        // 加锁
        Object result = null;
        try {
            boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(businessKey, uniqueValue, Duration.ofSeconds(annotation.lockTime()));
            if (!isSuccess) {
                throw new Exception("You can't do it，because another has get the lock =-=");
            }
//            redisTemplate.expire(businessKey, annotation.lockTime(), TimeUnit.SECONDS);
            Thread currentThread = Thread.currentThread();
            // 将本次 Task 信息加入「延时」队列中
            holderList.add(new RedisLockDefinitionHolder(businessKey, annotation.lockTime(), System.currentTimeMillis(),
                    currentThread, annotation.tryCount()));
            // 执行业务操作
            result = pjp.proceed();
            // 线程被中断，抛出异常，中断此次请求
            /*if (currentThread.isInterrupted()) {
                throw new InterruptedException("You had been interrupted =-=");
            }*/
        } catch (InterruptedException e ) {
            logger.error("Interrupt exception, rollback transaction", e);
            throw new Exception("Interrupt exception, please send request again");
        } catch (Exception e) {
            logger.error("has some error, please check again", e);
        } finally {
            // 请求结束后，强制删掉 key，释放锁
            redisTemplate.delete(businessKey);
            logger.info("release the lock, businessKey is [" + businessKey + "]");
        }
        return result;
    }

    private Method resolveMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class<?> targetClass = pjp.getTarget().getClass();

        Method method = getDeclaredMethodFor(
                targetClass,
                signature.getName(),
                signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("Cannot resolve target method: " + signature.getMethod().getName());
        }
        return method;
    }

    /**
     * 获取指定类上的指定方法
     *
     * @param clazz          指定类
     * @param name           指定方法
     * @param parameterTypes 参数类型列表
     * @return 找到就返回method，否则返回null
     */
    public static Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getDeclaredMethodFor(superClass, name, parameterTypes);
            }
        }
        return null;
    }


}
