package com.pikaqiu.familybucket.redis.messagequeue;

import com.pikaqiu.familybucket.idworker.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * Description: 消息提供者
 *
 * @author PikaQiu
 * @date 2020/8/25 11:32
 */
@Component
@Slf4j
public class MessageProvider {

    @Autowired
    private DelayingQueueService delayingQueueService;

    @Autowired
    private IdGenerator idGenerator;

    /**
     * 发送消息
     *
     * @param messageContent
     */
    public void sendMessage(String messageContent, String channel, long delay) {
        try {
            if (messageContent != null) {
                Message message = new Message();
                //时间戳默认为毫秒 延迟5s即为 5*1000
                long time = System.currentTimeMillis();
                log.info("当前时间戳：[{}], 入缓存消息内容：[{}]", time, messageContent);
                LocalDateTime dateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
                message.setDelayTime(time + (delay * 1000));
                message.setCreateTime(dateTime);
                message.setBody(messageContent);
                message.setChannel(channel);
                message.setId(idGenerator.nextId());
                delayingQueueService.push(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
