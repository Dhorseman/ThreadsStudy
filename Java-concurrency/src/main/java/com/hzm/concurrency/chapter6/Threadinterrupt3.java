package com.hzm.concurrency.chapter6;

/**
 * join()时被打断
 */
public class Threadinterrupt3 {


    public static void main(String[] args) throws InterruptedException {

        Thread t  = new Thread(){
            @Override
            public void run() {
                while (true){


                }
            }
        };
        t.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                main.interrupt();
                System.out.println("interrupt");
            }
        };
        t2.start();
        //这里调用join()的线程实际上是main，所以interrupt的也应当是main
        t2.join();
    }
}
