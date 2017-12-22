package com.xiangyu.function.funcinterface.eg1;

/**
 * 函数接口
 * default
 */
@FunctionalInterface
public interface Func extends NonFunc {
    void fun();

    default void foo(){
        System.out.println("in Func funcinterface foo");
    }

    default void voo(){
        System.out.println("in Func funcinterface voo");
    }
}
