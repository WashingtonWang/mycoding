package threadeg.eg7;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description:
 * user: xiangyu.wang
 * date: 2017/11/18 9:34
 */
public class CancelTask {
    /**
     * 通过Future 来取消任务  《java并发编程实战》 P.121
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException{
        Future<?> task = Executors.newFixedThreadPool(1).submit(r);
        try{
            task.get(timeout,unit);
        }catch (TimeoutException e){
            //接下来任务将取消
        }catch (ExecutionException e){
            //如果在任务中抛出异常，那么重新抛出该异常
            throw new RuntimeException(e.getCause());
        }finally {
            //如果任务已经结束，那么执行取消操作也不会带来任何影响
            task.cancel(true);//如果任务正在运行，那么将被中断
        }
    }

    /**
     * 使用私有的Executor 并且该Execrtor的生命周期受限于方法调用 《java并发编程实战》 P.130
     * @param hosts
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    private boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException{
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host : hosts){
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (checkMail(host)){
                            hasNewMail.set(true);
                        }
                    }
                });
            }
        }finally {
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }

    private boolean checkMail(String s){

        return false;
    }
}
