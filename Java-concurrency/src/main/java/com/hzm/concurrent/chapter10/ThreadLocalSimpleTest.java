package com.hzm.concurrent.chapter10;

/**
 * 了解ThreadLocal的使用
 */
public class ThreadLocalSimpleTest {
    //可以通过重写initialValue()方法，给定初始值
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "Hello World";
        }
    };
    //JVM start main thread
    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("Alex");
        Thread.sleep(1000);
        System.out.println(threadLocal.get());
    }
}
