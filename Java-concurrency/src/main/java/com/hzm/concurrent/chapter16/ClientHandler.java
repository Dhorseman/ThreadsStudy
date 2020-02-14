package com.hzm.concurrent.chapter16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 模拟客户端
 */
public class ClientHandler implements Runnable {
    //一个线程负责一个客户端的连接
    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
            //读的时候逐行读
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter printWriter = new PrintWriter(outputStream);){

            while (running) {
                String message = br.readLine();
                if (message==null){
                    break;
                }
                System.out.println("Come from client -> "+message);
                printWriter.write("echo "+message+"\n");
                printWriter.flush();
            }
        } catch (IOException e) {
            this.running=true;
        }finally {
            //如果客户端断开，进行清理
            this.stop();
        }
    }

    //用到balking设计模式
    public void stop() {
        //stop时判断线程是否已经关闭
        if (!running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
