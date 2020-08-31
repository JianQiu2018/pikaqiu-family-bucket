package com.pikaqiu.familybucket.dynamicsource;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceExchange {

    String value() default "master";

}
