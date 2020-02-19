package com.hzm.concurrent.chapter17;


import java.util.Arrays;

/**
 * 多线程之WorkerThread设计模式
 * 模拟生产流水线传送带，流水线告诉工人什么时候产品生产好了，什么时候需要等待
 * 通过队列实现(也可以通过LinkedList实现)
 */
public class Channel {
    //流水线上最多可以放置的产品数量
    private final static int MAX_REQUEST = 100;
    //产品
    private final Request[] requestQueue;
    //队列头部
    private int head;
    //队列尾部
    private int tail;
    //当前有多少个
    private int count;
    //
    private final WorkerThread[] workerPool;

    public Channel(int wokers){
        this.requestQueue=new Request[MAX_REQUEST];
        this.head=0;
        this.tail=0;
        this.count=0;
        this.workerPool=new com.hzm.concurrent.chapter17.WorkerThread[wokers];
        this.init();
    }

    //初始化，构造线程
    private void init() {
        for (int i = 0;i<workerPool.length;i++){
            workerPool[i]=new WorkerThread("Worker-"+i,this);
        }
    }
    //工作开关
    public void startWorker(){
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }
    //将产品放入流水线中
    public synchronized void put(Request request){
        //如果队列满了则wait()
        while (count>=requestQueue.length){
            try{
                this.wait();
            }catch (Exception e){

            }
        }
        //从tail放入，从head取出
        this.requestQueue[tail]=request;
        this.tail=(tail+1)%requestQueue.length;
        this.count++;
        this.notifyAll();
    }
    //从流水线中取出产品
    public synchronized Request take(){
        while (count<=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = this.requestQueue[head];
        this.head=(this.head+1)%this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
