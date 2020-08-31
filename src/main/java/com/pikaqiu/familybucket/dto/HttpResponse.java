package com.pikaqiu.familybucket.dto;

import com.pikaqiu.familybucket.constants.Constants;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 22:00
 */
public class HttpResponse<T>{

    private Integer resultCode;
    private String message;
    private T data;

    public HttpResponse(Integer resultCode){
        this.resultCode = resultCode;
    }
    public HttpResponse(){
        this(Constants.RESULT_SUCCESS);
    }
    public HttpResponse(String errorMessage){
        setErrorMessage(errorMessage);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpResponse<T> setErrorMessage(String message){
        return setErrorMessage(Constants.RESULT_FAILURE, message);
    }
    public HttpResponse<T> setErrorMessage(Integer errorCode,String message){
        this.resultCode = errorCode;
        this.message = message;
        return this;
    }
    public HttpResponse<T> setData(T data){
        this.data = data;
        return this;
    }
    public HttpResponse<T> setResultCode(Integer resultCode){
        this.resultCode = resultCode;
        return this;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
