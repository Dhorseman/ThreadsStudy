package com.hzm.concurrent.chapter14;

/**
 * 自定义实现一个CountDown，理解JDK中CountDown的原理
 */
public class CountDown {
    private final int total;

    private int conuter=0;

    public CountDown(int total) {
        this.total = total;
    }
    public void down(){
        synchronized (this){
            this.conuter++;
            this.notifyAll();
        }
    }
    public void await() throws InterruptedException {
        synchronized (this){
            while (conuter!=total){
                this.wait();
            }
        }
    }
}
