package com.mycoding.designpatterns.singleton;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/29 10:42
 */
public class SingletonInClass {

    public SingletonInClass() {
    }

    public static class SingletonHolder{
        public static final SingletonInClass INSTANCE = new SingletonInClass();
    }

    public static SingletonInClass getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        while (true){
            Future ft = es.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    SingletonInClass sc = SingletonInClass.getInstance();
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(sc);
                    return "wang";
                }
            });
            String name = String.valueOf(ft.get());
            System.out.println(name);
            //es.shutdown();
        }

    }

}
