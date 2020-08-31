package com.pikaqiu.familybucket.dto;

import lombok.Data;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 23:14
 */
@Data
public class AuthUserDTO {

    /**
     * @apiDefine userName
     * @apiParam {String} userName 用户名称
     */
    private String userName;
    /**
     * @apiDefine phoneNumber
     * @apiParam {String} phoneNumber 用户手机号
     */
    private String phoneNumber;
    /**
     * @apiDefine password
     * @apiParam {String} password 用户密码
     */
    private String password;
    /**
     * @apiDefine passwordEnc
     * @apiParam {String} passwordEnc 用户密码加密值（预定义，暂不使用）
     */
    private String passwordEnc;

}
