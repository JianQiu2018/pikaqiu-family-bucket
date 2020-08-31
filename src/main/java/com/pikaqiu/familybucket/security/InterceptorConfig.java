package com.pikaqiu.familybucket.security;

import com.pikaqiu.familybucket.filter.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/21 22:29
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Value("${authorization.interceptor.ignored-url:/api/**}")
    private String[] interceptorIgnoredURL;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(authenticationInterceptor())
                .excludePathPatterns(interceptorIgnoredURL)
                .addPathPatterns("/**");
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

}
