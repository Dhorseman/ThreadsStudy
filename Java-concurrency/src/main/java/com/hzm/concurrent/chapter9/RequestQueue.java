package com.hzm.concurrent.chapter9;

import java.util.LinkedList;

/**
 *  模拟请求队列，当请求到达时，如果不能及时处理则放在请求队列中
 */
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();
    //从队列中取出
    public Request getRequest(){
        synchronized (queue){
            //没有数据则等待
            while (queue.size()<=0){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    //接收到打断信号后返回null
                    return  null;
                }
            }
            return queue.removeFirst();
        }
    }
    //放入队列中
    public void putRequest(Request request){
        synchronized (queue){
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
