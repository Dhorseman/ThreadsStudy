package com.hzm.concurrent.chapter7;

/**
 * 测试不可变对象的性能
 */
public class ImmutablePerformance {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        SyncObj syncObj = new SyncObj();
//        syncObj.setName("HZM");
        ImmutableObj immutableObj = new ImmutableObj("HZM");
        for (long i = 0L; i < 10000000; i++) {
            System.out.println(immutableObj.toString());
        }
        long endTime =System.currentTimeMillis();
        System.out.println("Elapsed time "+(endTime-startTime));
    }
}
class ImmutableObj{
    private final String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "["+name+"]";
    }
}
class SyncObj{
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "["+name+"]";
    }
}
