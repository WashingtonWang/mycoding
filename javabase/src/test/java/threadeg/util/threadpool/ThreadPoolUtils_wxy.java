package threadeg.util.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
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
public class ThreadPoolUtils_wxy {
  private static Logger log = LoggerFactory.getLogger(ThreadPoolUtils_wxy.class);

  private ThreadPoolUtils_wxy() {
  }

  private static ThreadPoolHolder getInstance() {
    return ThreadPoolHolder.instance;
  }

  private static class ThreadPoolHolder {

    private static final ThreadPoolHolder instance = new ThreadPoolHolder();

    private ExecutorService service;
    private ThreadLocal<BlockingQueue<Future>> threadLocal;

    private ThreadPoolHolder() {
      this.service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 5);
      this.threadLocal = new ThreadLocal<>();
    }

    public void submit(Runnable task) {
      Future future = service.submit(task);
      BlockingQueue<Future> futureBlockingQueue = threadLocal.get();
      if (futureBlockingQueue == null) {
        futureBlockingQueue = new LinkedBlockingQueue<>();
        threadLocal.set(futureBlockingQueue);
      }
      futureBlockingQueue.add(future);
    }

    public Object executeAndGet() {
      BlockingQueue<Future> futureBlockingQueue = threadLocal.get();
      if (futureBlockingQueue == null) return null;
      try {
        Future future;
        while (true) {
          future = futureBlockingQueue.poll(0, TimeUnit.SECONDS);
          if (future == null) break;
          return future.get();
        }
      } catch (Exception e) {
        log.error("ThreadPool executeAndGet fail", e);
      }
      return null;
    }

    public void shutdown() {
      service.shutdown();
    }

    public boolean isShutdown(){
      return service.isShutdown();
    }
  }

  public static void submit(Runnable task) {
    ThreadPoolUtils_wxy.getInstance().submit(task);
  }

  public static Object executeAndGet() {
    return ThreadPoolUtils_wxy.getInstance().executeAndGet();
  }

  public static void shutdown() {
    ThreadPoolUtils_wxy.getInstance().shutdown();
  }

  public static boolean isShutdown(){
    return ThreadPoolUtils_wxy.getInstance().isShutdown();
  }

}