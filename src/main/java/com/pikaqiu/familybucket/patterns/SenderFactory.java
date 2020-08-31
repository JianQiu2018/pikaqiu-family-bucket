package com.pikaqiu.familybucket.patterns;

import com.pikaqiu.familybucket.patterns.strategy.PrizeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 策略模式
 */
@Component
public class SenderFactory {

    @Autowired
    private List<PrizeSender> senderList;

    public PrizeSender getPrizeSender(Integer type){
        for (PrizeSender prizeSender : senderList) {
            if(prizeSender.support(type)){
                return prizeSender;
            }
        }
        throw new UnsupportedOperationException("unsupported request: " + type);
    }


}
