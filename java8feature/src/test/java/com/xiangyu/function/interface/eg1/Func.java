package com.xiangyu.function.eg1;

@FunctionalInterface
public interface Func extends NonFunc {
    void fun();

    default void foo(){
        System.out.println("in Func interface foo");
    }

    default void voo(){
        System.out.println("in Func interface voo");
    }
}
