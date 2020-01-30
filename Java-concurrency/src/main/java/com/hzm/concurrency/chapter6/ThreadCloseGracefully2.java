package com.hzm.concurrency.chapter6;

/**
 * 如何优雅的关闭线程？
 * 1、合理的利用interrupt()
 * 2、通过设置开关的方式
 */
public class ThreadCloseGracefully2 {
    private static class Worker extends Thread{

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break; //或者是return;根据情况选择，break可以继续执行后面的逻辑，return直接返回

                }
            }
        }

        public static void main(String[] args) {
            Worker worker = new Worker();
            worker.start();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
        }
    }
}
