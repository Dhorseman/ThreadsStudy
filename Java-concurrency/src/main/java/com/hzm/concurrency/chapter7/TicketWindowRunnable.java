package com.hzm.concurrency.chapter7;

/**
 * 使用同步代码块的方式解决线程同步时，数据共享的问题
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private final static int MAX= 50;

    private final Object MONITOR = new Object();

    @Override
    public void run() {
            while (true) {
        synchronized (MONITOR) {
                if (index > MAX) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "的号码是：" + (index++));
            }
        }
    }
}
