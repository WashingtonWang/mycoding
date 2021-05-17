package com.xiangyu.string.eg1;

import java.time.ZoneId;

/**
 * java8 String类中 添加的唯一一个方法
 * user: xiangyu.wang
 * date: 2018/1/13 13:39
 */
public class J8String {
    public static void main(String[] args) {
        String joined = String.join(",", "I", "love","you");
        System.out.println(joined);
        String ids = String.join(",", ZoneId.getAvailableZoneIds());
        System.out.println(ids);

        double x = Double.parseDouble("+1.0");
        System.out.println(x);
        int n = Integer.parseInt("+1");
        System.out.println(n);
   }
}
