package com.mycoding.javabase.threadeg.util.threadpool;

import java.util.concurrent.*;

/**
 * @Description: 写个 线程池工具类
 * user: xiangyu.wang
 * date: 2018/2/7 18:02
 */
public class ThreadPoolUtil {
    public static void test() {
        ExecutorService es = ExecutorServiceInstance.INSTANCE.getInstance();
        System.out.println(es);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50000; j++) {
                final int iFinal = i;
                final int jFinal = j;
                instance().submit(() -> {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + " 当前I值：" + iFinal + " | 当前J值：" + jFinal + "");
                });
                //try {
                //    Object o = future.get();
                //    System.out.println(o);
                //} catch (InterruptedException e) {
                //    e.printStackTrace();
                //} catch (ExecutionException e) {
                //    e.printStackTrace();
                //}
            }
            //new Thread(ThreadPoolUtil::test).start();
        }
        shutdown();
    }

    private enum ExecutorServiceInstance {
        INSTANCE;

        private ExecutorService es;

        ExecutorServiceInstance() {
            es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        }

        public ExecutorService getInstance() {
            return es;
        }
    }

    public static void shutdown() {
        instance().shutdown();
        try {
            if (!instance().awaitTermination(30, TimeUnit.SECONDS)){
                System.out.println("0000");
                instance().shutdownNow();
            }else{
                System.out.println("1111");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ExecutorService instance() {
        return ExecutorServiceInstance.INSTANCE.getInstance();
    }

    public static void add(Runnable task) {
        Future<?> future = instance().submit(task);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

