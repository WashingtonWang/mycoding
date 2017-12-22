package com.xiangyu.function.eg1;

public class TestJ8FunctionalInterface implements com.xiangyu.function.eg1.Func {
    @Override
    public void fun() {
        System.out.println("fun");
    }

    @Override
    public void foo() {
        System.out.println("foo");
    }

    @Override
    public void voo() {
        System.out.println("voo");
    }

    public static void main(String[] args){
        com.xiangyu.function.eg1.Func func = new TestJ8FunctionalInterface();
        func.foo();
        func.fun();
        func.voo();
    }
}
