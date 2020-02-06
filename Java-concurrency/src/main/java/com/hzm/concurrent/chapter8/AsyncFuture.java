package com.hzm.concurrent.chapter8;

public class AsyncFuture<T> implements Future {

    private volatile boolean done = false;

    private T result;

    //得到结果时通知并赋值
    public void done(T result){
        synchronized (this){
            this.result =result;
            this.done = true;
            this.notifyAll();
        }
    }
    //获取结果，如果没有完成则等待
    @Override
    public Object get() throws InterruptedException {
        synchronized (this){
            while (!done){
                this.wait();
            }
        }
        return result;
    }
}
