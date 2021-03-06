package com.mycoding.javabase.concurrent.locks.eg1;

/**
 * @Description: 上面代码中countAdd是一个synchronized的，printCount是非synchronized的。
 * 从上面的结果中可以看出一个线程访问一个对象的synchronized代码块时，别的线程可以访问该对象的非synchronized代码块而不受阻塞。
 * 因为如果两个线程相互形成阻塞，那么对于静态变量count而言应当在线程1的一次循环中递增完毕，对于线程二而言只会只有4一个值，结果推翻了此假设，
 * 所以当一个线程访问对象的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块，互不阻塞。
 * @Auther: wangxiangyu
 * @Date: 2019/9/18 09:16
 */
public class CodeBlock1 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread1 = new Thread(counter, "A");
        Thread thread2 = new Thread(counter, "B");
        thread1.start();
        thread2.start();
    }
}

class Counter implements Runnable{
    private int count;

    public Counter() {
        count = 0;
    }

    public void countAdd() {
        synchronized(this) {
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
    public void printCount() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.equals("A")) {
            countAdd();
        } else if (threadName.equals("B")) {
            printCount();
        }
    }
}
