package com.hzm.concurrent.chapter4;

import java.util.List;

public class ThreadLifeCycleObserver implements LifeCycleListener {
    //考虑到多线程会有共享数据问题，定义一个锁
    private final Object LOCK = new Object();
    //批量查询
    public void concurrentQuery(List<String> ids){
        if (ids==null||ids.isEmpty()){
            return;
        }
        //循环传入
        ids.stream().forEach(id->new Thread(new ObserverRunnable(this){
            @Override
            public void run() {
                //记录线程的每个阶段
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                    System.out.println("query for the id"+id);
                    Thread.sleep(1000l);
                    int x = 1/0;
                    notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
                }catch (Exception e){
                    notifyChange(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),e));
                }
            }
        },id).start());
    }
    //回调函数
    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("The runnable ["+event.getThread().getName()+"] data change and state is [ "+event.getState()+" ]");
            //如果失败了，创建一个线程尝试去恢复
            if (event.getCause()!=null){
                System.out.println("The runnable ["+event.getThread().getName()+"] process failed.");
                event.getCause().printStackTrace();
            }
        }
    }
}
