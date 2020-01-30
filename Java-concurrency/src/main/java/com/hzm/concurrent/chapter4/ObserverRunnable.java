package com.hzm.concurrent.chapter4;

/**
 * 如何监控一个线程的状态
 */
public abstract class ObserverRunnable implements Runnable{
    final protected LifeCycleListener listener;
    //构造函数，传入一个监听器
    public ObserverRunnable(final LifeCycleListener listener){
        this.listener=listener;
    }
    //回调函数
    protected void notifyChange(final RunnableEvent event){
        listener.onEvent(event);
    }
    //线程状态
    public enum RunnableState{
        RUNNING,ERROR,DONE;
    }
    //内部类
    public static class RunnableEvent{
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
