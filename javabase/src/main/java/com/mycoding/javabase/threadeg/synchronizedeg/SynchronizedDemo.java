package com.mycoding.javabase.threadeg.synchronizedeg;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/2/28 上午10:54
 */
public class SynchronizedDemo {
    public void methodBlock() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }

    public synchronized void method() {
        System.out.println("synchronized 方法");
    }
}
