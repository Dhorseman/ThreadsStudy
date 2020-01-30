package com.hzm.concurrency.chapter7;

/**
 * 探究this锁，如果拿的锁不同，那么T1,T2可以同时执行m1,m2两个方法，synchronize代表的是this锁，synchronize()中是自定义的锁
 * 如果将m1的synchronize换成m2的方式，结果同示例1相同
 */
public class SynchronizedThis2 {
    public static void main(String[] args) {
        ThisLock2 thisLock = new ThisLock2();
        new Thread("T1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();
        new Thread("T2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock2{

    private final Object LOCK= new Object();

    public synchronized void m1(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void m2(){
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}