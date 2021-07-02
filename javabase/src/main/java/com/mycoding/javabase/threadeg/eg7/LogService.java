package com.mycoding.javabase.threadeg.eg7;

import java.io.PrintWriter;
import java.util.concurrent.*;

/**
 * @Description: 向LogWriter添加可靠的取消操作
 * user: xiangyu.wang
 * date: 2017/11/18 11:42
 */
public class LogService {
    /****************使用ExecutorService管理线程是更好的方法*******************/
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    /****************使用ExecutorService管理线程是更好的方法*******************/
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    private boolean isShutdown;
    private int reservations;

    public LogService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
        this.queue = queue;
        this.loggerThread = loggerThread;
        this.writer = writer;
    }

    public void start(){
        loggerThread.start();
    }

    public void stop() throws InterruptedException{
        synchronized (this){
            isShutdown = true;
        }
        loggerThread.interrupt();
        /****************使用ExecutorService管理线程是更好的方法*******************/
        try {
            exec.shutdown();
            exec.awaitTermination(1000, TimeUnit.MINUTES);
        }finally {
            writer.close();
        }

        /****************使用ExecutorService管理线程是更好的方法*******************/
    }

    public void log(String msg) throws InterruptedException{
        synchronized (this){
            if (isShutdown){
                throw new IllegalStateException();
            }
            ++reservations;
        }
        queue.put(msg);
        /****************使用ExecutorService管理线程是更好的方法*******************/
        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    //
                }
            });
        }catch (RejectedExecutionException ignored){

        }
        /****************使用ExecutorService管理线程是更好的方法*******************/
    }


    private class LoggerThread extends Thread{
        public void run(){
            try {
                while (true){
                    try {
                        synchronized (LogService.this){
                            if (isShutdown && reservations == 0){
                                break;
                            }
                            String msg = queue.take();
                            synchronized (LogService.this){
                                --reservations;
                            }
                            writer.println(msg);
                        }
                    }catch (InterruptedException e){
                        //com.mycoding.javabase.retry
                    }
                }
            }finally {
                writer.close();
            }
        }
    }

    /**
     * 通过注册一个关闭钩子来停止日志服务 《java并发编程实战》 P.136
     */
    public void hook(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                //操作
                try {
                    LogService.this.stop();
                }catch (InterruptedException ignored){
                    //
                }
            }
        });
    }


}
