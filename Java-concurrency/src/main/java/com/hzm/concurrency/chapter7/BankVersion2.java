package com.hzm.concurrency.chapter7;


/**
 * 模拟银行业务增强版，保证了实例只有一个，避免了线程逻辑和业务逻辑混淆
 * 但是依然存在数据顺序混乱和重复等问题，不同线程可能抢到同一个资源
 */
public class BankVersion2 {

    private final static int MAX =500;

    public static void main(String[] args) {

        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
//        final Runnable ticketWindow = ()->{
//            int index =1;
//            while (index<=MAX){
//                System.out.println(Thread.currentThread()+"的号码是："+(index++));
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };

        Thread windowThread1 = new Thread(ticketWindowRunnable,"一号窗口");
        Thread windowThread2 = new Thread(ticketWindowRunnable,"二号窗口");
        Thread windowThread3 = new Thread(ticketWindowRunnable,"三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
