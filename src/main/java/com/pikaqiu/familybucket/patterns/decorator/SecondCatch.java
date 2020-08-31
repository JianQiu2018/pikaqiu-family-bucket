package com.pikaqiu.familybucket.patterns.decorator;

import java.util.HashMap;

/**
 * 二级缓存：SecondCatch，【装饰者】
 * SecondCatch：在不改变原有一级缓存基础之上搭建二级缓存
 */
public class SecondCatch extends AbstractDecorator {
    //伪装成二级缓存
    HashMap secondCatchMap = new HashMap();

    public SecondCatch(ComponentCatch baseCatch) {
        super(baseCatch);
    }

    public Object getCatch(String key) {
        System.out.println(">>>>>>>调用二级缓存查询数据");
        //先查询二级缓存
        Object secondValue = secondCatchMap.get(key);
        //如果二级缓存没有，再查询一级缓存
        if (secondValue == null) {
            Object firstValue = super.getCatch(key);
            //如果一级缓存有的话
            if (firstValue != null) {
                //将一级缓存缓存到二级缓存
                secondCatchMap.put(key, firstValue);
                secondValue = firstValue;
            }
        }
        return secondValue;
    }

    @Override
    public void putCatch(String key, Object value) {

    }

}
