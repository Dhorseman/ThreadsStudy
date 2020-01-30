package com.hzm.concurrency.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 该程序存在问题：
 * 27行处，其他线程也可以进行unlock()，导致锁被释放，让其他线程拿到锁
 * 应该指定好释放锁的线程为拿到锁的线程
 * BooleanLock2为改进版本
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        final  BooleanLock2 booleanLock = new BooleanLock2();
        Stream.of("T1","T2","T3","T4").forEach(name->{
            new Thread(()->{
                try {
                    //等待10ms，如果抢不到锁则算超时
                    booleanLock.lock(10L);
                    Optional.of(Thread.currentThread().getName()+"抢到了锁").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Lock.TimeOutException e){
                    Optional.of(Thread.currentThread().getName()+"超时").ifPresent(System.out::println);
                } finally {
                    booleanLock.unlock();
                }
            },name).start();
        });
        //bug!
//        Thread.sleep(100);
//        booleanLock.unlock();

    }
    private static void work() throws InterruptedException{
        Optional.of(Thread.currentThread().getName()+" is working...")
                .ifPresent(System.out::println);
        Thread.sleep(40_000);
    }
}
