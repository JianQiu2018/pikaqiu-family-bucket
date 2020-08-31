package com.pikaqiu.familybucket.redis.hyperloglog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Description: 布隆过滤器
 *
 * @author PikaQiu
 * @date 2020/8/25 11:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HyperLogLogService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 添加值
     * pfadd 指令
     *
     * @param key
     * @param values
     */
    public void add(String key, String... values) {
        redisTemplate.opsForHyperLogLog().add(key, values);
    }


    /**
     * 统计数量
     * pfcount 指令
     *
     * @param key
     */
    public Long count(String key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }


    /**
     * 合并 用于将多个 pf 计数值累加在一起形成一个新的 pf 值
     * pfmerge
     *
     * @param keys
     */
    public void merge(String key1, String... keys) {
        redisTemplate.opsForHyperLogLog().union(key1, keys);
    }


}
