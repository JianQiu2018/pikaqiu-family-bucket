package com.pikaqiu.familybucket.exception;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/7 23:36
 */
public class UserException extends RuntimeException{

    private Integer errorCode;

    public UserException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}