package com.mycoding.javabase.threadeg.eg7;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @Description: 通过newTaskFor将非标准的取消操作封装在一个任务中 《java并发编程实战》 P.123
 * user: xiangyu.wang
 * date: 2017/11/18 10:24
 */
public class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable){
        if (callable instanceof CancellableTask){
            return ((CancellableTask) callable).newTasks();
        }else {
            return super.newTaskFor(callable);
        }
    }
}

abstract class SocketUsingTask<T> implements CancellableTask<T>{
    private Socket socket;

    protected  synchronized void setSocket(Socket s){
        socket = s;
    }

    public synchronized void cancel(){
        try {
            if (socket != null){
                socket.close();
            }
        }catch (IOException ignored){

        }
    }

    public RunnableFuture<T> newTask(){
        return new FutureTask<T>(this){
            public boolean cancel(boolean mayInterruptIfRunning){
                try {
                    SocketUsingTask.this.cancel();
                }finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}

interface CancellableTask<T> extends Callable<T>{
    void cancell();
    RunnableFuture<T> newTasks();
}
