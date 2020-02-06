package com.hzm.concurrent.chapter9;

/**
 * 模拟请求
 */
public class Request {

    final private String value;

    public Request(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
