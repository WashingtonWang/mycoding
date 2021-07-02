package com.xiangyu.function.stream.eg1;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/11/26 下午7:02
 */
public class StreamJoinTest {

    public static void main(String[] args) {
        String[] arr = {"1", "2", "3", "4", "5"};

        String joinStr = Arrays.stream(arr).collect(Collectors.joining(","));
        System.out.println(joinStr);
        String joinStr2 = String.join(",", arr);
        System.out.println(joinStr2);
    }
}
