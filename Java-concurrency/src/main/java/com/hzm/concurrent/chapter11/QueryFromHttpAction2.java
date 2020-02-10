package com.hzm.concurrent.chapter11;

public class QueryFromHttpAction2 {
    //根据姓名获得身份证号
    public void execute(){
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String id = getCardId(name);
        context.setCardId(id);
    }

    //模拟restful获取id数据
    private String getCardId(String name){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "12345678910111213"+Thread.currentThread().getId();
    }
}
