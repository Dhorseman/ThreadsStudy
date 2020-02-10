package com.hzm.concurrent.chapter10;

import java.util.Random;

/**
 * 复杂场景，可以看到ThreadLocal是线程安全的
 */
public class ThreadLocalComplexTest {

    private static final ThreadLocal<String> threadLocal= new ThreadLocal<>();
    //seed
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            threadLocal.set("Thread - thread1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            threadLocal.set("Thread - thread2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("=============");
        System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
    }
}
