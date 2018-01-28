package com.xiangyu.function.collection.list.eg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * user: xiangyu.wang
 * date: 2017/12/22 15:18
 */
public class ListSortEg {
    public static void main(String[] args) {
        // Collections.sort()方法
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        //list.forEach(System.out::println);

        // List.sort()方法结合Lambda表达式
        ArrayList<String> listLambda = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        listLambda.sort(Comparator.comparingInt(String::length));
        listLambda.forEach(System.out::println);
    }
}
