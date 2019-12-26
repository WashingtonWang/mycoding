package threadeg.util.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/12/6 15:17
 */
public class ThreadPoolUtils {

  private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
  private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
  private static final int MAXIMUM_POOL_SIZE = 512;
  private static final int KEEP_ALIVE = 1;

  private static ThreadPoolUtils utils;

  private ThreadPoolExecutor executor;

  public ThreadPoolUtils() {  }

  private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(1024);

  private static final ThreadFactory sThreadFactory = new ThreadFactory() {
    //一个线程安全的Int类
    private final AtomicInteger mCount = new AtomicInteger(1);

    public Thread newThread(Runnable r) {
      return new Thread(r, "ThreadPoolUtils " + mCount.getAndIncrement());
    }
  };

  public static ThreadPoolUtils getInstance() {
    if (utils == null) {
      synchronized (ThreadPoolUtils.class) {
        if (utils == null) {
          utils = new ThreadPoolUtils();
        }
      }
    }
    return utils;
  }




  //public static class SingletonExecutorService {
  //
  //  private static int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
  //  public static final ExecutorService INSTANCE = Executors.newFixedThreadPool(corePoolSize);
  //
  //}
  //
  //public static class SingletonHolder {
  //  public static final ThreadPoolUtils INSTANCE = new ThreadPoolUtils();
  //}
  //
  //public static ThreadPoolUtils getInstance() {
  //  return SingletonHolder.INSTANCE;
  //}
}
