package com.pikaqiu.familybucket.patterns.decorator;

import java.util.HashMap;

/**
 * 三级缓存【装饰者】
 * ThiredCatch：在不改变原有二级缓存的基础之上搭建三级缓存
 */
public class ThiredCatch extends AbstractDecorator {
    //伪装成三级缓存
    HashMap thiredCatchMap = new HashMap();

    public ThiredCatch(ComponentCatch baseCatch) {
        super(baseCatch);
    }

    public void putCatch(String key, Object value) {
    }

    public Object getCatch(String key) {
        System.out.println(">>>>>>>调用三级缓存查询数据");
        //先查询三级缓存
        Object thiredValue = thiredCatchMap.get(key);
        //如果三级缓存没有，再查询二级缓存,如果二级缓存为空的话，再查询一级缓存
        if (thiredValue == null) {
            Object secondValue = super.getCatch(key);
            //如果二级缓存不为空
            if (secondValue != null) {
                //将二级缓存缓存到三级缓存
                thiredCatchMap.put(key, secondValue);
                thiredValue = secondValue;
            }
        }
        return thiredValue;
    }
}
