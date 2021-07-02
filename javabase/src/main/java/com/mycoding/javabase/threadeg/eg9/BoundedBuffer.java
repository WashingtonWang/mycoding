package com.mycoding.javabase.threadeg.eg9;

import java.util.concurrent.Semaphore;

/**
 * @Description: 基于信号量的有界缓存   《java并发编程实战》  P.205
 * @Auther: wangxiangyu
 * @Date: 2017/12/10 16:16
 */
public class BoundedBuffer<E> {
    private final Semaphore availableItems, availableSpaces;
    private final E[] items;
    private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capacity){
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull(){
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException{
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }

    public E take() throws InterruptedException{
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    private synchronized void doInsert(E x){
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length) ? 0 : i;
    }

    private synchronized E doExtract(){
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return x;
    }

    public static void main(String[] args) throws Exception {
        //testIsEmptyWhenConstructed();
        //testIsFullAfterPuts();
        testTakeBlocksWhenEmpty();
    }

    /**
     * BoundedBuffer的基本单元测试  《java并发编程实战》  P.206
     */
    public static void testIsEmptyWhenConstructed(){
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        System.out.println(bb.isEmpty());
        System.out.println(bb.isFull());
    }

    /**
     * 同上
     * @throws InterruptedException
     */
    public static void testIsFullAfterPuts() throws InterruptedException{
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++){
            bb.put(i);
        }
        System.out.println(bb.isFull());
        System.out.println(bb.isEmpty());
    }

    /**
     * 测试阻塞行为以及对中断的响应性   《java并发编程实战》  P.207
     */
    public static void testTakeBlocksWhenEmpty(){
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(){
            public void run(){
                try{
                    int unused = bb.take();
                    fail();
                }catch (InterruptedException success){
                    System.out.println("成功");
                }
            }
        };
        try{
            taker.start();
            Thread.sleep(1000);
            taker.interrupt();
            taker.join(1000);
            System.out.println(taker.isAlive());
        }catch (Exception unexpreted){
            fail();
        }
    }

    public static void fail(){
        System.out.println("如果执行到这里说明出现了一个错误");
    }
}
