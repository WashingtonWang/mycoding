package threadeg.eg8;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Description: 自定义的线程工厂  《java并发编程实战》  P.146
 * user: xiangyu.wang
 * date: 2017/12/2 10:03
 */
public class MyThreadFactory implements ThreadFactory{
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}

/**
 * @Description: 定制Thread基类   《java并发编程实战》  P.146
 */
class MyAppThread extends Thread{
    public static final String DEFAULT_NAME = "MyAppThread";
    public static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable r){
        this(r, DEFAULT_NAME);
    }

    public MyAppThread(Runnable runnable, String name){
        super(runnable, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler(){
                    public void uncaughtException(Thread t, Throwable e){
                        log.log(Level.SEVERE, "UNCAUGHT in thread" + t.getName(), e);
                    }
                }
        );
    }

    public void run(){
        //复制debug标志以确保一致的值
        boolean debug = debugLifecycle;
        if (debug){
            log.log(Level.FINE, "Created " + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if (debug){
                log.log(Level.FINE, "Exiting " + getName());
            }
        }
    }

    public static int getThreadsCreated(){
        return created.get();
    }

    public static int getThreadsAlive(){
        return alive.get();
    }

    public static boolean getDebug(){
        return debugLifecycle;
    }

    public static void setDebug(boolean b){
        debugLifecycle = b;
    }
}
