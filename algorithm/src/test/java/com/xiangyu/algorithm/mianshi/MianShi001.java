package com.xiangyu.algorithm.mianshi;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/5/21 15:11
 * 第一题
 * 输入：一个数字和字母组成的字符串（0 < 长度 < 10000）
 * 输出：最大的整型数字是多少（如果没有返回-1）
 *
 * 样例输入：dsfadsf567781234567812345678afddsafads123f23fs212
 * 样例输出：567781234567812345678
 */
public class MianShi001 {

    public static void main(String[] args) {
        long str = backNumber("dsfadsf567781234567812345678afddsafads123f23fs212");
    }

    public static long backNumber(String str){
        String numbers= str.replace("\\D", "_");
        String[] arrNum = numbers.split("_");
        long max = 0;
        for (int i = 0; i < arrNum.length; i++){
            if (!arrNum[i].matches("\\d")){
                continue;
            }
            long element = Long.parseLong(arrNum[i]);
            if (element > max){
                max = element;
            }
        }
        return max;
    }

}
