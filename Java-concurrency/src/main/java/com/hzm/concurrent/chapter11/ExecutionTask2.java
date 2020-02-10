package com.hzm.concurrent.chapter11;

/**
 * ThreadLocal的使用场景
 * 模拟一个并发时处理任务的线程
 * 调整后不再将context作为参数传递
 * 需要注意的点是，如果有新的任务时，需要将context中的数据进行清空，否则会产生数据遗留问题
 */
public class ExecutionTask2 implements Runnable{

    //模拟，真正调用执行任务的类，查询出姓名
    private QueryFromDBAction2 queryFromDBAction = new QueryFromDBAction2();
    //模拟，真正调用执行任务的类，查询出cardId
    private QueryFromHttpAction2 httpAction = new QueryFromHttpAction2();

    //不一定会把真正的业务逻辑写在其中
    @Override
    public void run() {
        final Context context = ActionContext.getActionContext().getContext();
        //模拟从数据库中查询数据
        queryFromDBAction.execute();
        //模拟日志
        System.out.println("The Name Query Successful");
        httpAction.execute();
        System.out.println("The CardId Query Successful");
        System.out.println("The User Name Is "+context.getName()+"And CardId Is "+context.getCardId());
    }
}
