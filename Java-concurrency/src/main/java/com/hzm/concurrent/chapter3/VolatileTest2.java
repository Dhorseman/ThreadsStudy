package com.hzm.concurrent.chapter3;

/**
 * 使用volatile关键字，在不加锁的情况下完成数据的共享
 * 在INIT_VALUE前面加上volatile，使得Reader线程可以捕获到INIT_VALUE值的改变
 * 这就是
 */
public class VolatileTest2 {

    private volatile static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;

    public static void main(String[] args) {
        //定义一个读线程

        new Thread(()->{
            while (INIT_VALUE<MAX_LIMIT){
                System.out.println("T1->"+(++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ADDER-1").start();

         new Thread(()->{
             while (INIT_VALUE<MAX_LIMIT){
                 System.out.println("T2->"+(++INIT_VALUE));
                 try {
                     Thread.sleep(10);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         },"ADDER-2").start();
    }
}
