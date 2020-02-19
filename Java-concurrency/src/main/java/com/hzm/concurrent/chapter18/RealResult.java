package com.hzm.concurrent.chapter18;


public class RealResult implements Result {
    private final Object resultValue;

    public RealResult(final Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return this.resultValue;
    }
}
