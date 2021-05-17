package com.mycoding.javabase.threadeg.eg10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 使用显式条件变量的有界缓存   《java并发编程实战》  P.252
 * user: xiangyu.wang
 * date: 2017/12/30 10:18
 */
public class ConditionBoundedBuffer<T> {
    private static final int BUFFER_SIZE = 10;
    protected final Lock lock = new ReentrantLock();
    //条件谓词 ：notFull (count < items.length)
    private final Condition notFull = lock.newCondition();
    //条件谓词 : notEmpty (count > 0)
    private final Condition notEmpty = lock.newCondition();
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    private int tail, head, count;

    //阻塞并直到：notFull
    public void put(T x) throws InterruptedException{
        lock.lock();
        try {
            while (count == items.length){
                notFull.await();
            }
            items[tail] = x;
            if (++tail == items.length){
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    //阻塞并直到：notEmpty
    public T take() throws InterruptedException{
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();
            }
            T x = items[head];
            items[head] = null;
            if (++head == items.length){
                head = 0;
            }
            --count;
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }
}
