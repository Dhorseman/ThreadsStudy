package com.hzm.concurrent.chapter1;

public class SingletonObject1 {
    //缺点：无法进行懒加载
    private static final SingletonObject1 istance1 = new SingletonObject1();
    //单例私有构造
    private SingletonObject1(){
        //empty
    }
    //多线程情况下安全，返回的是同一个实例
    public static SingletonObject1 getInstance(){
        return istance1;
    }
}
