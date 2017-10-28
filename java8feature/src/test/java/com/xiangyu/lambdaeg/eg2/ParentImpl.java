package com.xiangyu.lambdaeg.eg2;

/**
 * user: xiangyu.wang
 * date: 2017/10/28 14:49
 */
public class ParentImpl implements Parent{
    @Override
    public void message(String body) {
        System.out.println(body);
    }

    @Override
    public String getLastMessage() {
        return "lastMessage";
    }
}
