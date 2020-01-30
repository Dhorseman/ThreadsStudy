package com.hzm.concurrency.chapter12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 自制简易线程池
 */
public class SimpleThreadPool extends Thread {
    //线程数量
    private int size;
    //任务队列数量
    private final int queueSize;
    //默认线程队列最大容量
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    //线程id
    private static volatile int seq = 0;
    //线程id前缀
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    //线程组
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");
    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    //存放运行的线程
    private final static List<WorkerTask> THREAD_QUEUE= new ArrayList<>();
    //线程池拒绝策略
    private final DiscardPolicy discardPolicy;
    //线程销毁状态
    private volatile boolean destory =false;
    //默认拒绝策略
    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{throw new DiscardException("Discard This Task");};
    //定义线程池的轮转，空闲时的释放多余的线程，线程数量不够时能够自动扩充
    private int min;
    private int max;
    private int active;

    public SimpleThreadPool() {
        this(4,8,12,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min,int active,int max,int queueSize,DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize=queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }
    //线程池初始化
    private void init(){
        //初始化时先初始化min数量的，不够用再扩大
        for (int i =0;i<this.min;i++){
            createWorkTask();
        }
        this.size=min;
        this.start();
    }

    //监控线程池中线程数量，及时调控
    @Override
    public void run() {
        while (!destory){
            System.out.printf("Pool#Min:%d,Max:%d,Current:%d,QueueSize:%d\n",this.min,this.max,this.active,TASK_QUEUE.size());
            try {
                Thread.sleep(5000L);
                //扩充判断
                if (TASK_QUEUE.size()>active && size<active){
                    for (int i = size;i<active;i++){
                        createWorkTask();
                    }
                    System.out.println("The Pool Incremented To Active.");
                    size =active;
                }else if (TASK_QUEUE.size()>max && size<max){
                    for (int i = size;i<max;i++){
                        createWorkTask();
                    }
                    System.out.println("The Pool Incremented To Max.");
                    size =max;
                }

                //回收判断
                if (TASK_QUEUE.isEmpty()&& size>active){
                    synchronized (TASK_QUEUE){
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator();it.hasNext();){
                            if (releaseSize<=0)
                                break;
                            WorkerTask task = it.next();
                            //当线程状态为阻塞时关闭
                        if (TaskState.BLOCK!=task.getTaskState()) {
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        }
                    }

                    for (int i = size;i<active;i++){
                        createWorkTask();
                    }
                    System.out.println("The Pool Incremented.");
                    size =active;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //创建线程并启动
    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP,THREAD_PREFIX+(seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }
    //停止线程方法,执行完所有任务后停止
    public void shutdown() throws InterruptedException {
        synchronized (THREAD_QUEUE) {
            while (!TASK_QUEUE.isEmpty()) {
                //模拟等待任务执行完成
                Thread.sleep(50);
            }
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    //只有当THREAD_QUEUE为空时才会是阻塞状态
                    if (task.getTaskState() == TaskState.BLOCK) {
                        //将线程打断,结束线程生命周期
                        task.interrupt();
                        //调用close()更改状态
                        task.close();
                        initVal--;
                    } else {
                        //如果关闭失败，则休息一下
                        Thread.sleep(100);
                    }
                }
            }
        }
        this.destory = true;
        System.out.println("The thread pool disposed.");
    }
    //与拒绝策略搭配使用异常
    public static class DiscardException extends RuntimeException{
        public DiscardException(String message){
            super(message);
        }
    }
    //拒绝策略:若超过最大任务队列数时处理方法
    public interface DiscardPolicy{
        void discard() throws DiscardException;
    }
    //定义枚举
    private enum TaskState{
        FREE,RUNNING,BLOCK,DEAD
    }
    //自己封装的Thread
    private static class WorkerTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;
        public TaskState getTaskState(){
            return this.taskState;
        }
        public WorkerTask(ThreadGroup threadGroup,String name){
            super(threadGroup,name);
        }
        public void run(){
            OUTER:
            //只要状态不为死亡就代表run
            while (this.taskState!=TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    //如果没有人提交，线程为空则等待
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            taskState=TaskState.BLOCK;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    //队列为先进先出
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable!=null){
                    taskState=TaskState.RUNNING;
                    runnable.run();
                    taskState=TaskState.FREE;
                }
            }
        }
        public void close(){
            this.taskState = TaskState.DEAD;
        }
    }
    //暴露给对外的接口，返回数量
    public int getSize() {
        return size;
    }
    public int getQueueSize() {
        return queueSize;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public int getActive() {
        return active;
    }

    //查看线程是否被销毁
    public boolean isDestory(){
        return this.destory;
    }
    //传入任务到线程
    public void submit(Runnable runnable){
        //如果线程被销毁不能提交任务
        if (destory){
            throw new IllegalStateException("The thread pool already destory and not allow submit task");
        }
        synchronized (TASK_QUEUE){
            //执行拒绝策略
            if (TASK_QUEUE.size()>queueSize){
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            //唤醒等待的线程
            TASK_QUEUE.notifyAll();
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        for (int i = 0;i<40;i++){
            threadPool.submit(()->{
                System.out.println("The runnable be serviced by "+Thread.currentThread()+" start.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by "+Thread.currentThread()+" finished.");
            });
        }

//        IntStream.rangeClosed(0,40).forEach(i->{
//            threadPool.submit(()->{
//                System.out.println("The runnable "+i+" be serviced by "+Thread.currentThread()+" start.");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("The runnable "+i+" be serviced by "+Thread.currentThread()+" finished.");
//            });
//        });
    }
}
