package com.pikaqiu.familybucket.redis.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pikaqiu.familybucket.constants.RedisKeysConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: 延迟队列
 *
 * @author PikaQiu
 * @date 2020/8/25 11:32
 */
@Component
@Slf4j
public class DelayingQueueService {

    private static ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 插入消息
     *
     * @param message
     * @return
     */
    @SneakyThrows
    public Boolean push(Message message) {
        Boolean addFlag = redisTemplate.opsForZSet().add(RedisKeysConstants.QUEUE_NAME, mapper.writeValueAsString(message), message.getDelayTime());
        return addFlag;
    }

    /**
     * 移除消息
     *
     * @param message
     * @return
     */
    @SneakyThrows
    public Boolean remove(Message message) {
        Long remove = redisTemplate.opsForZSet().remove(RedisKeysConstants.QUEUE_NAME, mapper.writeValueAsString(message));
        return remove > 0 ? true : false;
    }


    /**
     * 拉取最新需要
     * 被消费的消息
     * rangeByScore 根据score范围获取 0-当前时间戳可以拉取当前时间及以前的需要被消费的消息
     *
     * @return
     */
    public List<Message> pull() {
        Set<String> strings = redisTemplate.opsForZSet().rangeByScore(RedisKeysConstants.QUEUE_NAME, 0, System.currentTimeMillis());
        if (strings == null) {
            return null;
        }
        List<Message> msgList = strings.stream().map(msg -> {
            Message message = null;
            try {
                message = mapper.readValue(msg, Message.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return message;
        }).collect(Collectors.toList());
        return msgList;
    }


}
