package com.hzm.concurrent.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟APP客户端
 */
public class AppServer extends Thread{
    //默认端口号
    private int port;
    private static final int DEAFULT_PORT=12722;
    //
    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    //线程池
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    //
    private ServerSocket server;

    public AppServer(){
        this(DEAFULT_PORT);
    }
    public AppServer(int port){
        this.port=port;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            while (start){
                //启动
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.dispose();
        }
    }

    //关闭
    private void dispose() {
        //逐个关闭
        clientHandlers.stream().forEach(ClientHandler::stop);
        this.executor.shutdown();
    }

    public void shutdown() throws IOException {
        this.start=false;
        this.interrupt();
        this.server.close();
    }
}
