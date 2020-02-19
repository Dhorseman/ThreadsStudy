package com.hzm.concurrent.chapter18;

/**
 * 将请求放入ActivationQueue中
 */
public class SchedulerThread extends Thread{
    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    /**
     * 关键方法 将请求放入请求队列中
     * @param request
     */
    public void invoke(MethodRequest request){
        this.activationQueue.put(request);

    }

    //运行时不断从队列中拿出
    @Override
    public void run() {
        while (true){
            //真正执行的是servant类
            activationQueue.take().execute();

        }
    }
}
