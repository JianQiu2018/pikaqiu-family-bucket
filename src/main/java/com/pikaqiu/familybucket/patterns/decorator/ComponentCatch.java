package com.pikaqiu.familybucket.patterns.decorator;

public interface ComponentCatch {
    /**
     * 定义共同行为的方法标准
     */
    Object getCatch(String key);

    void putCatch(String key,Object value);

}
