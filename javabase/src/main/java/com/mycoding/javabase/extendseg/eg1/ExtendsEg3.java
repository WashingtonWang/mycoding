package com.mycoding.javabase.extendseg.eg1;

/**
 * user: xiangyu.wang
 * date: 2017/11/9 14:24
 */
public class ExtendsEg3 extends ExtendsEg2 {
    ExtendsEg3(){
        System.out.println("I am ExtendsEg3");
    }

    public static void main(String[] args) {
        ExtendsEg3 eg3 = new ExtendsEg3();
        eg3.test();
    }

    public void test(){
        System.out.println("ExtendsEg3 test");
    }
}
