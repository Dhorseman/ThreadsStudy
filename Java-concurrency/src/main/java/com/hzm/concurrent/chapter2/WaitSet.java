package com.hzm.concurrent.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 思考：线程在调用wait()时，线程将被存放在什么地方，如何唤醒，唤醒后从什么地方获得
 * 在Object类的305行起，表明每个类中都有一个WaitSet用于存放调用了wait()之后进入block状态的实例;
 * 线程被notify()之后，不一定立即得到执行;
 * 线程从waitSet中被唤醒的顺序不一定是先进先出（FIFO）
 * work()用于测试，当线程释放锁之后必须重新争抢锁，那是否会再次重复之前的行为（是否会重复输出"Begin..."）？
 * 答案是不会，因为代码执行在内存中有记录（程序计数器会储存执行到的地址）,被唤醒之后会从记录的地址处执行后面的代码
 */
public class WaitSet {
    private static final Object LOCK = new Object();

    private static void work(){
        synchronized (LOCK){
            System.out.println("Begin...");
            try {
                System.out.println("Thread will coming");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread will out");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(){
            @Override
            public void run() {
                work();
            }
        }.start();
        Thread.sleep(100);
        synchronized (LOCK){
            LOCK.notify();
        }

        IntStream.rangeClosed(1,10).forEach(i->{
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                        synchronized (LOCK){
                            try {
                                Optional.of(Thread.currentThread().getName()+"加入到等待队列").ifPresent(System.out::println);
                                LOCK.wait();
                                Optional.of(Thread.currentThread().getName()+"离开等待队列").ifPresent(System.out::println);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                }
            }.start();
        });

        Thread.sleep(3000);

        IntStream.rangeClosed(1,10).forEach(i->{
            synchronized (LOCK){
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
