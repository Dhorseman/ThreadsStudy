package com.hzm.concurrent.chapter9;

public class SuspensionClient {
    public static void main(String[] args) {
        //通过一个请求队列，实现发送和接收
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue,"Alex").start();
        new ServerThread(queue).start();

    }
}
