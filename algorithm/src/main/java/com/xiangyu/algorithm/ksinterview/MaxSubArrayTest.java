package com.xiangyu.algorithm.ksinterview;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/24 19:21
 * 最大连续子数组和(B)
 * [-2,1,-3,4,-1,2,1,-5,4]
 */
public class MaxSubArrayTest {
    public static void main(String[] args) {
        int[] array = new int[]{
                -2, 1, -3, 4, -1, 2, 1, -5, 4
        };
        test(array);
    }

    public static void test(int[] array) {
        if (array.length < 2) {
            return;
        }

        int pre = 0;
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            pre = Math.max(num + pre, num);
            max = Math.max(max, pre);
        }
        System.out.println(max);
    }
}
