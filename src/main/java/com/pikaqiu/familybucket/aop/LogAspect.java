package com.pikaqiu.familybucket.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @Author qiujian
 * @Date 2020/6/4
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.pikaqiu.familybucket..*.*(..))")
    public void doPublicPointcut(){

    }

    @Pointcut("execution(* com.pikaqiu.familybucket.api.AuthUserController.login(..))")
    public void doLoginPointcut(){

    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("doLoginPointcut()")
    public void doAfterReturning() {
        System.out.println("返回通知：登录成功****！");
    }


    /**
     * @description  在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("doPublicPointcut()")
    public void doAfterThrowing(){
//        System.out.println("异常通知：*****！");
    }


}
