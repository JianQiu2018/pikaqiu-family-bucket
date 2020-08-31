package com.pikaqiu.familybucket.patterns;

public class Person {

    private String name;
    private int age;
    private String sex;
    private final String sex2 = "2";
    private final static String sex3 = "2";

    final public void work(){ //final 修饰的最终方法 可以被子类继承，但不能被重写
        System.out.println("a person can do work");
    }

    final public void work(int age){ //重载一个final类
        System.out.println("a person can do another work");
    }

    public void eat(){   //普通方法  可以被子类继承、重写
        System.out.println("a person need to eat");
    }

    public static void sleep(){  //静态方法  可以被继承
        System.out.println("a person need to sleep");
    }
}
