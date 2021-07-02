package com.mycoding.javabase.threadeg.join;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/9 18:20
 */
public class JoinTest implements Runnable {
    public static int a = 0;

    public void run() {
        for (int k = 0; k < 5; k++) {
            a = a + 1;
        }
    }

    /**
     * 程序的输出结果是 5 吗？答案是：有可能。其实你很难遇到输出 5 的时候，通常情况下都不是 5。
     * 为什么呢？当主线程 main 方法执行 System.out.println(a) 这条语句时，线程还没有真正开始运行，或许正在为它分配资源准备运行。
     * 因为为线程分配资源需要时间，而 main 方法执行完 t.start() 方法后继续往下执行 System.out.println(a)，
     * 这时得到的结果是 a 还没有被改变的值 0 。怎样才能让输出结果为 5？其实很简单，join() 方法提供了这种功能。
     */
    public static void noJoin() throws Exception{
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        System.out.println(a);
    }

    public static void yesJoin() throws Exception{
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        t.join();         // 加入 join()
        System.out.println(a);
    }

    public static void sleepJoin(){
        Thread t = new Thread(new RunnableImpl());
        t.start();
        try {
            t.join(1000);
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        //noJoin();
        //yesJoin();
        sleepJoin();
    }
}

class RunnableImpl implements Runnable {

    public void run() {
        try {
            System.out.println("Begin sleep");
            /**
             * 当 main 线程调用 t.join 时，main 线程等待 t 线程，等待时间是 1000，如果 t 线程 Sleep 2000 呢
             */
            Thread.sleep(1000);
            /**
             * 也就是说 main 线程只等 1000 毫秒，不管 t 什么时候结束
             */
            //Thread.sleep(2000);
            System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
