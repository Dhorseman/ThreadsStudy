package com.hzm.concurrency.chapter12;

import java.util.Arrays;

/**
 * 线程组的创建
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        //方法一：
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println(getThreadGroup().getParent());
                        System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().activeCount());
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
//        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
//        System.out.println(tg2.getName());
//        System.out.println(tg2.getParent());
        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2,"T2"){
            @Override
            public void run() {
                System.out.println(tg1.getName());
                //将当前tg1中活跃的线程打印出
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);
            }
        };
        t2.start();

    }
}
