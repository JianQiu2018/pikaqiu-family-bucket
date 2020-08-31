package com.pikaqiu.familybucket.redis.bitmap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Description: 位图操作类
 *
 * @author PikaQiu
 * @date 2020/8/25 11:32
 */
@Component
@Slf4j
@AllArgsConstructor
public class BitMapService {

    private final StringRedisTemplate redisTemplate;


    /**
     * 保存
     *
     * @param key   位图key
     * @param index 下标
     * @param bit   0 false 1 true
     * @return
     */
    public Boolean setBit(String key, Long index, Boolean bit) {
        return redisTemplate.execute((RedisCallback<Boolean>) con -> con.setBit(key.getBytes(), index, bit));
    }

    /**
     * 获取值
     *
     * @param key   位图key
     * @param index 下标
     * @return
     */
    public Boolean getBit(String key, Long index) {
        return redisTemplate.execute((RedisCallback<Boolean>) con -> con.getBit(key.getBytes(), index));
    }


    /**
     * 用来查找指定范围内出现的第一个 0 或 1
     * @param key
     * @return
     */
    public Long bitPos(String key,Boolean bit){
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitPos(key.getBytes(), bit));
    }

    /**
     * 统计次数 只统计为为1的个数
     *
     * @param key 位图key
     * @return
     */
    public Long bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }


    /**
     * 统计下标范围内次数 只统计为为1的个数
     *
     * @param key 位图key
     * @return
     */
    public Long bitCount(String key, Long start, Long end) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }

}
