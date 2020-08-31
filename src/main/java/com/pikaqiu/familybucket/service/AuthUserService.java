package com.pikaqiu.familybucket.service;

import com.pikaqiu.familybucket.dto.AuthUserDTO;
import reactor.core.publisher.Mono;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 23:12
 */
public interface AuthUserService {

    Mono<String> register(AuthUserDTO authUserDto);

    Mono<String> login(AuthUserDTO authUserDto);

}
