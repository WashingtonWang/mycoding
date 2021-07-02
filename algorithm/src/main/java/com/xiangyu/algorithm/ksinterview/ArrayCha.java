package com.xiangyu.algorithm.ksinterview;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/27 17:30
 * <p>
 * 求数组最大差值 (B) (放水)
 * Q: 给定一个无序数组A，求max(Ai - Aj) (i < j)，比如[0, 4, -5,9, 8],最大的间隔差值就是4-(-5) = 9.
 * A: 这个只需要保存当前元素前面的子数组中的最大元素值，然后减去当前值即可。
 */
public class ArrayCha {

    public static void main(String[] args) {
        int[] array = {0, 4, -5, 9, 8, -10};
        test(array);
    }

    public static void test(int[] array) {
        if (array.length < 2) {
            return;
        }

        int length = array.length;
        int[] dp = new int[length];
        int result = Integer.MIN_VALUE;
        for (int i = length - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] < 0 ? array[i] - array[i + 1] : array[i] - array[i + 1] + dp[i + 1];
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        System.out.println(result);
    }

}
