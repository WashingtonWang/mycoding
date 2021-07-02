package com.mycoding.javabase.threadeg.eg5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 使用FutureTask来提前加载稍后需要的数据 p.81 《java并发编程实战》
 * @Auther: wangxiangyu
 * @Date: 2017/11/5 14:29
 */
public class Proloader {
    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return new ProductInfo();
        }
    });
    private final Thread thread = new Thread(future);
    public void start(){
        thread.start();
    }

    public ProductInfo get() throws Exception{
        try {
            return future.get();
        }catch (ExecutionException e){
            Throwable cause = e.getCause();
            if (cause instanceof ExecutionException){
                throw (Exception) cause;    //此处异常和下面的异常都是自定义异常 书中没有给出 所以...
            }else{
                throw launderThrowable(cause);
            }
        }
    }

    public static RuntimeException launderThrowable(Throwable t){
        if (t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if (t instanceof Error){
            return (RuntimeException) t;
        }else{
            return new IllegalStateException("not unchecked",t);
        }
    }


    static class ProductInfo{

    }


}

