package com.hzm.concurrent.chapter18;

/**
 * 接受异步消息的主动方法
 */
public interface ActiveObject {

    //调用的时候不能立即得到返回 ，通过future拿到返回结果
    Result makeString(int count,char fillChar);
    //进行展示
    void displayString(String text);

}
