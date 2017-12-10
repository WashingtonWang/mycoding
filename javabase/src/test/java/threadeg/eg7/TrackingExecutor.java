package threadeg.eg7;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 在ExecutorService 中跟踪在关闭后被取消的任务 《java并发编程实战》 P.130
 * user: xiangyu.wang
 * date: 2017/11/18 17:57
 */
public class TrackingExecutor extends AbstractExecutorService {

    private final ExecutorService exec;
    private final Set<Runnable> taskCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    public List<Runnable> getCancelledTask(){
        if (!exec.isTerminated()){
            throw new IllegalStateException();
        }
        return new ArrayList<Runnable>(taskCancelledAtShutdown);
    }

    @Override
    public void execute(final Runnable runnable) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                }finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted()){
                        taskCancelledAtShutdown.add(runnable);
                    }
                }
            }
        });
    }

    //将ExecutorServicer的其它方法委托给exec
    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

}
