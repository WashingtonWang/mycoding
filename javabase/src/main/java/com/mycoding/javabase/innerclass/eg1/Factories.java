package com.mycoding.javabase.innerclass.eg1;

/**
 * @Description: 用匿名内部类 去构造工厂方法  《Think in java》  P.199
 * user: xiangyu.wang
 * date: 2017/12/8 17:38
 */
public class Factories {
    public static void serviceConsumer(ServiceFactory factory){
        Service s = factory.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implmentation1.factory);
        serviceConsumer(Implmentation2.factory);
    }
}
