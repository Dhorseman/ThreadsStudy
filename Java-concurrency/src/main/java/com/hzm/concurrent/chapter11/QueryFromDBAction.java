package com.hzm.concurrent.chapter11;

/**
 * 模拟执行业务的线程
 */
public class QueryFromDBAction {

    public void execute(Context context){
        try {
            Thread.sleep(1000L);
            String name = "HZM" + Thread.currentThread().getName();
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
