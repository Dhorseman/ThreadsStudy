package com.hzm.concurrent.chapter18;

/**
 * 针对ActiveObject中的DisplayString()创建了单独的请求类
 * {@link ActiveObject#displayString(String)}
 */
public class DisplayStringRequest extends MethodRequest {

    private final String text;

    //当不需要future设计模式时设置为null即可
    public DisplayStringRequest(Servant servant, final String text) {
        super(servant,null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
