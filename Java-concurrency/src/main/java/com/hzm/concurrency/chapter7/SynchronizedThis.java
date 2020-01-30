package com.hzm.concurrency.chapter7;

/**
 * 探究this锁，如果在m2方法前没加synchronize，则两个线程会可以同时访问m1,m2方法
 * 如果在m2前加了synchronize锁，则多线程访访问m1、m2时会顺序访问，说明synchronize锁住的是同一个东西，此时synchronize所代表的就是this
 */
public class SynchronizedThis {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
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

class ThisLock{
    public synchronized void m1(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void m2(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}