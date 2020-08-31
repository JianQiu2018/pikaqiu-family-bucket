package com.pikaqiu.familybucket.patterns.decorator;

import java.util.HashMap;

/**
 *  定义被装饰角色
 * 【一级缓存】，FirstCatch【被装饰的类】
 */
public class FirstCatch implements ComponentCatch {

    //伪装成一级缓存
    HashMap firstCatchMap = new HashMap();

    public FirstCatch() {
        firstCatchMap.put("1", "xuyu");
    }

    @Override
    public Object getCatch(String key) {
        Object value = firstCatchMap.get(key);
        System.out.println(">>>>>>>调用一级缓存查询数据");
        return value;
    }

    @Override
    public void putCatch(String key, Object value) {
        firstCatchMap.put(key,value);
    }

}
