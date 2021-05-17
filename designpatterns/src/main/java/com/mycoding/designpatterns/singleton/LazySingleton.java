package com.mycoding.designpatterns.singleton;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/12 17:08
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}
