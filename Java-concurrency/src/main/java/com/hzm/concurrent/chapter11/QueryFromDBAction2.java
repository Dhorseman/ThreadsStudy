package com.hzm.concurrent.chapter11;

/**
 * 模拟执行业务的线程
 * 调整后
 */
public class QueryFromDBAction2 {

    public void execute(){
        try {
            Thread.sleep(1000L);
            String name = "HZM" + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
