package threadeg.eg7;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 在专门的线程中中断任务
 * @Auther: wangxiangyu
 * @Date: 2017/11/16 22:35
 */
public class TimeRun {

    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException{
        //class RethrowableTask implements Runnable{
        //    private volatile Throwable t;
        //
        //    @Override
        //    public void run() {
        //        try{
        //            r.run();
        //        }catch (Throwable t){
        //            this.t = t;
        //        }
        //        void rethrow(){
        //            if (t != null){
        //                throw new RuntimeException(t);
        //            }
        //        }
        //        private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);
        //
        //
        //        RethrowableTask task = new RethrowableTask();
        //        final Thread taskThread = new Thread(task);
        //        taskThread.start();
        //    }
        //}
    }
}
