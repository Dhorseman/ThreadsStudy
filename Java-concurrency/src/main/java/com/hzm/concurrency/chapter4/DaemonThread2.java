package com.hzm.concurrency.chapter4;

/**
 * 在线程中再创建线程，观察守护线程的作用
 */
public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
           Thread innerThread = new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
           innerThread.setDaemon(true);
           innerThread.start();
            try {
                Thread.sleep(1000);
                System.out.println("t Thread finish done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        t.setDaemon(true);

        t.start();
    }

}
