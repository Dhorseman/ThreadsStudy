package com.hzm.concurrent.chapter17;

public class WorkClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Hzm",channel).start();
        new TransportThread("Brad",channel).start();
        new TransportThread("Tom",channel).start();
        new TransportThread("Pitt",channel).start();
    }
}
