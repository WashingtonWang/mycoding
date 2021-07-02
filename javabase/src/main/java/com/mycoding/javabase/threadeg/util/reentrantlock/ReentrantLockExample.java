package com.mycoding.javabase.threadeg.util.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/12 15:07
 */
public class ReentrantLockExample {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockExample lockExample = new ReentrantLockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> lockExample.func());
        executorService.execute(() -> lockExample.func());
    }

    public void func(){
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        } finally {
            lock.unlock();
        }
    }
}
