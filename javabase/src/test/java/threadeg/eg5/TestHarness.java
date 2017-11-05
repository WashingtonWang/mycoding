package threadeg.eg5;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: 在计时测试中使用CountDownLatch来启动和停止线程 《java并发编程实战》P.79
 * @Auther: wangxiangyu
 * @Date: 2017/11/5 13:48
 */
public class TestHarness {
    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++){
            Thread t = new Thread(){
                public void run(){
                    try{
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException ignored){}
                }
            };
            t.start();

            //下面的代码是lamdba形式
            //Thread t1 = new Thread(() -> {
            //    try{
            //        startGate.await();
            //        try {
            //            task.run();
            //        }finally {
            //            endGate.countDown();
            //        }
            //    }catch (InterruptedException i){}
            //}).start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException{
        long x = timeTasks(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("xxx");
            }
        });
        System.out.println(x);

        long y = timeTasks(10,() -> {
            System.out.println("yyy");
        });
        System.out.println(y);
    }
}
