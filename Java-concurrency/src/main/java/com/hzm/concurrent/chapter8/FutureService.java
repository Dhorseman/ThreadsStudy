package com.hzm.concurrent.chapter8;

import java.util.function.Consumer;

/**
 * 通过FutureService将Future和FutureTask组合起来
 * 传入要做的任务，返回一个结果
 */
public class FutureService {
    public <T> Future<T> submit(final FutureTask<T> task){
        //额外的线程用于将任务结果返回
        AsyncFuture<T> asyncFuture = new AsyncFuture();
        //创建一个线程用于运行任务
        new Thread(()->{
            //得到结果
            T result = task.call();
            //将结果通知并返回
            asyncFuture.done(result);
        }).start();
        return asyncFuture;
    }

    //加入了回调
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer){
        //额外的线程用于将任务结果返回
        AsyncFuture<T> asyncFuture = new AsyncFuture();
        //创建一个线程用于运行任务
        new Thread(()->{
            //得到结果
            T result = task.call();
            //将结果通知并返回
            asyncFuture.done(result);
            consumer.accept(result);
        }).start();
        return asyncFuture;
    }
}
