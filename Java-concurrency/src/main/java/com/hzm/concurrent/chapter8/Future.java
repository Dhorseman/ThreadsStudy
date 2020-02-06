package com.hzm.concurrent.chapter8;

/**
 * 多线程Future设计模式
 * Future类用于返回结果
 */
public interface Future<T> {
    T get() throws InterruptedException;

}
