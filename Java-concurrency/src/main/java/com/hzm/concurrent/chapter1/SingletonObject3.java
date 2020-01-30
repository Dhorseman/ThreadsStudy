package com.hzm.concurrent.chapter1;


public class SingletonObject3 {

    private static SingletonObject3 instance;
    private SingletonObject3(){

    }
    /**
     * 单例设计模式
     * 多线程下存在的问题，单例判断是否为null时，由于线程的切换可能会导致多个实例被new出
     * 解决思路一：getInstance()加上synchronized，使得线程串行化，但是这样会导致性能变差
     */
    public synchronized static SingletonObject3 getInstance(){
        if (null==instance){
            instance = new SingletonObject3();
        }
        return instance;
    }
}
