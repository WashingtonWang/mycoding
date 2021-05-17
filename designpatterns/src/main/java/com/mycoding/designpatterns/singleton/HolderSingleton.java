package com.mycoding.designpatterns.singleton;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/12 17:12
 */
public class HolderSingleton {

    private HolderSingleton() {
    }

    public static class Holder {
        private static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.instance;
    }
}
