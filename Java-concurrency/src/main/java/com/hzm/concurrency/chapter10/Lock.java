package com.hzm.concurrency.chapter10;

import java.util.Collection;

public interface Lock {
    class TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
    }
    //锁方法，允许中断
    void Lock() throws InterruptedException;
    //设定超时
    void lock(long mills) throws InterruptedException,TimeOutException;
    //释放锁
    void unlock();
    //查看阻塞状态
    Collection<Thread> getBlockedThread();
    //查看阻塞线程数量
    int getBlockedSize();

}
