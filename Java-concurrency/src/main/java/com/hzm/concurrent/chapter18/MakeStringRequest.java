package com.hzm.concurrent.chapter18;

/**
 * 针对ActiveObject中的MakeString()创建了单独的请求类
 * {@link ActiveObject#makeString(int, char)}
 *
 */
public class MakeStringRequest extends MethodRequest {
    private final int count;
    private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        //在servant中有具体任务的实现
        Result result = servant.makeString(count, fillChar);
        //通过Future设计模式，得到结果后返回
        futureResult.setResult(result);
    }
}
