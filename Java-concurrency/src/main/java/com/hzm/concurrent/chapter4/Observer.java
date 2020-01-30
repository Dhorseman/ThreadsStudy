package com.hzm.concurrent.chapter4;

public abstract class Observer {
    //观察的对象
    protected Subject subject;
    //创建observe实例时将实例传入并添加至观察者集合中
    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
    //抽象方法
    public abstract void update();
}
