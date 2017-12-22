package com.xiangyu.function.funcinterface.eg1;

@FunctionalInterface
public interface Func extends com.xiangyu.function.eg1.NonFunc {
    void fun();

    default void foo(){
        System.out.println("in Func funcinterface foo");
    }

    default void voo(){
        System.out.println("in Func funcinterface voo");
    }
}
