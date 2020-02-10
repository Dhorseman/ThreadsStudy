package com.hzm.concurrent.chapter13;

import com.sun.xml.internal.ws.api.model.MEP;

public class ProducerAndConsumerClient {
    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue();
        new ProducerThreads(messageQueue,1).start();
        new ProducerThreads(messageQueue,2).start();
        new ProducerThreads(messageQueue,3).start();
        new ConsumerThread(messageQueue,1).start();
        new ConsumerThread(messageQueue,2).start();
        new ConsumerThread(messageQueue,3).start();
    }
}
