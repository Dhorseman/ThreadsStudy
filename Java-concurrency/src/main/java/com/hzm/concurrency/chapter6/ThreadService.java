package com.hzm.concurrency.chapter6;

/**
 * 场景：在多线程情况下，如果某个线程超时且阻塞，无法执行到设定好的关闭代码时，如何将线程关闭？
 * 将需要执行的线程定义为一个守护线程，而主线程的作用相当于倒计时，时间结束如果主线程退出，守护线程也就退出
 */
public class ThreadService {
    //执行线程
    private Thread executeThread;
    //判断是否执行结束
    private boolean finished =false;
    //接收一个任务
    public void execute (Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {
                //创建一个守护线程
                Thread runner = new Thread(task);
                runner.setDaemon(true);

                runner.start();
                try {
                    //避免执行线程结束时守护线程还没有启动，这里进行阻塞等待守护线程执行完，保证主线程的生命周期
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }
    //在指定时间将线程shutdown
    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        //判断是否执行结束，如果提前结束就提前shutdown
        while (!finished){
            //任务执行时间大于指定时间时
            if ((System.currentTimeMillis()-currentTime)>=mills){
                System.out.println("任务超时，需要结束");
                break;
            }
            //任务没有超时，但也没有执行结束
            try {
                executeThread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished =false;
    }
}
