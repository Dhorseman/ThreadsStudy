package com.hzm.concurrent.chapter18;

/**
 * 返回值的存放处
 */
public class FutureResult implements Result {

    private Result result;
    //任务完成标识
    private boolean ready = false;
    //设置结果
    public synchronized void setResult(Result result){
        this.ready=true;
        this.result=result;
        this.notifyAll();
    }

    @Override
    public synchronized Object getResultValue() {
        while (!ready){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }

}
