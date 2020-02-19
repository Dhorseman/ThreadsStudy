package com.hzm.concurrent.chapter18;

/**
 * 对应ActiveObject的每一个方法
 * 使用comend设计模式
 * 执行对应请求的方法
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }


    public abstract void execute();
}
