package com.hzm.concurrent.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟threadlocal工作原理：
 * 始终以当前线程作为key值
 */
public class ThreadLocalSimulater<T> {

    private final Map<Thread,T> storage = new HashMap<>();

    public void set(T t){
        synchronized (this){
            Thread key = Thread.currentThread();
            storage.put(key,t);
        }
    }

    public T get(){
        synchronized (this){
            Thread key  = Thread.currentThread();
            T value = storage.get(key);
            if (value==null){
                return initialValue();
            }
            return value;
        }
    }

    //返回默认值
    public T initialValue() {
        return null;
    }

}
