package com.hzm.concurrent.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * CountDown设计模式
 * 使用场景：如果同时开了多个线程执行任务，但是最后需要等待所有线程执行完任务才能开启下一阶段的任务
 *
 *示例中：第二阶段没有等待第一阶段完成便执行完，可以使用join()的方式等待第一阶段任务完成，也可以使用JDK自带的CountDownLatch
 */
public class JDKCountDown {
    private static final Random random = new Random(System.currentTimeMillis());


    public static void main(String[] args) throws InterruptedException {
        //注意需要和循环的次数对应上
        final CountDownLatch latch = new CountDownLatch(5);

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
                latch.countDown();
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
