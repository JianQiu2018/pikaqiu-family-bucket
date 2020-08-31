package com.pikaqiu.familybucket.patterns.decorator;

/**
 * 抽象装饰者：AbstractDecorator，定义【被装饰者】与【具体装饰者】共同行为
 */
public abstract class AbstractDecorator implements ComponentCatch {

    protected ComponentCatch baseCatch;

    public AbstractDecorator(ComponentCatch baseCatch) {
        this.baseCatch = baseCatch;
    }

    @Override
    public Object getCatch(String key) {
        return baseCatch.getCatch(key);
    }

}
