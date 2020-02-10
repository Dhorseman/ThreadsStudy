package com.hzm.concurrent.chapter11;

import java.util.stream.IntStream;

public class ContextTest {
    public static void main(String[] args) {
        //
        IntStream.range(1, 5).forEach(i -> {
            new Thread(new ExecutionTask2()).start();
        });
    }
}
