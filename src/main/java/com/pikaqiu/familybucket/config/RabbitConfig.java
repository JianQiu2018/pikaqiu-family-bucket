package com.pikaqiu.familybucket.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.inject.Qualifier;
import java.util.HashMap;
import java.util.Map;


@Component
public class RabbitConfig {

    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";
    public static final String ROUTINGKEY_EMAIL="inform.#.email.#";

    /**
     * 定义消息转换实例 ，转化成 JSON传输
     *
     * @return Jackson2JsonMessageConverter
     */
    @Bean
    public MessageConverter integrationEventMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 配置启用rabbitmq事务
     *
     * @param connectionFactory connectionFactory
     * @return RabbitTransactionManager
     */
    @Bean
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }


    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    //声明QUEUE_INFORM_EMAIL队列，配置死信队列需要的参数
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL(){
        /*Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","dev");
        return new Queue(QUEUE_INFORM_EMAIL,true,false,false,map);*/
        return QueueBuilder.durable(QUEUE_INFORM_EMAIL).withArgument("x-dead-letter-exchange", DEAD_EXCHANGE)
                //毫秒
//                .withArgument("x-message-ttl", 10000)
                //设置死信routingKey
                .withArgument("x-dead-letter-routing-key", "dev").build();
    }

    //ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(){
        return BindingBuilder.bind(new Queue(QUEUE_INFORM_EMAIL)).to(new DirectExchange(EXCHANGE_TOPICS_INFORM, true, true)).with(ROUTINGKEY_EMAIL);
    }

    //以下为死信队列
    private static final String DEAD_EXCHANGE = "x-dead-letter-exchange";

    @Bean(DEAD_EXCHANGE)
    public Exchange dead_exchange(){
        return ExchangeBuilder.directExchange(DEAD_EXCHANGE).durable(true).build();
    }

    @Bean("dead_queue")
    public Queue dead_routing_key(){
        return QueueBuilder.durable("dead_queue").build();
    }

    @Bean("dead_bind")
    public Binding dead_bind(){
        return BindingBuilder.bind(new Queue("dead_queue")).to(dead_exchange()).with("dev").noargs();
    }


}
