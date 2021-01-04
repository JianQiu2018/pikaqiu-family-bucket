package com.pikaqiu.familybucket;

import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudSecondaryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * 按请求粒度负载均衡(使用MongoDB存储事务日志):需引入SpringCloudConfiguration;
 * 按事务粒度负载均衡(使用文件系统存储事务日志):需引入SpringCloudSecondaryConfiguration;
 */
//@Import({/*DynamicDataSourceRegister.class, */SpringCloudSecondaryConfiguration.class}) //springboot版本太高有兼容性问题
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})// 使用文件存储时, 不需要配置mongodb,编译红线提示不影响运行
public class FamilyBucketApplication {



    public static void main(String[] args) {
        SpringApplication.run(FamilyBucketApplication.class, args);
    }



}
