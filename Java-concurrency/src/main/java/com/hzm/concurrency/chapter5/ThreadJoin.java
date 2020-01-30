package com.hzm.concurrency.chapter5;

/**
 * join()示例，模拟数据采集过程，通过join()方法使得结束时间一致
 */

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunaable("M1",10000l));
        Thread t2 = new Thread(new CaptureRunaable("M2",30000l));
        Thread t3 = new Thread(new CaptureRunaable("M3",15000l));
        t1.start();
        t2.start();
        t3.start();
        //在所有的线程执行完毕之后才会执行打印
        t1.join();
        t2.join();
        t3.join();

        long endTime = System.currentTimeMillis();


        //如果没有加join()，endTime的时间以t1线程执行完为准
        System.out.printf("Save data begin timestamp is:%s,end timestamp is:%s",startTime,endTime);
    }
}

class CaptureRunaable implements Runnable{
    private String machineName;

    private long spendTime;

    public CaptureRunaable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        //do the really capture data.
        try {
            Thread.sleep(spendTime);
            System.out.println(machineName+" completed data capture and successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getResult(){
        return machineName+" finish.";
    }
}