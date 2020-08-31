package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.AuthUserDTO;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 23:08
 */
@Slf4j
@RestController
public class AuthUserController {

    @Autowired
    private AuthUserService userService;

    /**
     * @api {POST} /api/admin/authUser/register 用户注册
     * @apiGroup User
     * @apiUse GlobalErrorCode
     * @apiUse userName
     * @apiUse phoneNumber
     * @apiUse password
     * @apiUse passwordEnc
     */
    @PostMapping(value = Constants.URL_API_ADMIN_PREFIX + "/authUser/register")
    public Mono<HttpResponse<String>> register(@RequestBody AuthUserDTO authUserDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/authUser/register [POST].");
        }
        return userService.register(authUserDto).map(data -> new HttpResponse<String>().setData(data));
    }

    /**
     * @api {POST} /api/admin/authUser/login 用户登录
     * @apiGroup User
     * @apiUse GlobalErrorCode
     * @apiUse phoneNumber
     * @apiUse password
     */
    @PostMapping(value = Constants.URL_API_ADMIN_PREFIX + "/authUser/login")
    public Mono<HttpResponse<String>> login(@RequestBody AuthUserDTO authUserDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/authUser/login [POST].");
        }
        return userService.login(authUserDto).map(data -> new HttpResponse<String>().setData(data));
    }

}
