package com.pikaqiu.familybucket.patterns;

import com.pikaqiu.familybucket.patterns.decorator.ComponentCatch;
import com.pikaqiu.familybucket.patterns.decorator.FirstCatch;
import com.pikaqiu.familybucket.patterns.decorator.SecondCatch;
import com.pikaqiu.familybucket.patterns.decorator.ThiredCatch;

/**
 * 装饰模式和代理模式区别？
 *
 * 代理模式：在方法之前和之后实现处理，在方法上实现增强，隐藏真实方法的真实性，保证安全。
 *
 * 装饰模式：不改变原有的功能，实现增强，不断新增很多装饰。
 *
 */
public class FactoryCatch {

    public static ComponentCatch getComponentCatch() {
        ThiredCatch thiredCatch = new ThiredCatch(new SecondCatch(new FirstCatch()));
        return thiredCatch;
    }

    public static void main(String[] args) {
        ComponentCatch getComponentCatch = getComponentCatch();
        Object value1 = getComponentCatch.getCatch("1");
        System.out.println("value1:" + value1);
        System.out.println("###########################");
        Object value2 = getComponentCatch.getCatch("1");
        System.out.println("value2:" + value2);
    }

}
