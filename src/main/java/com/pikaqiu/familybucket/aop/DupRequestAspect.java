package com.pikaqiu.familybucket.aop;

import com.alibaba.fastjson.JSON;
import com.pikaqiu.familybucket.annotation.DupRequestAnnotation;
import com.pikaqiu.familybucket.utils.DedupReqHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.time.Duration;


@Aspect
@Component
public class DupRequestAspect {

    private static final Logger logger = LoggerFactory.getLogger(DupRequestAspect.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @annotation 拦截重复请求
     */
    @Pointcut("@annotation(com.pikaqiu.familybucket.annotation.DupRequestAnnotation)")
    public void dupRequest() {
    }

    @Before(value = "dupRequest() && @annotation(dupRequestAnnotation))")
    public void before(JoinPoint joinPoint, DupRequestAnnotation dupRequestAnnotation) throws Throwable {
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String inParam = JSON.parseArray(JSON.toJSONString(joinPoint.getArgs())).getJSONObject(0).toJSONString();
        String userId= "12345678";//用户从各自系统业务逻辑处获取
        String dedupMD5 = new DedupReqHelper().dedupParamMD5(inParam, dupRequestAnnotation.excludeKeys());//计算请求参数摘要，其中剔除里面（一些干扰字段影响，例如：时间戳等等）干扰
        String dupKey = "user=" + userId + "&method=" + method + "&params=" + dedupMD5;
        dupKey = "dup:" + new DedupReqHelper().jdkMD5(dupKey);
        long expireTime =  dupRequestAnnotation.times();// 1000毫秒过期，1000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;
        // 底层API，保证SETNX+过期时间是原子操作
        /*Boolean firstSet = redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(dupKey.getBytes(), val.getBytes(), Expiration.milliseconds(expireTime),
                RedisStringCommands.SetOption.SET_IF_ABSENT));*/
        Boolean isDup = redisTemplate.opsForValue().setIfAbsent(dupKey, val, Duration.ofMillis(expireTime));
        if (isDup != null && !isDup) {
            throw new Exception("Don't duplicate request ^_^");
        }
        logger.debug("请求进入方法~~~");
    }

}
