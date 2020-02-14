package com.hzm.concurrent.chapter16;

public class CounterTest {
    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();
        Thread.sleep(1000);
        counterIncrement.close();
    }
}
