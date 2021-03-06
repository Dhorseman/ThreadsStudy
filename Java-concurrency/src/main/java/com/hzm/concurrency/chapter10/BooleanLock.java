package com.hzm.concurrency.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * 自定义锁实现加锁解锁和超时等待功能
 */
public class BooleanLock implements Lock {

    //初始值为false，刚开始无人获取锁
    private boolean initValue;

    private  Collection<Thread> blockedThreadCollection = new ArrayList<>();
    public BooleanLock(){
        this.initValue = false;
    }

    @Override
    public synchronized void Lock() throws InterruptedException {
        //如果锁被拿走了则等待
        while (initValue){
            //加入等待队列中
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        //从等待队列中剔除
        blockedThreadCollection.remove(Thread.currentThread());
        //拿到锁
        initValue=true;
    }

    //在指定时间内如果没有拿到锁，则放弃抢锁
    @Override
    public void lock(long mills) throws InterruptedException, TimeOutException {


    }

    @Override
    public synchronized void unlock() {
        //释放锁
        this.initValue=false;
        Optional.of(Thread.currentThread()+"释放了锁").ifPresent(System.out::println);
        this.notifyAll();
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        //直接返回list是不安全的，其他用户可对list进行操作，所以返回一个不可修改的list
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
