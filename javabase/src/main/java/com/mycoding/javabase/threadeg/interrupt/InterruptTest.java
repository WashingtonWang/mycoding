package com.mycoding.javabase.threadeg.interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/4/26 23:05
 * <p>
 * 原文链接：https://blog.csdn.net/qq_35634181/article/details/106769113
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        //test3();
        test4();
    }


    /*
    执行结果：
    主线程正在执行
    主线程等待，睡眠两秒
    线程一正在执行，将进入等待状态，当前时间 = 1592271902514, 此时的中断标志位：false
    线程一从等待状态中醒来，当前时间 = 1592271904515, 此时的中断标志位：true
    线程一从等待状态中醒来，当前时间 = 1592271904515, 此时的中断标志位：true

     可以看到当线程一调用 Thread.interrupt方法之后，会从线程会从等待状态中醒来，然后第二次调用LockSupport.park时，
     根本就不会再次进入等待状态，所以调用LockSupport.park是不会清除中断标志位的。
    */
    private static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("线程一正在执行，将进入等待状态，当前时间= " + System.currentTimeMillis() + ", 此时的中断标志位：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println("线程一从等待状态中醒来，当前时间= " + System.currentTimeMillis() + ", 此时的中断标志位：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println("线程一从等待状态中醒来，当前时间= " + System.currentTimeMillis() + ", 此时的中断标志位：" + Thread.currentThread().isInterrupted());
        });

        System.out.println("主线程正在执行");
        thread.start();
        System.out.println("主线程等待，睡眠两秒");
        TimeUnit.SECONDS.sleep(2);
        //注意此处  注释与去掉注释  观察结果
        thread.interrupt();
    }

    /**
     * /*
     * 执行结果：
     * 主线程正在执行
     * 主线程等待，睡眠两秒
     * 线程一正在执行，将进入等待状态，时间= 1592272574294,中断标志位：false
     * 线程一从等待状态中醒来，时间= 1592272576295,中断标志位：false
     * 线程一从等待状态中醒来，时间= 1592272581295,中断标志位：false
     * <p>
     * Object.wait()、Thread.join、Thread.sleep方法都是会清除中断标志位，这里就只演示Thread.sleep方法了，
     * TimeUnit.SECONDS.sleep底层是调用Thread.sleep方法。
     */
    private static void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("线程一正在执行，将进入等待状态，时间= " + System.currentTimeMillis() + ",中断标志位：" + Thread.currentThread().isInterrupted());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("第一次中断异常，时间=" + System.currentTimeMillis() + ",中断标志位：" + Thread.currentThread().isInterrupted());
            }
            System.out.println("线程一从等待状态中醒来，时间= " + System.currentTimeMillis() + ",中断标志位：" + Thread.currentThread().isInterrupted());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("第二次中断异常，时间=" + System.currentTimeMillis() + ",中断标志位：" + Thread.currentThread().isInterrupted());
            }
            System.out.println("线程一从等待状态中醒来，时间= " + System.currentTimeMillis() + ",中断标志位：" + Thread.currentThread().isInterrupted());
        });

        System.out.println("主线程正在执行");
        thread.start();
        System.out.println("主线程等待，睡眠两秒");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("interrupt() before 线程一当前中断标志位：" + thread.isInterrupted() + " 时间=" + System.currentTimeMillis());
        thread.interrupt();
        System.out.println("interrupt() after 线程一当前中断标志位：" + thread.isInterrupted() + " 时间=" + System.currentTimeMillis());
    }

    /**
     * synchronized和ReentrantLock不响应中断的demo
     * <p>
     * /*
     * 执行结果：
     * 线程1打算获取锁
     * 线程1打算睡眠
     * 线程2打算获取锁
     * 线程1已经执行完成
     * 线程2的中断标志位 true
     * java.lang.InterruptedException: sleep interrupted
     * at java.lang.Thread.sleep(Native Method)
     * at java.lang.Thread.sleep(Thread.java:340)
     * at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     * at it.cast.basic.thread.interrupt.Test03.lambda$main$1(Test03.java:43)
     * at java.lang.Thread.run(Thread.java:748)
     * InterruptedException 线程2的中断标志位 false
     * 线程2已经执行完成
     * <p>
     * ————————————————
     * 版权声明：本文为CSDN博主「goodluckwj」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_35634181/article/details/106769113
     * <p>
     * 从下面例子可以得到，当线程等待进行synchronized的方法或是代码块，是不会响应中断的，
     * 如果此时调用Thread.interrupt方法是不会有任何方法，但是在阻塞的时候还是会讲中断标志位置位true的，
     * 那么在拿到锁之后，执行到TimeUnit.SECONDS.sleep(5);会迅速抛出一个中断的异常。
     */
    private static void test3() throws InterruptedException {
        //使用线程池进行测试
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Object lock = new Object();

        executorService.execute(() -> {
            System.out.println("线程1打算获取锁");
            synchronized (lock) {
                try {
                    System.out.println("线程1打算睡眠");
                    //sleep不会释放锁
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("线程1异常 InterruptedException. 线程1的中断标志位" + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
                System.out.println("线程1已经执行完成");
            }
        });

        //主线程睡眠1秒，保证线程1已经睡眠了
        TimeUnit.SECONDS.sleep(2);

        Thread thread = new Thread(() -> {
            System.out.println("线程2打算获取锁");
            //线程2会在此被阻塞，因为线程1已经拿到了锁，并抱着锁睡觉了
            //在此中断线程是不会有任何响应的
            synchronized (lock) {
                try {
                    //判断当前线程的中断为
                    System.out.println("线程2的中断标志位 " + Thread.currentThread().isInterrupted());
                    //虽然在synchronized (lock)中不会响应，但是线程的中断标志位还是true，所以执行到此，会响应迟到的中断
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException 线程2的中断标志位 " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
            System.out.println("线程2已经执行完成");
        });

        thread.start();
        //此时中断线程2，测试线程2在等待获取锁的时候会不会响应中断
        thread.interrupt();
    }

    /**
     * ReentrantLock测试中断
     * <p>
     * 执行结果：
     * 线程1打算获取锁
     * 线程1打算睡眠
     * 线程2打算获取锁
     * java.lang.InterruptedException: sleep interrupted
     * at java.lang.Thread.sleep(Native Method)
     * at java.lang.Thread.sleep(Thread.java:340)
     * at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     * at it.cast.basic.thread.interrupt.Test04.lambda$main$1(Test04.java:47)
     * at java.lang.Thread.run(Thread.java:748)
     * 线程1已经执行完成
     * 线程2的中断标志位 true
     * InterruptedException 线程2的中断标志位 false
     * 线程1已经执行完成
     */

    private static void test4() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Lock lock = new ReentrantLock();
        executorService.execute(() -> {
            System.out.println("线程1打算获取锁");
            lock.lock();
            try {
                System.out.println("线程1打算睡眠");
                //sleep不会释放锁
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("executorService.execute Interrupted. ");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("线程1已经执行完成");
        });

        //主线程睡眠1秒，保证线程1已经睡眠了
        TimeUnit.SECONDS.sleep(2);

        Thread thread = new Thread(() -> {
            System.out.println("线程2打算获取锁");
            //在此中断线程是不会有任何响应的
            lock.lock();
            try {
                //判断当前线程的中断为
                System.out.println("线程2的中断标志位 " + Thread.currentThread().isInterrupted());
                //执行到此，会抛出中断异常，虽然这个中断操作是在lock.lock();进行的，但是会在
                //执行sleep的时候响应，就是判断中断标志位
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("InterruptedException 线程2的中断标志位 " + Thread.currentThread().isInterrupted());
            } finally {
                lock.unlock();
            }
            System.out.println("线程2已经执行完成");
        });

        thread.start();
        thread.interrupt();
    }
}
