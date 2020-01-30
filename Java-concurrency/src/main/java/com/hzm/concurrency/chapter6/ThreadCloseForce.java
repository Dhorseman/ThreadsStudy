package com.hzm.concurrency.chapter6;

/**
 * 模拟业务场景，加载大型资源
 */
public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();
        long startTime = System.currentTimeMillis();
        threadService.execute(()->{
            //load a very heavy resource.
//            while (true){
//
//            }
            //compeleted early
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadService.shutdown(10000);
        long endTime = System.currentTimeMillis();
        System.out.println("total seconds:"+(endTime-startTime));
    }
}
