package com.hzm.concurrent.chapter17;

import java.util.Random;

/**
 * 模拟往生产线上传送商品
 */
public class TransportThread extends Thread{

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try{
            for (int i = 0;true;i++){
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (Exception e){

        }
    }
}
