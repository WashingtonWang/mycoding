package com.mycoding.javabase.threadeg.eg9;

/**
 * @Description: 使用wait和notifyAll来实现可重新关闭的阀门  《java并发编程实战》 P.249
 * @Auther: wangxiangyu
 * @Date: 2017/12/28 23:11
 */
public class ThreadGate {
    //条件谓词: opened-since(n) (isOpen || generation > n)
    private boolean isOpen;
    private int generation;

    public synchronized void close(){
        isOpen = false;
    }

    public synchronized void open(){
        ++generation;
        isOpen = true;
        notifyAll();
    }

    //阻塞并直到：opened-since(generation on entry)
    public synchronized void await() throws InterruptedException{
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation){
            wait();
        }
    }
}
