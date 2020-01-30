package com.hzm.concurrency.chapter11;

import java.util.Arrays;
import java.util.Optional;

/**
 * 跟踪打印stack信息
 */
public class Test2 {
    public void test(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //通过getStackTrace()拿到StackTraceElement列表，通过stream()过滤掉native方法，循环打印方法被调用相关信息
        Arrays.asList(Thread.currentThread().getStackTrace()).stream().filter(e->!e.isNativeMethod())
        .forEach(e-> Optional.of(e.getClassName()+":"+e.getMethodName()+":"+e.getLineNumber())
        .ifPresent(System.out::println));
    }
}
