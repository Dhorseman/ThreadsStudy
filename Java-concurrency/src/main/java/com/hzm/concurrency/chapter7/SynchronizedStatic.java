package com.hzm.concurrency.chapter7;

/**
 * 有加锁的静态代码块存在时，线程同样需要对静态代码块中的锁进行抢占
 * 静态代码块只会初始化一次，所以任一线程执行静态代码块中的代码之后静态代码块将不再执行
 * 静态代码块中的内容初始化完毕之后才会进行其他代码执行
 * 静态代码块中的锁是类本身
 */
public class SynchronizedStatic {
    //假设存在一个静态代码块并加锁，和一个不加锁的方法，测试哪个线程会先抢占静态代码块中的锁
    static {
        synchronized (SynchronizedStatic.class){
            System.out.println("static  "+Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1(){
        System.out.println("m1  "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized static void m2(){
        System.out.println("m2  "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  static void m3(){
        System.out.println("m3 "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
