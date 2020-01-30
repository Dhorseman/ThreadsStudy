package com.hzm.concurrency.chapter4;

public class DeamonThread {
    public static void main(String[] args) {

        Thread t1 = new Thread("Test"){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"start");
                    Thread.sleep(100000);
                    System.out.println(Thread.currentThread().getName()+"done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        System.out.println(Thread.currentThread().getName());

    }
}
