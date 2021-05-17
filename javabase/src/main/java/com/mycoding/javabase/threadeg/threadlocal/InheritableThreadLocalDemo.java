package com.mycoding.javabase.threadeg.threadlocal;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/6 下午5:23
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set("父类数据:threadLocal");
        inheritableThreadLocal.set("父类数据:inheritableThreadLocal");

        new Thread(() -> {
            System.out.println("子线程获取父类threadLocal数据：" + threadLocal.get());
            System.out.println("子线程获取父类inheritableThreadLocal数据：" + inheritableThreadLocal.get());
        }).start();
    }
}
