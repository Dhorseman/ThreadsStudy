package com.hzm.concurrent.chapter18;

/**
 * 主动对象的代理类，帮助完成方法的调用，在工厂中传入一个SchedulerThread和Servant
 */
public class ActiveObjectProxy implements ActiveObject{

    private final SchedulerThread schedulerThread;

    private final Servant servant;

    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }



    @Override
    public Result makeString(int count, char fillChar) {
        FutureResult futureResult = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant,futureResult,count,fillChar));
        return futureResult;
    }

    @Override
    public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant,text));
    }
}
