package com.hzm.concurrent.chapter1;


public class SingletonObject2 {

    private static SingletonObject2 instance;
    private SingletonObject2(){

    }
    /**
     * 多线程下存在的问题，单例判断是否为null时，由于线程的切换可能会导致多个实例被new出
     */
    public static SingletonObject2 getInstance(){
        if (null==instance){
            instance = new SingletonObject2();
        }
        return instance;
    }
}
