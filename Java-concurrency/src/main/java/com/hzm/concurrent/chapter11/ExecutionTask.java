package com.hzm.concurrent.chapter11;

/**
 * ThreadLocal的使用场景
 * 模拟一个并发时处理任务的线程
 * 当一个程序执行分为多个阶段，后一个阶段需要用到前一个阶段的返回结果
 * 这样会将一个实例（这里是context）作为参数反复传递，来拼装数据
 * 在多线程情况下，有什么更好的方法保证实例的安全性，答案当然是ThreadLocal
 */
public class ExecutionTask implements Runnable{

    //模拟，真正调用执行任务的类，查询出姓名
    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();
    //模拟，真正调用执行任务的类，查询出cardId
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    //不一定会把真正的业务逻辑写在其中
    @Override
    public void run() {
        final Context context = new Context();
        //模拟从数据库中查询数据
        queryFromDBAction.execute(context);
        //模拟日志
        System.out.println("The Name Query Successful");
        httpAction.execute(context);
        System.out.println("The CardId Query Successful");
        System.out.println("The User Name Is "+context.getName()+"And CardId Is "+context.getCardId());
    }
}
