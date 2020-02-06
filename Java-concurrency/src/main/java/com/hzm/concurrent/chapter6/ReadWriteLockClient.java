package com.hzm.concurrent.chapter6;

public class ReadWriteLockClient {

    public static void main(String[] args) {
        final SharedData data = new SharedData(10);
        new ReadWoker(data).start();
        new ReadWoker(data).start();
        new ReadWoker(data).start();
        new ReadWoker(data).start();
        new ReadWoker(data).start();
        new WriteWoker(data,"sadadasfcasdefcas").start();
        new WriteWoker(data,"SADADASFCASDEFCAS").start();

    }
}
