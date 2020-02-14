package com.hzm.concurrent.chapter13;

import java.nio.file.Path;
import java.util.LinkedList;

public class MessageQueue {
    //信息队列
    private final LinkedList<Message> queue;
    //队列最大长度
    private final static int DEFAULT_MAX_LIMIT=100;

    private final int limit;

    public MessageQueue(){
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit){
        this.limit=limit;
        this.queue=new LinkedList<>();
    }
    //将消息放入消息队列中
    public void put(Message message) throws InterruptedException {
        synchronized (queue){
            while (queue.size()>limit){
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }
    //从消息队列中获取消息
    public Message take() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }
    public int getMaxLimit(){
        return this.limit;
    }
    public int getMessageSize(){
        //queue的大小可能会改动所以需要加锁
        synchronized (queue){
            return queue.size();
        }
    }
}
