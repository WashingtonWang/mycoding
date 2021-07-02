package com.mycoding.designpatterns.singleton;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/12 17:09
 */
public class DoubleCheckedSingleton {

    private static volatile DoubleCheckedSingleton instance = null;

    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
