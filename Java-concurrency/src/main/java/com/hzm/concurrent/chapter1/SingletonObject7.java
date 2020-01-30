package com.hzm.concurrent.chapter1;


import java.util.stream.IntStream;

/**
 * 思路三：使用枚举
 * 保证懒加载，性能好，安全
 */
public class SingletonObject7 {

    private SingletonObject7(){

    }

    private enum Singleton{
        INCETANCE;
        private final SingletonObject7 instance;

        Singleton(){
            instance = new SingletonObject7();
        }
        public SingletonObject7 getInstance(){
            return instance;
        }

    }
    public static SingletonObject7 getInstance(){
        return Singleton.INCETANCE.getInstance();
    }

    /**
     * 简单的测试
     * @param args
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i->new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(SingletonObject7.getInstance());
            }
        }.start());
    }
}
