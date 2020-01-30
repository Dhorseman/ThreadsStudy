package com.hzm.concurrency.chapter11;

/**
 * 线程的run()方法中无法抛出异常，如何进行捕获并对异常进行及时的反应？
 */
public class ThreadException {
    private final static int A = 10;
    private final static int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(5_000L);
                int result = A/B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //通过这个API捕获异常并进行处理
        t.setUncaughtExceptionHandler((thread,e)->{
            System.out.println(e);
            System.out.println(thread);
        });
        t.start();
    }
}
