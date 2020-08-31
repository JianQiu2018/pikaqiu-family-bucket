package com.pikaqiu.familybucket.service.impl;

import com.pikaqiu.familybucket.dto.StarDTO;
import com.pikaqiu.familybucket.service.StarRedisService;
import com.pikaqiu.familybucket.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * 功能描述：
 * @Author qiujian
 * @Date 2020/6/9
 */
@Service
public class StarServiceImpl implements StarService {

    @Autowired
    private StarRedisService starRedisService;

    @Override
    public Mono<Long> brightStar(StarDTO starDTO) {
        Boolean isTrue = starRedisService.isMemberStar(starDTO.getResourceId(), starDTO.getUserId());
        if(!isTrue){
            //可使用定时器同步数据到数据库或者增加mq异步存储
            starRedisService.saveStarRedis(starDTO.getResourceId(), starDTO.getUserId());
        }else {
            starRedisService.unStarRedis(starDTO.getResourceId(), starDTO.getUserId());
        }
        return Mono.just(starRedisService.isSumStar(starDTO.getResourceId()));
    }

}
