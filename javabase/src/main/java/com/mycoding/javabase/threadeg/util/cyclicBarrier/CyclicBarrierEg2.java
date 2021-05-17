package com.mycoding.javabase.threadeg.util.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description: 关于CyclicBarrier 使用用例
 * user: xiangyu.wang
 * date: 2017/12/11 9:18
 */
public class CyclicBarrierEg2 {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);   //此处参数可以多加一个线程
        /*********************new CyclicBarrier(N, Thread) start*************************/
        //下面这种方式  从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
        //CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("当前线程"+Thread.currentThread().getName());
        //    }
        //});
        /**********************new CyclicBarrier(N, Thread) end************************/
        //下面的为普通方式
        //for (int i = 0; i < N; i++){
        //    new Writer(barrier).start();
        //}
        /*********************看一下为await指定时间的效果 start*************************/
        for (int i = 0; i < N; i++){
            if (i < N-1){
                new Writer(barrier).start();
            }else{
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                new Writer(barrier).start();
            }
        }
        /**********************看一下为await指定时间的效果 end************************/
    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run(){
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);  //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                //下面的为普通方式
                //cyclicBarrier.await();
                /*********************看一下为await指定时间的效果 start*************************/
                try {
                    cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
                }catch (TimeoutException e){
                    e.printStackTrace();
                }
                /*********************看一下为await指定时间的效果 end*************************/
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
