package com.hzm.concurrent.chapter7;

import java.util.stream.IntStream;

/**
 * 不可变对象测试：
 * 不可变对象，在多个变量访问时由于没有写操作，没有机会对变量进行改变，故变量是安全的
 * 官方定义：没有set()方法，且所有的属性都是由final和private修饰，不让其他对象继承；举例：String
 */
public class ImmutableClient {
    public static void main(String[] args) {
        //共享数据
        Person person = new Person("Alex", "Gansu");

        IntStream.rangeClosed(0,5).forEach(i->
            new UsePersonThread(person).start()
        );

    }
}
