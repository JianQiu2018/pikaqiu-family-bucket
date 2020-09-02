package com.pikaqiu.familybucket.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.constants.ErrorCode;
import com.pikaqiu.familybucket.entities.AuthUser;
import com.pikaqiu.familybucket.exception.UserException;
import com.pikaqiu.familybucket.repository.AuthUserRepository;
import com.pikaqiu.familybucket.annotation.PassToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.pikaqiu.familybucket.constants.RedisKeysConstants.TOKEN;
import static com.pikaqiu.familybucket.constants.RedisKeysConstants.USERID_TOKEN;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/21 22:12
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 执行认证
        /*if (token == null) {
            throw new UserException("无token,请重新登录", ErrorCode.NO_TOKEN);
        }*/
        Assert.notNull(token, "无token,请重新登录");
        // 获取token中的 userId
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new UserException("JWT->token校验失败", ErrorCode.TOKEN_ERROR);
        }
        //todo: Redis相关代码需抽取到网关项目中做校验
        String redisToken = stringRedisTemplate.opsForValue().get(TOKEN + token);
        log.debug("redis_token : {}", redisToken);
        if (!token.equals(redisToken)) {
            throw new UserException("token校验失败", ErrorCode.TOKEN_ERROR);
        }
        Optional<AuthUser> userOptional = authUserRepository.findById(Long.valueOf(userId));
        if (!userOptional.isPresent()) {
            throw new UserException("用户不存在, 请重新登录", ErrorCode.USER_DOES_EXIST);
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userOptional.get().getPassword())).build();
        try {
            jwtVerifier.verify(token);
            //token 小于指定时间时则为key重置时间
            if(stringRedisTemplate.getExpire(TOKEN + token, TimeUnit.MINUTES) < Duration.ofMinutes(Constants.TYPE_FIVE).toMinutes()){
                stringRedisTemplate.opsForValue().set(TOKEN + token, token, Duration.ofMinutes(30));
                stringRedisTemplate.opsForValue().set(USERID_TOKEN + token, userId, Duration.ofMinutes(30));
            }
        } catch (JWTVerificationException e) {
            throw new UserException("JWT校验token失败", ErrorCode.TOKEN_ERROR);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
