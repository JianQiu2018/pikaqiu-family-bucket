package com.pikaqiu.familybucket.service;


/**
 * 功能描述：
 * @Author qiujian
 * @Date 2020/6/9
 */
public interface StarRedisService {

    /**
     * 该用户的点赞数加1
     * @param likedUserId
     */
    void incrementStarCount(String likedUserId);

    /**
     * 该用户的点赞数减1
     * @param likedUserId
     */
    void decrementStarCount(String likedUserId);

    /**
     * 点赞
     * @param resourceId
     * @param userId
     */
    void saveStarRedis(Long resourceId , Long userId);

    /**
     * 取消点赞
     * @param userId
     */
    void unStarRedis(Long resourceId , Long userId);

    /**
     * 判断是否点赞
     * @param userId
     */
    Boolean isMemberStar(Long resourceId , Long userId);

    /**
     * 获取当前用户的点赞总数
     * @param resourceId
     */
    Long isSumStar(Long resourceId);



}
