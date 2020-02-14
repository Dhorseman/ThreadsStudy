package com.hzm.concurrent.chapter16;

import java.util.Random;

/**
 * 线程清理设计模式(Two Phase Termination)
 */
public class CounterIncrement extends Thread{
    private volatile boolean terminated = false;

    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try{
            while (!terminated){
                System.out.println(Thread.currentThread().getName()+" "+counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //
            this.clean();

        }
    }

    /**
     * 在关闭时接收到打断信号后将会执行clean操作
     */
    public void clean(){
        System.out.println("Do some clean work for the second phase.Current counter is "+counter);

    }

    public void close(){
        this.terminated=true;
        this.interrupt();
    }
}
