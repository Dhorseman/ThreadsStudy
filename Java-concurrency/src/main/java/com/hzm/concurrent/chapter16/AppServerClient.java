package com.hzm.concurrent.chapter16;

import java.io.IOException;

/**
 * 关注点：主动从服务端关闭连接和从客户端被动关闭连接时都需要有相应的处理
 */
public class AppServerClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer appServer = new AppServer(12345);
        appServer.start();
        Thread.sleep(45_000L);
        //主动关闭
        appServer.shutdown();

    }
}
