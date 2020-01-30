package com.hzm.concurrency.chapter9;

import java.util.stream.Stream;

/**
 * 改进版本的生产者消费者模式
 * 在只有一个生产者和一个消费者的情况下运行良好
 * 但是多个生产者和多个消费者运行的情况下会有一些问题
 * 由于notify()的线程不明确，所以被notify()的线程发现自己生产的数据未被消费或者未有数据可以被消费，这会导致线程继续wait()，这并不是死锁的状况
 * 此时所有的线程都等待着其他线程将自己唤醒
 */
public class ProducerConsumerVersion3 {
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
        ProducerConsumerVersion3 pc = new ProducerConsumerVersion3();
        Stream.of("P1","P2").forEach(n->{
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        pc.produce();
                    }
                }
            }.start();
        });
        Stream.of("C1","C2").forEach(n->{
            new Thread(){
                @Override
                public void run() {
                    while (true) {
                        pc.consume();
                    }
                }
            }.start();
        });

    }
}
