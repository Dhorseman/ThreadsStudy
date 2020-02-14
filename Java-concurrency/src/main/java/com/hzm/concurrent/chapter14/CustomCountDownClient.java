package com.hzm.concurrent.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CustomCountDownClient {
    private static final Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException {
        final CountDown latch = new CountDown(5);

        System.out.println("准备多线程处理任务");
        //the first phase.
        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" is Working.");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //标记任务结束
                latch.down();
            },String.valueOf(i)).start();
        });
        //等着所有任务结束
        latch.await();
        //the second phase
        System.out.println("多线程任务全部结束，准备第二阶段任务");
        System.out.println("------");
        System.out.println("FINISH");
    }
}
