package com.hzm.concurrency.chapter2;

/**
 * 同时有三个柜台在并发处理事务时，如何做到数据共享？
 * 保证数据共享之后如何保证顺序执行？
 * 如何将业务数据和线程逻辑分离出来
 *
 */
public class Bank {
    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号柜台");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("二号柜台");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("三号柜台");
        ticketWindow3.start();
    }
}
