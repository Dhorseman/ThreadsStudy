package com.hzm.concurrent.chapter4;

public interface LifeCycleListener {

    void onEvent(ObserverRunnable.RunnableEvent event);
}
