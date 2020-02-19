package com.hzm.concurrent.chapter18;

/**
 * 方法的实现
 * 接收到请求之后，真正执行方法的类
 * 模拟有返回值和无返回值
 *
 * 通常情况下 Servant为包可见，外部无法创建Servant的实例，通过factory创建
 * factory中创建好Servant和ActivationQueue并交给SchedulerThread，SchedulerThread再交给ActiveObjectProxy
 */
public class Servant implements ActiveObject {

    //将char拼接成指定数字的char数组，再转换成String放入Result中后返回
    @Override
    public Result makeString(int count, char fillChar) {
        char[] buf = new char[count];
        for (int i =0;i<count;i++){
            buf[i]=fillChar;
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
        }
        return new RealResult(new String(buf));
    }

    @Override
    public void displayString(String text) {
        try {
            System.out.println(text);
            Thread.sleep(10_000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
