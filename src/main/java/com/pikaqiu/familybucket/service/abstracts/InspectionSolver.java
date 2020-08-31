package com.pikaqiu.familybucket.service.abstracts;

/**
 * Description: 策略设计模式，可以减少大量复用代码
 *
 * @author PikaQiu
 * @date 2019/8/23 21:37
 */
public abstract class InspectionSolver {

    public abstract void solve(Long orderId, Long userId);

    public abstract String[] supports();

}
