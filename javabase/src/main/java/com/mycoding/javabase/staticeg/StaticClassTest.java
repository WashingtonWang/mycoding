package com.mycoding.javabase.staticeg;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/1/23 下午10:33
 */
public class StaticClassTest {

    static {
        int a = 3;
        //静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问.
        //System.out.println(i);

        System.out.println(a);
    }

    private static int i = 1;

    public static void main(String[] args) {
        soutTest();
    }

    public static void soutTest(){
        System.out.println(i);
    }
}