package com.pikaqiu.familybucket.service.abstracts;

/**
 * Description: 模板设计模式，抽取共同行为，实现基础方法，让子类自行实现相关特殊行为
 *
 * @author PikaQiu
 * @date 2019/8/29 23:03
 */
public abstract class ModeMethod {

    protected final void allSuperMethodTemplate(){
        start1();
        start2();
        start3();
        start4();
    }

    /**
     * 基础方法
     */
    private void start1() {
        System.out.println("1~~~");
    }

    protected abstract void start2();

    private void start3() {
        System.out.println("3~~~");
    }

    private void start4() {
        if(isStart()){
            System.out.println("4~~~");
        }
    }

    /**
     * 可以默认实现或者空实现
     * @return
     */
    protected boolean isStart() {
        return false;
    }

}
