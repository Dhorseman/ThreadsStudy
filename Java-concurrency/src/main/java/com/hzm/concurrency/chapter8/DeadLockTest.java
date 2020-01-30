package com.hzm.concurrency.chapter8;

/**
 * 死锁场景模拟，通过终端输入 jps 查找线程进程号
 * 终端输入 jstack 端口号 显示进程详细信息(Found 1 deadlock)
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(){
            @Override
            public void run() {
                while (true){
                    deadLock.m1();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    otherService.s2();
                }
            }
        }.start();
    }
}
