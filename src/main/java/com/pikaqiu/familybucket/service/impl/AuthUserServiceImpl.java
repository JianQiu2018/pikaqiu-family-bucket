package com.pikaqiu.familybucket.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.constants.ErrorCode;
import com.pikaqiu.familybucket.dto.AuthUserDTO;
import com.pikaqiu.familybucket.entities.AuthUser;
import com.pikaqiu.familybucket.entities.UserInfo;
import com.pikaqiu.familybucket.enums.NoticeEnum;
import com.pikaqiu.familybucket.exception.UserException;
import com.pikaqiu.familybucket.feign.FeignNoticeService;
import com.pikaqiu.familybucket.idworker.IdGenerator;
import com.pikaqiu.familybucket.repository.AuthUserRepository;
import com.pikaqiu.familybucket.repository.UserInfoRepository;
import com.pikaqiu.familybucket.service.AuthUserService;
import com.pikaqiu.familybucket.service.component.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static com.pikaqiu.familybucket.constants.RedisKeysConstants.TOKEN;
import static com.pikaqiu.familybucket.constants.RedisKeysConstants.USERID_TOKEN;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 23:12
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    FeignNoticeService feignNoticeService;

    @Autowired
    CommonService commonService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<String> register(AuthUserDTO authUserDto){
        Optional<AuthUser> userOptional = authUserRepository.findByPhoneNumber(authUserDto.getPhoneNumber());
        if (userOptional.isPresent()) {
            return Mono.error(new UserException("该手机号已注册~", ErrorCode.USER_DOES_EXIST));
        }
        AuthUser authUser = new AuthUser();
        Long userId = idGenerator.nextId();
        authUser.setId(userId);
        authUserDto.setPassword(passwordEncoder.encode(authUserDto.getPassword()));
        BeanUtils.copyProperties(authUserDto,authUser);
        authUser.setTokenVersion(Constants.TYPE_ZERO);
        authUser.setCreateTime(LocalDateTime.now());
        //保存用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(idGenerator.nextId());
        userInfo.setUserId(userId);
        userInfo.setUserName(authUserDto.getUserName());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setLastModifyTime(LocalDateTime.now());
        userInfoRepository.save(userInfo);
        //调用远程发布消息
        feignNoticeService.publishNotice(commonService.publishNotice("欢迎"+userInfo.getUserName()+"加入PikaQiu大家庭~","用户注册成功", NoticeEnum.REGISTER.getModuleType(),Constants.TYPE_ONE,"",new Long[]{userInfo.getUserId()}));
        return Mono.just(authUserRepository.save(authUser).getId().toString());
    }

    @Override
    public Mono<String> login(AuthUserDTO authUserDto) {
        Optional<AuthUser> authUserOptional = authUserRepository.findByPhoneNumber(authUserDto.getPhoneNumber());
        if(authUserOptional.isPresent()){
            Boolean isTure = passwordEncoder.matches(authUserDto.getPassword(), authUserOptional.get().getPassword());
            if(isTure){
                AuthUser authUser = authUserOptional.get();
                authUser.setTokenVersion(authUser.getTokenVersion() + Constants.TYPE_ONE);
                authUser.setLastLoginTime(LocalDateTime.now());
                authUserRepository.save(authUser);
                UserInfo userInfo = userInfoRepository.findByUserId(authUser.getId());
                //调用远程发布消息
                feignNoticeService.publishNotice(commonService.publishNotice(userInfo.getUserName()+"上线啦~","用户登录成功",NoticeEnum.LOGIN.getModuleType(), Constants.TYPE_ONE,"",new Long[]{userInfo.getUserId()}));
                return Mono.just(getToken(authUserOptional.get()));
            }
        }else{
            return Mono.error(new UserException("该手机号未注册~", ErrorCode.USER_DOES_NOT_EXIST));
        }
        return Mono.error(new UserException("密码不匹配,请重新输入~", ErrorCode.PASSWORD_DOES_NOT_MATCH));
    }


    public String getToken(AuthUser user) {
        String token = JWT.create()
                .withAudience(user.getId().toString())
                //签名的主题
                .withSubject("PikaQiu")
                //生成签名的时间
                .withIssuedAt(new Date())
                //五分钟失效 5 * 60 * 1000
                //.withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60 * 1000)))
                //签名 Signature
                .sign(Algorithm.HMAC256(user.getPassword()));
        //Duration.ofSeconds(30) : 30s ,Duration.ofMinutes(30) : 30min
        stringRedisTemplate.opsForValue().set(TOKEN + token, token, Duration.ofMinutes(5));
        stringRedisTemplate.opsForValue().set(USERID_TOKEN + token, user.getId().toString(), Duration.ofMinutes(5));
        return token;
    }

}
