package com.pikaqiu.familybucket.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DupRequestAnnotation {

    /**
     * 重复时间（在这个时间内的请求需要）
     */
    int times() default 1000;
    /**
     * 需排除掉字段
     * @return
     */
    String[] excludeKeys() default {};

}
