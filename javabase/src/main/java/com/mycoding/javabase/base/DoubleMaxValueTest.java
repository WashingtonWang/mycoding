package com.mycoding.javabase.base;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/12/25 下午12:37
 */
public class DoubleMaxValueTest {
    public static void main(String[] args) {
        doubleMaxValueTest();
    }

    /**
     * Double. MIN_VALUE 和 Double. MAX_VALUE 一样，都是正数，Double. MIN_VALUE 的值是 2^(-1074)，
     * 直接打印 Double. MIN_VALUE 的话，输出结果为 4.9E-324。
     */
    public static void doubleMaxValueTest() {
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Math.min(0.0d, Double.MAX_VALUE));
    }
}
