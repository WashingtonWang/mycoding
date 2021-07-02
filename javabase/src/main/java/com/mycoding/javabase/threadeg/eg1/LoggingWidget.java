package com.mycoding.javabase.threadeg.eg1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 此处需要注意：由于LoggingWidget 继承了 Widget 并且重写了父类的同步方法，
 * 以下面代码的运行情况来分析的话 得出的结论是：
 * 同步的时候  是 LoggingWidget 对象实例上 加的锁
 */
public class LoggingWidget extends Widget{
    public void doSomething(){
        synchronized(this){
            System.out.println(this.getClass());
            System.out.println("i am is loggingWidget");
            super.doSomething();
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(5);
        LoggingWidget lw = new LoggingWidget();
        for (int i = 0; i < 100; i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    lw.doSomething();
                }
            });
        }
        es.shutdown();
    }
}
