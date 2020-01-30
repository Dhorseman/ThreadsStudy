package com.hzm.concurrent.chapter3;

/**
 * 使用volatile关键字，在不加锁的情况下完成数据的共享
 * 在INIT_VALUE前面加上volatile，使得Reader线程可以捕获到INIT_VALUE值的改变
 * 这就是
 */
public class VolatileTest {

    private volatile static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        //定义一个读线程
        /**
         * 当程序运行时，会将运算的数据从主内存中copy一份到CPU的高速缓存中行，程序进行操作时是对高速缓存中的数据进行读取和写入
         * 如果有写入，则将数据写入高速缓存中，再将高速缓存中的数据刷新的主内存中
         * 但是这样的操作会到来一个问题，在多线程情况下，对缓存写操作带来的数据不一致问题
         * 解决办法：
         * 1、给数据总线加锁：
         *     总线（数据总线，地址总线，控制总线）
         * 2、CPU高速缓存一致性协议（目前常用）：保证主内存的数据与缓存中的副本一致
         *      核心思想：①当CPU写入数据的时候，如果发现变量共享（在其他CPU中也存在该变量的副本），会发出一个信号，通知其他CPU该变量的缓存无效
         *              ②当其他CPU访问该变量的时候，重新到主内存进行获取
         */
        new Thread(()->{
            int localValue = INIT_VALUE;
            while (localValue<MAX_LIMIT){
                if (localValue!=INIT_VALUE){
                    System.out.printf("The value updated to [%d]\n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"Reader").start();

         new Thread(()->{
             int localValue = INIT_VALUE;
             while(INIT_VALUE<MAX_LIMIT){
                 System.out.printf("Update the value to [%d]\n",++localValue);
                 INIT_VALUE = localValue;
                 try {
                     Thread.sleep(500);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         },"UPDATER").start();
    }
}
