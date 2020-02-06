package com.hzm.concurrent.chapter6;

public class ReadWoker extends Thread {
    private final SharedData data;

    public ReadWoker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName()+" reads "+String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
