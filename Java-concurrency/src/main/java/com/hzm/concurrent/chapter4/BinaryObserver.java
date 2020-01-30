package com.hzm.concurrent.chapter4;


/**
 * 二进制观察者
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        super(subject);    }
        //观察者根据自己情况 复写通知方法
    @Override
    public void update() {
        System.out.println("Binary String:"+Integer.toBinaryString(subject.getState()));
    }
}
