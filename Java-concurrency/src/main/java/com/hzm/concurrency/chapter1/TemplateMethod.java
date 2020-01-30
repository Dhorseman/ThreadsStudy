package com.hzm.concurrency.chapter1;

/**
 * 这个例子是模拟thread中提供的start()方法；
 * start()提供一个线程，让用户选择性复写run()方法
 * 这里也运用了模板方法的技巧
 */
public class TemplateMethod {
    public final void print(String message){
        System.out.println("##############");
        wrapPrint(message);
        System.out.println("##############");
    }

    protected void wrapPrint(String message) {
    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }
        };
        t1.print("HelloThread");
        TemplateMethod t2 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+"+message+"+");
            }
        };
        t2.print("HelloThread");
    }
}
