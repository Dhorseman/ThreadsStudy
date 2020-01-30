package com.hzm.concurrent.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式，被观察对象
 */
public class Subject {
    //观察者集合
    private List<Observer> observers = new ArrayList<>();
    private int state;
    //设置状态，并通知集合中的观察者
    public void setState(int state) {
        if (state==this.state){
            return;
        }
        this.state = state;
        notifyAllObsever();
    }

    public int getState(){
        return this.state;
    }
    //将观察者加入集合中
    public void attach(Observer observer){
        observers.add(observer);
    }
    //通知集合中的观察者
    public void notifyAllObsever(){
        observers.stream().forEach(Observer::update);
    }

}
