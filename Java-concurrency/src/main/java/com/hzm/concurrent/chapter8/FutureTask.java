package com.hzm.concurrent.chapter8;

/**
 * 真正做事情的接口
 * @param <T>
 */
public interface FutureTask<T> {
    //相当于SyncInvoker中的get()，去实现某个任务
    T call();
}
