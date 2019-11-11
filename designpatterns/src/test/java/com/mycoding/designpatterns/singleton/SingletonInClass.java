package com.mycoding.designpatterns.singleton;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/29 10:42
 */
public class SingletonInClass {

    public SingletonInClass() {
    }

    public static class SingletonHolder{
        public static final SingletonInClass INSTANCE = new SingletonInClass();
    }

    public SingletonInClass getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
