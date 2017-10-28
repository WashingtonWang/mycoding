package com.xiangyu.lambdaeg.eg2;

/**
 * user: xiangyu.wang
 * date: 2017/10/28 14:46
 */
public interface Parent {
    void message(String body);

    default void welcome(){
        message("Parent: Hi");
    }

    String getLastMessage();
}
