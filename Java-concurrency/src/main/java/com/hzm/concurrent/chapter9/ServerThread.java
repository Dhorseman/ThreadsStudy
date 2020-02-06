package com.hzm.concurrent.chapter9;

import java.util.Random;

public class ServerThread extends Thread{

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag){
            Request request = queue.getRequest();
            //被打断时返回null
            if (null==request){
                System.out.println("Received the empty request.");
                //continue后会重新判断while条件
                continue;
            }
            System.out.println("Server -> "+request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                //接收打断信号并return
                return;
            }
        }
    }
    //模拟关闭服务接收
    public void close(){
        this.flag = true;
        //只是改变flag可能会导致在wait()的线程接收不到，所以需要使用打断方法
        this.interrupt();
    }
}
