package com.hzm.concurrent.chapter12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Balking设计模式：假设你在餐厅点餐，向服务员招手，远处的服务员看到朝你走来，但是离你近的服务员也看到了，这时离你近的来处理你的需求，离你远的继续做它的事情
 * 将服务员换成线程就是这个设计模式所要解决的问题
 * 这里模拟写入文件，当有新的内容时则写入，没有变化则不会执行写操作
 */
public class BalkingData {
    //文件名
    private final String fileName;
    //写入内容
    private String content;
    //改动标识
    private boolean changed;
    public BalkingData(String fileName,String content){
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    /**
     * 这里change和save需要配对出现，因为如果change()连续执行了两到三次，那么中间的数据有可能会失踪，所以change
     */

    //主动发生改动
    public synchronized void change(String newContent){
        this.content = newContent;
        this.changed = true;
    }
    //轮询检测是否改动
    public synchronized void save() throws IOException {
        if (!changed){
            return;
        }
        //有改动时进行存储
        doSace();
        this.changed=true;
    }

    private void doSace() throws IOException {
        System.out.println(Thread.currentThread().getName()+" calls do save,content is :"+content);
        try(Writer writer = new FileWriter(fileName,true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
