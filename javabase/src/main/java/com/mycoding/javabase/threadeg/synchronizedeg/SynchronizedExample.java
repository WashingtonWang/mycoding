package com.mycoding.javabase.threadeg.synchronizedeg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/12 15:02
 */
public class SynchronizedExample {

    /**
     * synchronized 作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
     */
    public static void main(String[] args) {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        SynchronizedExample e3 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func2());
        executorService.execute(() -> e2.func2());
        executorService.execute(() -> e3.func2());

        executorService.shutdown();
    }

    public void func2() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }
}
