package com.pikaqiu.familybucket.service.impl;

import com.pikaqiu.familybucket.constants.RedisKeysConstants;
import com.pikaqiu.familybucket.service.StarRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * 功能描述：
 * @Author qiujian
 * @Date 2020/6/9
 */
@Service
public class StarRedisServiceImpl implements StarRedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void incrementStarCount(String likedUserId) {
        redisTemplate.opsForHash().increment(RedisKeysConstants.STAR_USER_COUNT, likedUserId, 1);
    }

    @Override
    public void decrementStarCount(String likedUserId) {
        redisTemplate.opsForHash().increment(RedisKeysConstants.STAR_USER_COUNT, likedUserId, -1);
    }

    @Override
    public void saveStarRedis(Long resourceId , Long userId){
        redisTemplate.opsForSet().add(RedisKeysConstants.STAR_USER + resourceId, userId);
    }

    @Override
    public void unStarRedis(Long resourceId , Long userId) {
        redisTemplate.opsForSet().remove(RedisKeysConstants.STAR_USER + resourceId, userId);
    }

    @Override
    public Boolean isMemberStar(Long resourceId, Long userId) {
        return redisTemplate.opsForSet().isMember(RedisKeysConstants.STAR_USER + resourceId, userId);
    }

    @Override
    public Long isSumStar(Long resourceId) {
        return redisTemplate.opsForSet().size(RedisKeysConstants.STAR_USER + resourceId);
    }


}
