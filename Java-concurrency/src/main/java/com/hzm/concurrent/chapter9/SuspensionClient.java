package com.hzm.concurrent.chapter9;

public class SuspensionClient {
    public static void main(String[] args) {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue,"Alex").start();
        new ServerThread(queue).start();

    }
}
