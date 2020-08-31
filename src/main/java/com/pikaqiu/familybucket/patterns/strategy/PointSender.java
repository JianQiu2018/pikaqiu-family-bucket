package com.pikaqiu.familybucket.patterns.strategy;

import org.springframework.stereotype.Service;


@Service
public class PointSender implements PrizeSender{

    @Override
    public boolean support(Integer type) {
        return PrizeTypeEnum.POINT.getKey().equals(type);
    }

    @Override
    public void sendPrize(Integer type) {
        System.out.println("发放积分");
    }
}
