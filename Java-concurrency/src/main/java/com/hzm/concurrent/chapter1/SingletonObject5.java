package com.hzm.concurrent.chapter1;


/**
 * 针对思路一的再次改进：加上volatile
 * volatile可以保证内存的可见性（多个线程看到的数据是同一个）以及有序性（避免JVM进行优化），严格遵循HapeensBefore原则
 */
public class SingletonObject5 {

    private static volatile SingletonObject5 instance;
    private SingletonObject5(){

    }
    public  static SingletonObject5 getInstance(){

        if (null==instance){
            synchronized (SingletonObject5.class){
                if (null==instance){
                    instance = new SingletonObject5();
                }
            }
        }
        return instance;
    }
}
