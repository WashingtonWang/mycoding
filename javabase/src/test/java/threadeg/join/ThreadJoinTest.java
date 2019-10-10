package threadeg.join;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/9 20:29
 *
 * 在 main 方法中，通过 new ThreadTest(t).start() 实例化 ThreadTest 线程对象，它通过 synchronized (thread) ，
 * 获取线程对象 t 的锁，并 sleep(9000) 后释放，这就意味着，即使 main 方法 t.join(1000) 等待一秒钟，
 * 它也必须等待 ThreadTest 线程释放 t 锁后才能进入 wait 方法中。
 *
 * 注意：t.join(1000) 是让 main 线程等待 1000ms 或 t 死掉后执行，所以 t.join(1000) 和 sleep(9000) 是同时的，
 * ThreadTest 的实际等待时间还是 9s
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        Thread t = new Thread(new RunnableImplThread());
        new ThreadTest(t).start();
        t.start();
        try {
            t.join(1000);           // main 线程等 1s
            System.out.println("join Finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("join Finish");
    }
}

class RunnableImplThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Thread Begin sleep " + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("Thread End sleep " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest extends Thread {
    Thread thread;
    public ThreadTest(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        holdThreadLock();
    }

    public void holdThreadLock() {
        //用当前的线程当做lock
        synchronized (thread) {
            System.out.println("ThreadTest getObjectLock " + System.currentTimeMillis());
            try {
                Thread.sleep(9 * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("ThreadTest ReleaseObjectLock " + System.currentTimeMillis());
        }
    }
}
