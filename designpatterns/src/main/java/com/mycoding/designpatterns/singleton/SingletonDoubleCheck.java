package com.mycoding.designpatterns.singleton;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/29 10:47
 */
public class SingletonDoubleCheck {
    //public volatile static SingletonDoubleCheck instance;
    public static volatile SingletonDoubleCheck instance;

    public SingletonDoubleCheck(){}

    public static SingletonDoubleCheck getInstance(){
        if (instance == null){
            synchronized (SingletonDoubleCheck.class){
                if (instance == null){
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        
        return instance;
    }
}
