package com.mycoding.javabase.gc;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/7 下午4:57
 */
public class GCTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[309000 * 1024];
        allocation2 = new byte[309000 * 1024];
        allocation3 = new byte[309000 * 1024];
        allocation4 = new byte[309000 * 1024];
        //allocation5 = new byte[309000 * 1024];
        //allocation2 = new byte[900*1024];
    }
}
