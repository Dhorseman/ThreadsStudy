package com.hzm.concurrency.chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 生产者消费者实战案例
 */
public class CaptureService {
    //同时运行线程最大值
    private final static int MAX_WORKER=5;

    //定义一个容器保存同时运行的线程
    final static private LinkedList<Control> CONTROLS = new LinkedList<>();

    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();
        //将一组字符串转换成流对象，在map中设置function，传入(对象:方法)将字符串转换成线程，然后将线程逐一启动
        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10").stream().
                map(CaptureService::createCaptureThread)
                .forEach(t->{
                    t.start();
                    //因为forEach()不会返回数组，所以 在这里将遍历到的线程存储起来方便之后使用
                    worker.add(t);
                });
        //保证线程执行完各自方法
        worker.stream().forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }
    private static Thread createCaptureThread(String name){
        //同时运行的线程最多为5个，多余的线程等待
        return new Thread(()->{
            //ifPresent()防止空指针
            Optional.of("The worker ["+Thread.currentThread().getName()+"] BEGIN capture data").ifPresent(System.out::println);

            synchronized (CONTROLS){
                //进行判断
                while (CONTROLS.size()>MAX_WORKER){
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }
            Optional.of("The worker ["+Thread.currentThread().getName()+"] is working ...").ifPresent(System.out::println);
            //模拟线程工作
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS){
                Optional.of("The worker ["+Thread.currentThread().getName()+"] END capture data ...").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }

    private static class Control{

    }
}
