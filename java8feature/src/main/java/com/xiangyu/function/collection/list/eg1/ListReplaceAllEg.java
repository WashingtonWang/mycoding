package com.xiangyu.function.collection.list.eg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * user: xiangyu.wang
 * date: 2017/12/22 15:11
 */
public class ListReplaceAllEg {
    public static void main(String[] args) {
        // 使用下标实现元素替换
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        for (int i = 0; i < list.size(); i++){
            String str = list.get(i);
            if (str.length() > 3){
                list.set(i, str.toUpperCase());
            }
        }
        //com.mycoding.javabase.list.forEach(System.out::println);

        // 使用匿名内部类实现
        ArrayList<String> listInner = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        listInner.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                if (s.length() > 3){
                    return s.toUpperCase();
                }
                return s;
            }
        });
        //listInner.forEach(System.out::println);

        // 使用Lambda表达式实现
        ArrayList<String> listLambda = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        listLambda.replaceAll(s -> {
            if (s.length() > 3){
                return s.toUpperCase();
            }
            return s;
        });
        listLambda.forEach(System.out::println);
    }
}
