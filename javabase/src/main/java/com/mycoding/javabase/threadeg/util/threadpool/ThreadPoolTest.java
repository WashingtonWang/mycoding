package com.mycoding.javabase.threadeg.util.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/12/17 15:07
 */
public class ThreadPoolTest {
  private static Logger log = LoggerFactory.getLogger(ThreadPoolTest.class);

  private ThreadPoolTest() {}

  private static ThreadPoolHolder getInstance(){
    return ThreadPoolHolder.instance;
  }

  private static class ThreadPoolHolder{

    private static final ThreadPoolHolder instance = new ThreadPoolHolder();

    private ExecutorService service;
    private ThreadLocal<BlockingQueue<Future>> threadLocal;

    private ThreadPoolHolder(){
      this.service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
      this.threadLocal = new ThreadLocal<>();
    }

    public void submit(Runnable task){
      Future future = service.submit(task);
      BlockingQueue<Future> futureBlockingQueue = threadLocal.get();
      if(futureBlockingQueue == null){
        futureBlockingQueue = new LinkedBlockingQueue<>();
        threadLocal.set(futureBlockingQueue);
      }
      futureBlockingQueue.add(future);
    }

    public void getResult(){
      BlockingQueue<Future> futureBlockingQueue = threadLocal.get();
      if(futureBlockingQueue == null) return;
      try {
        Future future;
        while (true) {
          future = futureBlockingQueue.poll(0, TimeUnit.SECONDS);
          if (future == null) break;
          future.get();
        }
      }catch (Exception e){
        log.error("ThreadPool getResult fail", e);
      }
    }

    public void shutdown(){
      service.shutdown();
    }
  }

  public static void execute(Runnable task){
    ThreadPoolTest.getInstance().submit(task);
  }

  public static void getResult(){
    ThreadPoolTest.getInstance().getResult();
  }

  public static void shutdown(){
    ThreadPoolTest.getInstance().shutdown();
  }



  public static void main(String[] args) throws ExecutionException, InterruptedException {

    for (int i = 0; i < 20; i++){
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          ThreadPoolHolder tph = ThreadPoolTest.getInstance();
          System.out.println(Thread.currentThread().getName() + " | " + tph);
        }
      });
      thread.start();
    }



    //ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 8,
    //    500, TimeUnit.MILLISECONDS,
    //    new LinkedBlockingQueue<>(20),
    //    new ThreadPoolExecutor.AbortPolicy());
    //
    //for (int i = 0; i < 20; i++) {
    //  Future<String> future = threadPool.submit(new Callable<String>() {
    //    @Override
    //    public String call() throws InterruptedException {
    //      Thread.sleep(3000);
    //      int val = ai.getAndIncrement();
    //      System.out.println("threadName: " + Thread.currentThread());
    //      return String.valueOf(val);
    //    }
    //  });
    //  String val = future.get();
    //  System.out.println(val);
    //}


  }
}