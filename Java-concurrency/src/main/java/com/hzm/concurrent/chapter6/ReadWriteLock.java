package com.hzm.concurrent.chapter6;

/**
 * 读写锁设计模式
 * 将读写锁分离，使得线程在进行读操作时不再进行串行化处理，提升读的效率
 */
public class ReadWriteLock {
    //当前进行读操作的线程数
    private int readingReaders = 0;
    //想要进行读操作的线程数
    private int waitingReaeders = 0;
    //当前进行写操作的线程数
    private int writingWriters = 0;
    //想要进行写操作的线程数
    private int waitingWriters = 0;
    //加入标记，更偏向写操作
    private boolean preferWriter = true;
    public ReadWriteLock(){
        this(true);
    }
    public ReadWriteLock(boolean preferWriter){
        this.preferWriter=preferWriter;
    }
    //读锁
    public synchronized void readLock() throws InterruptedException {
        //线程刚进来先进入等待队列中
        this.waitingReaeders++;
        try{

        //同一时刻只能有一个线程进行写操作，进行写操作的时候不能读
        while (writingWriters>0 || (preferWriter&&waitingWriters>0)){
            this.wait();
        }
        //当前线程进行读操作
        this.readingReaders++;
        }finally {
            //完事之后从队列中剔除
            this.waitingReaeders--;
        }
    }
    //释放读锁
    public synchronized void readUnlock(){
        this.readingReaders--;
        this.notifyAll();
    }
    //写锁
    public synchronized void writeLock() throws InterruptedException {
        //进来先进入等待队列中
        this.waitingWriters++;
        try{
            //当有人在读或者写时，不能进行写操作
             while (readingReaders>0||writingWriters>0){
                 this.wait();
             }
             //进行写操作
             this.writingWriters++;
        }finally {
            this.waitingWriters--;
        }
    }
    //释放写锁
    public synchronized void writeUnlock(){
        this.writingWriters--;
        this.notifyAll();
    }
}
