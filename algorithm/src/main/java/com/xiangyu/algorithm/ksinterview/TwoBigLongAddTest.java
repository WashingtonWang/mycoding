package com.xiangyu.algorithm.ksinterview;

import org.springframework.util.StringUtils;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/19 15:04
 * 1. 写一个大整数加法程序，保证不溢出，大整数用字符串表示，输入非空，非负数，不用考虑不合法输入
 */
public class TwoBigLongAddTest {
    public static void main(String[] args) {
        add("1234567890", "1234567890");
    }

    public static void add(String a, String b) {
        if (!StringUtils.hasText(a) || !StringUtils.hasText(b)) {
            return;
        }

        char[] charA = new StringBuilder(a).reverse().toString().toCharArray();
        char[] charB = new StringBuilder(b).reverse().toString().toCharArray();
        int maxLength = Math.max(charA.length, charB.length);
        int[] result = new int[maxLength + 1];

        int temp = 0;
        for (int i = 0; i <= maxLength; i++) {
            temp = result[i];

            if (i < charA.length) {
                temp += charA[i] - '0';
            }
            if (i < charB.length) {
                temp += charB[i] - '0';
            }

            if (temp >= 10) {
                temp -= 10;
                result[i + 1] = 1;
            }

            result[i] = temp;
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = maxLength; i >= 0; i--) {
            if (result[i] == 0 && flag) {
                continue;
            }
            flag = false;
            sb.append(result[i]);
        }
        System.out.println(sb.toString());
    }
}
