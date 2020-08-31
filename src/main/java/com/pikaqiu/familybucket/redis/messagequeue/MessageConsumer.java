package com.pikaqiu.familybucket.redis.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: 延迟队列消费
 *
 * @author PikaQiu
 * @date 2020/8/25 11:32
 */
@Component
@Slf4j
public class MessageConsumer {

    private static ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

    @Autowired
    private DelayingQueueService delayingQueueService;

    /**
     * 定时消费队列中的数据
     * zset会对score进行排序 让最早消费的数据位于最前
     * 拿最前的数据跟当前时间比较 时间到了则消费
     */
    @Scheduled(cron = "*/100 * * * * *")
    public void consumer() throws JsonProcessingException {
        log.info("定时器启动****");
        List<Message> msgList = delayingQueueService.pull();
        if (null != msgList) {
            long current = System.currentTimeMillis();
            msgList.stream().forEach(msg -> {
                // 已超时的消息拿出来消费
                if (current >= msg.getDelayTime()) {
                    try {
                        log.info("消费消息：{}:消息创建时间：{},消费时间：{}", mapper.writeValueAsString(msg), msg.getCreateTime(), LocalDateTime.now());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    //移除消息
                    delayingQueueService.remove(msg);
                }
            });
        }
    }

}
