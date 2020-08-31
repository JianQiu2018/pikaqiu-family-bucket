package com.pikaqiu.familybucket.service;

import com.pikaqiu.familybucket.dto.StarDTO;
import reactor.core.publisher.Mono;

/**
 * 功能描述：
 * @Author qiujian
 * @Date 2020/6/9
 */
public interface StarService {

    /**
     * 点赞or取消赞
     * @param starDTO
     * @return
     */
    Mono<Long> brightStar(StarDTO starDTO);


}
