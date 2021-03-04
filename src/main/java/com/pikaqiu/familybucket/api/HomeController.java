package com.pikaqiu.familybucket.api;

//import com.pikaqiu.starter.autoconfiguration.CommonPropertyService;
import com.pikaqiu.familybucket.annotation.DupRequestAnnotation;
import com.pikaqiu.familybucket.annotation.RedisLockAnnotation;
import com.pikaqiu.familybucket.dto.CommentDTO;
import com.pikaqiu.familybucket.entities.AuthUser;
import com.pikaqiu.familybucket.enums.RedisLockTypeEnum;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;


@RestController
@Slf4j
public class HomeController {

//    @Autowired
//    private CommonPropertyService commonPropertyService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${qiujian.test:123}")
    private String testValue;

    @GetMapping(value = "/api/home/getUserInfo")
    public AuthUser getUserInfo() {
//        System.out.println(commonPropertyService.getOutInfo());
        String str = "pikaqiu";
        log.info("Request getUserInfo:{}", str);
        log.info("Request testValue:{}", testValue);
        return new AuthUser();
    }

    @DupRequestAnnotation(times = 3000, excludeKeys = {"moduleType", "resourceId"})
    @PostMapping(value = "/api/home/dupRequest")
    public String getUserInfos(@RequestBody CommentDTO commentDTO) {
        String str = "pikaqius";
        log.info("Request getUserInfo:{}", str);
        return str;
    }

    @DupRequestAnnotation(times = 3000)
    @GetMapping(value = "/api/home/dupRequest2")
    public String getUserInfos2(CommentDTO commentDTO) {
        String str = "pikaqius";
        log.info("Request getUserInfo:{}", str);
        return str;
    }

    @GetMapping(value = "/api/home/mq")
    public void testMq(CommentDTO commentDTO) {
        for (int i = 0; i < 3; i++) {
            System.out.println(this.send(i+500));
        }
    }

    @RequestMapping("/send")
    public String send(Integer messge){
        //第一个参数：交换机名字  第二个参数：Routing Key的值  第三个参数：传递的消息对象
//        rabbitTemplate.convertAndSend("test.direct","test",messge, new CorrelationData(UUID.randomUUID().toString()));
        rabbitTemplate.convertAndSend("exchange_topics_inform","inform.#.email.#",messge, new CorrelationData(UUID.randomUUID().toString()));
        return "发送消息成功";
    }

    @GetMapping("/api/home/testRedisLock")
    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public String testRedisLock(@RequestParam("userId") Long userId) {
        /*try {
            log.info("睡眠执行前");
            Thread.sleep(4000);
            log.info("睡眠执行后");
        } catch (Exception e) {
            // log error
            log.info("has some error", e);
        }*/
        return "2344";
    }


    @RabbitListener(queues = "queue_inform_email")
    private void receiveMessage(String content, @Headers Channel channel, Message message) throws Exception {
        System.out.println("接收到---》"+ content);
        try {
            //处理消息
//             确认消息已经消费成功
            if(Integer.valueOf(content)==102){
                throw new Exception("i=102 error~~~");
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            System.out.println("消费处理异常:"+e+"");
//             拒绝当前消息，并把消息返回原队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            throw new Exception(e.getMessage());
        }
    }

    @RabbitListener(queues = "dead_queue")
    private void receiveMessage2(String content, @Headers Channel channel, Message message) throws IOException {
        System.out.println("死信队列接收到---》"+ content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
