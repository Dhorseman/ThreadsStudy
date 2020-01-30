package com.hzm.concurrency.chapter2;

/**
 * 模拟银行叫号业务
 */
public class TicketWindow extends Thread{

    private final String name;

    private final int Max = 50;
    //使用static实例化一次，但是static存在一个问题：随着JVM加载该类之后static修饰的信息会一直存在，即使类被销毁了也可能一直存在
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index<=Max){
            System.out.println("Tbale Name is :"+name+"Current number is :"+(index++));
        }
    }
}
