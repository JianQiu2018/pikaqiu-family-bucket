package com.pikaqiu.familybucket.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Description: queryDsl配置
 *
 * @author PikaQiu
 * @date 2019/8/31 18:40
 */
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.pikaqiu.familybucket.repository"/*,transactionManagerRef = "jtaTransactionManager"*/)
@EnableElasticsearchRepositories(basePackages = "com.pikaqiu.familybucket.elasticsearch.repository")
@Configuration
public class DefaultCoreConfiguration {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory getQueryFactory(){
        return new JPAQueryFactory(this.em);
    }

    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }

}
