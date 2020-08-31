package com.pikaqiu.familybucket.exception.handle;

import com.pikaqiu.familybucket.constants.ErrorCode;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

/**
 * Description: 异常捕获类
 *
 * @author PikaQiu
 * @date 2019/8/21 20:53
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = UserException.class)
    public HttpResponse handleUserException(UserException e) {
        return new HttpResponse().setErrorMessage(e.getErrorCode(),e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public HttpResponse handleMethodValidException(MethodArgumentNotValidException e) {
        return new HttpResponse().setErrorMessage( ErrorCode.PARAMS_VALID_ERROR ,e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = ValidationException.class)
    public HttpResponse handleBeanFieldValidException(ValidationException e) {
        return new HttpResponse().setErrorMessage(ErrorCode.PARAMS_VALID_ERROR ,e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public HttpResponse handleUnknowException(Exception e) {
        log.error("统一异常ERROR: [{}]", e.getMessage());
        return new HttpResponse().setErrorMessage(-100, "统一异常ERROR[" + e.getMessage() + "]");
    }

}