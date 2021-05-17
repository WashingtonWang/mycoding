package com.mycoding.javabase.threadeg.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalDemo {
    public static void main(String[] args){
        for (int i  = 0; i < 10; i++){
            final Thread t = new Thread(){
              @Override
              public void run(){
                  System.out.println("当前线程: "+Thread.currentThread().getName() + " | 已分配的ID: "+ ThreadId.get());
              }
            };
            t.start();
        }
    }
    static class ThreadId{
        private static final AtomicInteger nextId = new AtomicInteger(0);
        private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
          @Override
          protected Integer initialValue(){
              return nextId.getAndIncrement();
          }
        };

        public static int get(){
            return threadId.get();
        }
    }
}

