package com.mycoding.javabase.concurrent.locks.eg1;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/9/17 17:54
 */
public class CodeBlock {
    public static void main(String[] args) {
        /** 1：第一种情况 同一个对象*/
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();

        /** 1：第二种情况 不同对象*/
        //Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
        //Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
        //thread1.start();
        //thread2.start();
    }
}

/**
 * 同步线程
 */
class  SyncThread implements Runnable {
    private static Integer count;

    public SyncThread() {
        count = 0;
    }

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }
}
