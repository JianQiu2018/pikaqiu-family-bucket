package com.pikaqiu.familybucket.patterns;

public class Man extends Person{

    private String sex;
    private final String sex2 = "2";
    private final static String sex3 = "2";

    public void eat(){
        super.eat();
        System.out.println("the man also need do eat");
    }

    public static void sleep(){
        System.out.println("the man also need to sleep");
    }

    public static void main(String[] args) {
        Person man2 = new Man();
        man2.work(20);
        man2.eat();
        Man man = new Man();
        man.eat();
    }

}