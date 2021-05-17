package com.mycoding.javabase.threadeg.deadlock;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/31 09:41
 */
public class DeadLockThreeTest {
    public static final byte[] obj1 = new byte[0];
    public static final byte[] obj2 = new byte[0];

    public static void main(String[] args) {
        LockA lockA = new LockA();
        LockB lockB = new LockB();

        new Thread(lockA).start();
        new Thread(lockB).start();
    }
}

class LockA implements Runnable {

    @Override
    public void run() {
        System.out.println("LockA开始执行……");
        while (true) {
            synchronized (DeadLockThreeTest.obj1) {
                System.out.println("LockA锁住obj1……");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLockThreeTest.obj2) {
                    System.out.println("LockA锁住obj2……");
                }
                System.out.println("LockA释放obj2……");
            }
            System.out.println("LockA释放obj1……");
        }
    }
}

class LockB implements Runnable {

    @Override
    public void run() {
        System.out.println("LockB开始执行……");
        while (true) {
            synchronized (DeadLockThreeTest.obj2) {
                System.out.println("LockB锁住obj1……");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLockThreeTest.obj1) {
                    System.out.println("LockB锁住obj2……");
                }
                System.out.println("LockB释放obj2……");
            }
            System.out.println("LockB释放obj1……");
        }
    }
}
