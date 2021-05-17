package com.mycoding.javabase.annotation.eg1;

public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1(){
        int i = 0;
        i = i / i;
        System.out.println(i);
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2(){
        int[] a= new int[10];
        int i = a[1];
        System.out.println(i);
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m3(){
        System.out.println("i am m3!");
    }
}
