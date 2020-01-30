package com.hzm.concurrency.chapter1;

public class TryConcurrency {

    /**
     * 在main中创建一个线程，使得两个线程同时运行
     * @param args
     */
    public static void main(String[] args) {

        //第一种创建线程的方式
        Thread t1 = new Thread("Custom-Thread"){
            @Override
            public void run() {
                for (int i = 0;i<1000;i++){
                    println("Task 1=>"+i);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {


                    }
                }
            }
        };
        //关键步骤：不调用start()，只能是一个线程实例，该方法会立即返回不会阻塞
        t1.start();

        for (int i = 0;i<1000;i++){
            println("Task 2=>"+i);
        }




//        readFromDataBase();
//        writeDataToFile();
    }

    public static void readFromDataBase(){
        //read data from database and handle it.
        try {
            println("Begin read data from db.");
            Thread.sleep(1000*10l);
            println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully");
    }

    public static void writeDataToFile(){
        //write data from database and handle it.
        try {
            println("Begin write data to file.");
            Thread.sleep(2000*10l);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully");
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
