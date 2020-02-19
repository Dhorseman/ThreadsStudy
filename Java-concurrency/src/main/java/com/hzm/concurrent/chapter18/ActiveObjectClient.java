package com.hzm.concurrent.chapter18;

public class ActiveObjectClient {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakeClientThread(activeObject,"HZM").start();
        new MakeClientThread(activeObject,"Lucy").start();

        new DisplayClientThread("Tom",activeObject).start();
    }
}
