package com.mycoding.designpatterns.singleton;

/**
 * 饿汉式
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/12 17:04
 */
public class HungerSingleton {

    public static HungerSingleton instance = new HungerSingleton();

    private HungerSingleton(){}

    public static HungerSingleton getInstance(){
        return instance;
    }
}
