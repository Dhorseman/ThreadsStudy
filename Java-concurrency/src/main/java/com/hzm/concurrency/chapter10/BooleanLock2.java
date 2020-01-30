package com.hzm.concurrency.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock2 implements Lock {

    //初始值为false，刚开始无人获取锁
    private boolean initValue;
    //用于存储当前线程
    private Thread currentThread;

    private  Collection<Thread> blockedThreadCollection = new ArrayList<>();
    public BooleanLock2(){
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
        this.initValue=true;
        this.currentThread = Thread.currentThread();

    }

    //在指定时间内如果没有拿到锁，则放弃抢锁
    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        //默认执行Lock()方法
        if (mills<=0){
            Lock();
        }
        //持续时间
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis()+mills;
        //如果锁被抢占
        while (initValue){
            //如果持续时间小于等于0则超时
            if (hasRemaining<=0){
                throw new TimeOutException("超时！");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining=endTime-System.currentTimeMillis();
        }
        this.initValue=true;
        this.currentThread=Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        //通过判断保证其他线程不发进行锁的释放
        if (Thread.currentThread()==currentThread){
            //释放锁
            this.initValue=false;
            Optional.of(Thread.currentThread()+"释放了锁").ifPresent(System.out::println);
            this.notifyAll();
        }
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
