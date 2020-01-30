package com.hzm.concurrency.chapter9;

/**
 * 模拟线程间的通信，通过生产者消费者模式完成
 * 存在问题：consumer只消费最新的一个，导致生产者不断产生新的数据，但无法被正常消费+
 */

public class ProducerConsumerVersion1 {
    private int i = 1;
    final private Object LOCK = new Object();
    private void produce(){
        synchronized (LOCK){
            System.out.println("P->"+(i++));
        }
    }
    private void consume(){
        synchronized (LOCK){
            System.out.println("C->"+i);
        }
    }

    public static void main(String[] args) {

        ProducerConsumerVersion1 pc = new ProducerConsumerVersion1();

        new Thread("P"){
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start();
        new Thread("C"){
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();


    }

}
