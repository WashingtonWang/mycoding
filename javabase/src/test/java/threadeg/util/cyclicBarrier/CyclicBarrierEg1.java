package threadeg.util.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description: 关于CyclicBarrier 使用用例
 * 类说明：一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 * 使用场景：需要所有的子任务都完成时，才执行主任务，这个时候就可以选择使用CyclicBarrier。
 * @Auther: wangxiangyu
 * @Date: 2017/12/10 22:13
 */
public class CyclicBarrierEg1 {
    private static final int THREAD_NUM = 5;

    public static class WorkerThread implements Runnable{
        CyclicBarrier barrier;
        public WorkerThread(CyclicBarrier b){
            this.barrier = b;
        }

        @Override
        public void run() {
            try {
                System.out.println("Worker's waiting");
                //线程在这里等待，直到所有线程都到达barrier
                barrier.await();
                System.out.println("ID: " + Thread.currentThread().getId() + " Working");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Barrier");
            }
        });
        for (int i = 0; i < THREAD_NUM; i++){
            new Thread(new WorkerThread(cb)).start();
        }
    }
}
