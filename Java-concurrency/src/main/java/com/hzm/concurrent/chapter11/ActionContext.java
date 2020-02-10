package com.hzm.concurrent.chapter11;

public final class ActionContext {
    //保证要修改的context唯一
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>(){
        //设置初始值
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    //单例模式创建出ActionContext
    private static class ContextHolder{
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext(){
        return ContextHolder.actionContext;
    }
    //拿到唯一的context对象
    public Context getContext(){
        return  threadLocal.get();
    }
}
