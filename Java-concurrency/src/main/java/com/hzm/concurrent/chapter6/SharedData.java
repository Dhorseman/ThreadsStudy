package com.hzm.concurrent.chapter6;

public class SharedData {
     //定义一个char型的数组，读的时候从buffer中读，写的时候往buffer中写
    private final char[] buffer;
    //随机休眠
    private final ReadWriteLock lock = new ReadWriteLock();


    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0;i<buffer.length;i++){
            //初始化
            this.buffer[i] = '*';
        }
    }
    //向外提供read()
    public char[] read() throws InterruptedException {
        try{
            lock.readLock();
            return doRead();
        }finally {
            lock.readUnlock();
        }
    }
    //向外提供的write()
    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        }finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i =0;i<buffer.length;i++){
            buffer[i] = c;
            slowly(10);
        }
    }

    private char[] doRead() {
        //创建一个副本
        char[] newBuf = new char[buffer.length];
        for (int i =0;i<buffer.length;i++){
            newBuf[i] = buffer[i];
        }
        slowly(50);
        return newBuf;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
