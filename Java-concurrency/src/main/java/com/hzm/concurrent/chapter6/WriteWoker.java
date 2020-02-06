package com.hzm.concurrent.chapter6;

import java.util.Random;

public class WriteWoker extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private final SharedData sharedData;

    private final String fillter;

    private int index = 0;

    public WriteWoker(SharedData sharedData, String fillter) {
        this.sharedData = sharedData;
        this.fillter = fillter;
    }

    @Override
    public void run() {
        try {
            while (true){
                char c = nextChar();
                sharedData.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private char nextChar(){
        char c  = fillter.charAt(index);
        index++;
        if (index>=fillter.length()){
            index=0;
        }
        return c;
    }
}
