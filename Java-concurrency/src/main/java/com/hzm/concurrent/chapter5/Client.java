package com.hzm.concurrent.chapter5;

public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("BaoBao","BeiJing",gate);
        User sh = new User("ShangLao","ShangHai",gate);
        User gz = new User("GuangLao","GuangZhou",gate);
        bj.start();
        sh.start();
        gz.start();
    }

}
