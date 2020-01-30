package com.hzm.concurrent.chapter1;


/**
 * 思路二：
 * 保证懒加载，性能好，安全
 */
public class SingletonObject6 {

    private SingletonObject6(){

    }
   private static class InstanceHolder{
    //static保证只有一个实例，且保证线程执行顺序，使用时才被加载
        private final static SingletonObject6 instance = new SingletonObject6();
   }
   public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
   }
}
