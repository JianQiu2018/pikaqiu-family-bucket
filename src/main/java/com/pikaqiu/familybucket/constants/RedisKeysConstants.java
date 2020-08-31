package com.pikaqiu.familybucket.constants;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/9/7 18:10
 */
public class RedisKeysConstants {

    public static final String TOKEN = "token:";

    public static final String USERID_TOKEN = "userId:";

    public static final String STAR_USER = "star_user:";

    public static final String STAR_USER_COUNT = "star_user_count:";

    /**
     * 可以不同业务用不同的key
     */
    public static final String QUEUE_NAME = "message:messagequeue";

    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222:333333
     * @param likedUserId 被点赞的人id
     * @param likedPostId 点赞的人的id
     * @return
     */
    public static String getStarKey(String likedUserId, String likedPostId){
        StringBuilder builder = new StringBuilder();
        builder.append(likedUserId);
        builder.append(":");
        builder.append(likedPostId);
        return builder.toString();
    }
}
