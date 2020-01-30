package com.hzm.concurrency.chapter9;

/**
 * 改进版本的生产者消费者模式
 * 在只有一个生产者和一个消费者的情况下运行良好
 * 但是多个生产者和多个消费者运行的情况下会有一些问题
 */
public class ProducerConsumerVersion2 {
    private int i = 0;
    final private Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public void produce(){
        synchronized (LOCK){
            if (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                i++;
                System.out.println("P-->"+i);
                LOCK.notify();
                isProduced=true;
            }
        }
    }
    public void consume(){
        synchronized (LOCK){
            if (isProduced){
                System.out.println("C-->"+i);
                LOCK.notify();
                isProduced=false;
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion2 pc = new ProducerConsumerVersion2();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    pc.produce();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();
    }
}
