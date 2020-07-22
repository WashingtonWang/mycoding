package com.xiangyu.algorithm;

import java.util.HashSet;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/3/31 11:21
 */
public class Palindrome {

  public static void main(String[] args) {
    //System.out.println(longestPalindromeNum("abccccdd"));

    //System.out.println(isPalindrome("A man, a plan, a canal: Panama"));

    //System.out.println(longestPalindrome("ddccccddada"));
    System.out.println(2 & 1);
    for (int i = 0; i < 100; i++) {
      if ((i & 1) == 0){

      }
    }

  }

  /**
   * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
   * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
   * 注意:
   * 假设字符串的长度不会超过 1010。
   * https://leetcode-cn.com/problems/longest-palindrome/description/
   */
  public static int longestPalindromeNum(String s) {
    if (s.length() == 0)
      return 0;
    // 用于存放字符
    HashSet<Character> hashset = new HashSet<Character>();
    char[] chars = s.toCharArray();
    int count = 0;
    for (int i = 0; i < chars.length; i++) {
      if (!hashset.contains(chars[i])) {
        // 如果hashset没有该字符就保存进去
        hashset.add(chars[i]);
      } else {
        // 如果有,就让count++(说明找到了一个成对的字符),然后把该字符移除
        hashset.remove(chars[i]);
        count++;
      }
    }
    return hashset.isEmpty() ? count * 2 : count * 2 + 1;
  }

  /**
   * Leet Code:给定一个字符串,验证它是否是回文串,只考虑字母和数字字符,可以忽略字母的大小写。
   * 说明:本题中,我们将空字符串定义为有效的回文串
   * https://leetcode-cn.com/problems/valid-palindrome/description
   *
   * @param s
   * @return
   */
  public static boolean isPalindrome(String s) {
    if (s.length() == 0)
      return true;
    int l = 0, r = s.length() - 1;
    while (l < r) {
      // 从头和尾开始向中间遍历
      if (!Character.isLetterOrDigit(s.charAt(l))) {
        // 字符不是字母和数字的情况
        l++;
      } else if (!Character.isLetterOrDigit(s.charAt(r))) {
        // 字符不是字母和数字的情况
        r--;
      } else {
        // 判断二者是否相等
        if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
          return false;
        l++;
        r--;
      }
    }
    return true;
  }

  /**
   * Leetcode: Leet Code:最长回文子串给定一个字符串s,找到s中最长的回文子串。你可以假设s的最大长度为100。
   * https://leetcode-cn.com/problems/longest-palindromic-substring/description/
   *
   * @param s
   * @return
   */
  public static String longestPalindrome(String s) {
    if (s.length() < 2) return s;
    for (int i = 0; i < s.length() - 1; i++) {
      palindromeHelper(s, i, i);
      palindromeHelper(s, i, i + 1);
    }
    return s.substring(index, index + len);
  }

  private static int index, len;

  public static void palindromeHelper(String s, int l, int r) {
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    if (len < r - l - 1) {
      index = l + 1;
      len = r - l - 1;
    }
  }

}
