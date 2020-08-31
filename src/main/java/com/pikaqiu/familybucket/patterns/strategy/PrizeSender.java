package com.pikaqiu.familybucket.patterns.strategy;


public interface PrizeSender {

    /**
     * 用于判断当前实例是否支持当前奖励的发放
     */
    boolean support(Integer type);

    /**
     * 发放奖励
     */
    void sendPrize(Integer type);


}
