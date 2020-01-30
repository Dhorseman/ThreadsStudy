package com.hzm.concurrency.chapter10;

import com.hzm.concurrency.chapter7.SynchronizedStatic;

public class SynchronizedProblem {
    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                System.out.println("t1");

                SynchronizedProblem.run();
            }
        }.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("t2");

                SynchronizedProblem.run();
            }
        };
        t2.start();
    }
    private synchronized static void run(){
//        Thread.sleep(100_000);
        while (true){

        }
    }
}
