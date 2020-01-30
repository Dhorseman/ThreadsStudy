package com.hzm.concurrent.chapter4;

import java.util.Arrays;

public class ThreadLifeCyclClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
