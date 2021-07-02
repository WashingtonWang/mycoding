package com.mycoding.javabase.regularexpression;

import java.util.regex.Pattern;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/11/14 20:37
 */
public class RegularExpressionTest {
    public static void main(String[] args) {

    }

    /**
     * 匹配正整数
     */
    public static void isNumber(){
        System.out.println(Pattern.matches("^\\d+$", "df"));
        System.out.println(Pattern.matches("^\\d+$", "0.111"));
        System.out.println(Pattern.matches("^\\d+$", "1212.12"));
        System.out.println(Pattern.matches("^\\d+$", "---1111"));
    }

    /**
     * 匹配是数字，小数
     */
    public static void isNumberOrDecimal(){
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        System.out.println(Pattern.matches(regex, "1010"));
        System.out.println(Pattern.matches(regex, "-10"));
        System.out.println(Pattern.matches(regex, "12.123"));
        System.out.println(Pattern.matches(regex, "0.123123"));
        System.out.println(Pattern.matches(regex, "0.00"));
        System.out.println(Pattern.matches(regex, "0.1ddd"));
        System.out.println(Pattern.matches(regex, "123ddd"));
        System.out.println(Pattern.matches(regex, "asdsd"));
        System.out.println(Pattern.matches(regex, "--"));
        System.out.println(Pattern.matches(regex, "--asd"));
    }
}
