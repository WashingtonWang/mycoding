package com.mycoding.javabase.singleton.eg1;

/**
 * 以内部类的形式 创建单例对象
 * user: xiangyu.wang
 * date: 2018/3/10 10:21
 */
public class SingletonInnerClass {

    private static class SingletonHolder {
        private static SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }

    private SingletonInnerClass (){
        System.out.println("123");
    }

    private static SingletonInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                SingletonInnerClass ss = SingletonInnerClass.getInstance();
                System.out.println(ss);
            }).start();
        }
    }
}
