package com.xiangyu.function.funcinterface.eg1;

public class TestJ8FunctionalInterface2 {

    public static void main(String[] args){
        TestJ8FunctionalInterface2 test = new TestJ8FunctionalInterface2();
        test.test(10, () -> System.out.println("A customed Func."));
        test.test(100, test::customedFunc);
        test.test(1000, () -> System.out.println("A customed Func."));
    }

    public void customedFunc(){
        System.out.println("A customed method reference.");
    }

    public void test(int x, Func func){
        System.out.println(x);
        func.fun();
    }
}
