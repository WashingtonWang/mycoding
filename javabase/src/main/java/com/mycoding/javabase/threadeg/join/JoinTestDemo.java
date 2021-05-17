package com.mycoding.javabase.threadeg.join;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/9 19:21
 */
class CustomThread1 extends Thread{

    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start. ct1");
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(threadName + " loop at " + i + " ct1");
                Thread.sleep(1000);
            }
            System.out.println(threadName + " end. ct1");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run. ct1");
        }
    }
}

class CustomThread extends Thread {
    CustomThread1 t1;
    public CustomThread(CustomThread1 t1) {
        this.t1 = t1;
    }
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start. ct");
        try {
            t1.join();
            System.out.println(threadName + " end. ct");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run. ct");
        }
    }
}

public class JoinTestDemo {

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start. main");
        CustomThread1 t1 = new CustomThread1();
        CustomThread t = new CustomThread(t1);
        try {
            t1.start();
            Thread.sleep(2000);
            t.start();
            t.join();         // 下面会将此处注释掉
            /**
             * t.join 没有注释的执行结果
             * main start. // main 方法所在的线程起动，但没有马上结束，因为调用 t.join()，所以要等到 t 结束了，此线程才能向下执行
             * [CustomThread1] Thread start. // 线程 CustomThread1 起动
             * [CustomThread1] Thread loop at 0 // 线程 CustomThread1 执行
             * [CustomThread1] Thread loop at 1 // 线程 CustomThread1 执行
             * [CustomThread] Thread start. // 线程 CustomThread 起动，但没有马上结束，因为调用 t1.join()，所以要等到 t1 结束了，此线程才能向下执行
             * [CustomThread1] Thread loop at 2 // 线程 CustomThread1 继续执行
             * [CustomThread1] Thread loop at 3 // 线程 CustomThread1 继续执行
             * [CustomThread1] Thread loop at 4 // 线程 CustomThread1 继续执行
             * [CustomThread1] Thread end. // 线程 CustomThread1 结束了
             * [CustomThread] Thread end. // 线程 CustomThread 在 t1.join() 阻塞处起动，向下继续执行的结果
             * main end! // 线程 CustomThread 结束，此线程在 t.join() 阻塞处起动，向下继续执行的结果
             */
            //t.join();
            /**
             * t.join(); 注释掉的执行结果
             * main start. // main 方法所在的线程起动，但没有马上结束，这里并不是因为 join 方法，而是因为 Thread.sleep(2000)
             * [CustomThread1] Thread start. // 线程 CustomThread1 起动
             * [CustomThread1] Thread loop at 0 // 线程 CustomThread1 执行
             * [CustomThread1] Thread loop at 1 // 线程 CustomThread1 执行
             * main end! // Thread.sleep(2000) 结束，虽然在线程 CustomThread 执行了 t1.join()，但这并不会影响到其他线程（这里 main 方法所在的线程）
             * [CustomThread] Thread start. // 线程 CustomThread 起动，但没有马上结束，因为调用 t1.join()，所以要等到 t1 结束了，此线程才能向下执行。
             * [CustomThread1] Thread loop at 2 // 线程 CustomThread1 继续执行
             * [CustomThread1] Thread loop at 3 // 线程 CustomThread1 继续执行
             * [CustomThread1] Thread loop at 4 // 线程 CustomThread1 继续执行
             * [CustomThread1] Thread end. // 线程 CustomThread1 结束了
             * [CustomThread] Thread end. // 线程 CustomThread 在 t1.join() 阻塞处起动，向下继续执行的结果
             */
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end! main");
    }
}
