package com.mycoding.javabase.threadeg.deadlock;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/31 08:41
 *
 * 方式二（在synchronized代码块中锁定不同的对象）
 */
public class DeadLockTwoTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread2 thread1 = new MyThread2();

        new Thread(thread1, "threadA").start();

        Thread.sleep(5);

        new Thread(thread1, "threadB").start();
    }
}

class MyThread2 implements Runnable {
    private final String[] lock = {"A", "B"};

    private void intoA(String threadName, int index) {
        System.out.println("线程" + threadName + "尝试进入intoA");
        synchronized (lock[index]) {
            System.out.println("线程" + threadName + "进入intoA");
            sleep(200);
            intoB(threadName, 1);
        }
    }

    private void intoB(String threadName, int index) {
        System.out.println("线程" + threadName + "尝试进入intoB");
        synchronized (lock[index]) {
            System.out.println("线程" + threadName + "进入intoB");
            sleep(200);
            intoA(threadName, 0);
        }
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if ("threadA".equals(threadName)) {
            intoA(threadName, 0);
        } else {
            intoB(threadName, 1);
        }
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
