package com.hzm.concurrent.chapter18;

import java.util.LinkedList;

/**
 * 请求队列
 * 当有请求调用主动对象中的方法时，外部组装好Requet之后（DisplayStringRequest/MakeStringRequest）
 * 将Request放入队列中
 */
public class ActivationQueue {
    //队列size限制
    private final static int MAX_METHOD_REQUEST_SIZE=100;
    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        this.methodQueue = new LinkedList<>();
    }
    //将请求加入队列
    public synchronized void put(MethodRequest request){
        //队列满了则等待
        while (methodQueue.size()>=MAX_METHOD_REQUEST_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(request);
        this.notifyAll();
    }
    public synchronized MethodRequest take(){
        while (methodQueue.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
