package com.hzm.concurrent.chapter5;

/**
 * 单线程执行设计模式
 * 当线程通过时记录线程的相关信息
 * 将Gate模拟为共享资源
 */
public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 临界值
     */
    public synchronized void pass(String name,String address){
        this.counter++;
        //多线程情况下会对这两个值竞争
        this.name = name;
        this.address=address;
        verify();
    }
    //模拟校验，读操作，在pass()加锁后也会一同进行加锁，但是对读操作加锁会导致性能变差，如何将读写锁分离
    private void verify() {
        if (this.name.charAt(0)!=this.address.charAt(0)){
            System.out.println("*******BROKEN*******"+toString());
        }
    }
    //读操作，在pass()加锁后也会一同进行加锁
    public String toString(){
        return "No."+counter+": "+name+", "+address;
    }
}
