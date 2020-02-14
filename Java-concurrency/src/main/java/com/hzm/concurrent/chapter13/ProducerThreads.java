package com.hzm.concurrent.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟生产队列
 */
public class ProducerThreads extends Thread{
    //信息队列
    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());
    //计数器
    private final static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThreads(MessageQueue messageQueue,int seq) {
        super("Producer--"+seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = new Message("Message-" + counter.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName()+" put message "+message.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
