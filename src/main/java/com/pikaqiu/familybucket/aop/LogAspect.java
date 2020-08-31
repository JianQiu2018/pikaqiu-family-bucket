package com.pikaqiu.familybucket.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * <p>版权所有：</p >
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company 杭州孚立计算机软件有限公司
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
