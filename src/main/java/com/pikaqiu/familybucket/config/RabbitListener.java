package com.pikaqiu.familybucket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitListener.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void init(){
        /**
         * 消息发送到交换器Exchange后触发回调。
         * 使用该功能需要开启确认，spring-boot中配置如下：
         * spring.rabbitmq.publisher-confirms = true
         * correlationData：对象内部只有一个 id 属性，用来表示当前消息的唯一性
         * ack：消息投递到broker 的状态，true表示成功
         * cause：表示投递失败的原因
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    System.out.println("消息已确认 cause:{"+cause+"} - {"+correlationData+"}");
                } else {
                    System.out.println("消息未确认 cause:{"+cause+"} - {"+correlationData+"}");
                }
            }
        });
        /**
         * 通过实现ReturnCallback接口，
         * 如果消息从交换器发送到对应队列失败时触发
         * 比如根据发送消息时指定的routingKey找不到队列时会触发
         * 使用该功能需要开启确认，spring-boot中配置如下：
         * spring.rabbitmq.publisher-returns = true
         */
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                LOGGER.error("消息被退回:{}", message);
                LOGGER.error("消息使用的交换机:{}", exchange);
                LOGGER.error("消息使用的路由键:{}", routingKey);
                LOGGER.error("描述:{}", replyText);
            }
        });
    }

}
