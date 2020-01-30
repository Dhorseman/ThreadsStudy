package com.hzm.concurrency.chapter9;

import java.util.stream.Stream;

/**
 * 终极版本的生产者消费者模式
 * 注意区分if()和while()条件下的区别
 */
public class ProducerConsumerVersion4 {
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
        ProducerConsumerVersion4 pc = new ProducerConsumerVersion4();
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
