package com.mycoding.javabase.innerclass.eg1;

/**
 * @Description: 用匿名内部类 去构造工厂方法  《Think in java》  P.199
 * user: xiangyu.wang
 * date: 2017/12/8 17:33
 */
public class Implmentation1 implements Service {

    private Implmentation1(){}

    @Override
    public void method1() {
        System.out.println("Implmentation1 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implmentation1 method2");
    }

    public static ServiceFactory factory = () -> new Implmentation1();

    //内部类形式
    public static ServiceFactory factory1 =
            new ServiceFactory() {
                @Override
                public Service getService() {
                    return new Implmentation1();
                }
            };

}
