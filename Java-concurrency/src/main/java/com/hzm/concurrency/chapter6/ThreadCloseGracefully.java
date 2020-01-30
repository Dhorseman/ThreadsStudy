package com.hzm.concurrency.chapter6;

/**
 * 如何优雅的关闭线程？
 * 1、合理的利用interrupt()
 * 2、通过设置开关的方式
 */
public class ThreadCloseGracefully {
    private static class Worker extends Thread{
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start){

            }
        }
        public void shutDown(){
            this.start=false;
        }

        public static void main(String[] args) {
            Worker worker = new Worker();
            worker.start();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            worker.shutDown();
        }
    }
}
