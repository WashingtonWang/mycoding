package com.xiangyu.algorithm;

import java.util.Arrays;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/3/31 10:49
 * 最长公共前缀
 * <p>
 * Leetcode:编写一个函数来査找字符串数组中的最长公共前缀。如果不存在公共前缀,返回空字符串
 * <p>
 * 思路很简单!先利用 Arrays. sort(strs)为数组排序,再将数组第一个元素和最后一个元素的字符从前往后对比即可
 */
public class CommonPrefix {

  public static void main(String[] args) {
    String[] strs = { "caustomer", "car", "cat" };
    // String[] strs = { "customer", "car", null };//空串
    // String[] strs = {};//空串
    // String[] strs = null;//空串
    System.out.println(replaceSpace(strs));// c
  }

  public static String replaceSpace(String[] strs) {
    // 如果检查值不合法及就返回空串
    if (!checkStrs(strs)) {
      return "";
    }
    // 数组⻓度
    int len = strs.length;
    // 用于保存结果
    StringBuilder res = new StringBuilder();
    // 给字符串数组的元素按照升序排序(包含数字的话,数字会排在前面)
    Arrays.sort(strs);
    int m = strs[0].length();
    int n = strs[len - 1].length();
    int num = Math.min(m, n);
    for (int i = 0; i < num; i++) {
      if (strs[0].charAt(i) == strs[len - 1].charAt(i)) {
        res.append(strs[0].charAt(i));
      } else break;
    }
    return res.toString();
  }

  private static boolean checkStrs(String[] strs) {
    boolean flag = false;
    if (strs != null) {       // 遍历strs检查元素值
      for (int i = 0; i < strs.length; i++) {
        if (strs[i] != null && strs[i].length() != 0) {
          flag = true;
        } else {
          flag = false;
          break;
        }
      }
    }
    return flag;
  }
}
