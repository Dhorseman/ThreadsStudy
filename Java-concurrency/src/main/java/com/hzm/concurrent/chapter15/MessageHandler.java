package com.hzm.concurrent.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * PerThread设计模式:一个请求对应一个线程
 */
public class MessageHandler {
    private final static Random random = new Random(System.currentTimeMillis());

    //线程的创建和销毁极耗资源，所以使用线程池
    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(()->{
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The Message will be handle by " + Thread.currentThread().getName()+" "+value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        new Thread(() -> {
//            String value = message.getValue();
//            try {
//                Thread.sleep(random.nextInt(1000));
//                System.out.println("The Message will be handle by " + Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
    public void shutdown(){
        ((ExecutorService)executor).shutdown();
    }
}
