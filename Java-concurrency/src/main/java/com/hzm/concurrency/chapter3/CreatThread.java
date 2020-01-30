package com.hzm.concurrency.chapter3;

import java.util.Arrays;

/**
 * 结果是一共有三个线程
 * Monitor Ctrl-Break,5
 */
public class CreatThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        //获取当前线程组
        System.out.println(t1.getThreadGroup());
        //获取当前线程名
        System.out.println(Thread.currentThread().getName());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        //获取线程组名称
        System.out.println(threadGroup.getName());
        //获取线程组中当前活跃线程数
        System.out.println(threadGroup.activeCount());
        Thread[] threads = new Thread[threadGroup.activeCount()];
        //获得线程组中线程的数量
        threadGroup.enumerate(threads);

        //循环打印
        for (Thread temp:threads){
            System.out.println(temp);
        }
        //Java8写法
        Arrays.asList(threads).forEach(System.out::println);

    }
}
