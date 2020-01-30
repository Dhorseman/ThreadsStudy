package com.hzm.concurrent.chapter5;

/**
 * 使用User模拟线程
 */
public class User extends Thread{
    private String myName;
    private String myAddress;
    private final Gate gate;

    public User(String myName,String myAddress,Gate gate){
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }
    //模拟线程使用资源
    @Override
    public void run() {
        System.out.println(myName+"BEGIN");
        while (true) {
            this.gate.pass(myName,myAddress);
        }
    }
}
