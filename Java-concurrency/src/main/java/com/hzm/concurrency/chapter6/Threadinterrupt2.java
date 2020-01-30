package com.hzm.concurrency.chapter6;

/**
 * wait()时被打断
 */
public class Threadinterrupt2 {

    private static Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t  = new Thread(){
            @Override
            public void run() {
                while (true){
                    synchronized (MONITOR){
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t.start();
        //调用start()后线程并不一定马上就能执行，所以等待一下或者调用join()
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
