package com.hzm.concurrent.chapter1;


public class SingletonObject4 {

    private static SingletonObject4 instance;
    private SingletonObject4(){

    }
    /**
     * 对于思路一的改进：doublecheck，改变synchronized位置，做到了严格单例，做到了懒加载，解决了性能问题
     * 但是可能会引起空指针异常，原因：
     * 假设一个线程经过了doublecheck之后创建了一个实例，在堆内存中获得了对应的区域，但是实例中相关的属性并没有能够及时的构造完毕（初始化过程，进行相对应的赋值）就被返回了
     * 此时，另一个线程进行doublecheck发现实例被创建了，就直接将实例拿去使用，这样就有可能导致空指针
     * 这是JVM在编译时进行的指令重排序等优化行为，以及运行时程序计数器进行的优化行为所导致的
     */
    public  static SingletonObject4 getInstance(){

        if (null==instance){
            synchronized (SingletonObject4.class){
                if (null==instance){
                    instance = new SingletonObject4();
                }
            }
        }
        return instance;
    }
}
