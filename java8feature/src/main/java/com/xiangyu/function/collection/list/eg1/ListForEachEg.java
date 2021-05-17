package com.xiangyu.function.collection.list.eg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description:
 * user: xiangyu.wang
 * date: 2017/12/22 14:44
 */
public class ListForEachEg {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "234", "345");
        list.forEach(System.out::println);
    }


    public static void forEachTest(){
        /***********************forEach使用***************************/
        // 使用曾强for循环迭代
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you"));
        for (String s : list){
            if (s.length() > 3){
                System.out.println(s);
            }
        }
        // 使用forEach()结合匿名内部类迭代
        ArrayList<String> listForEach = new ArrayList<>(Arrays.asList("I", "love", "you"));
        listForEach.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                if (s.length() > 3){
                    System.out.println(s);
                }
            }
        });
        // 使用forEach()结合Lambda表达式迭代
        ArrayList<String> listLambda = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        listLambda.forEach(str -> {
            if (str.length() > 3){
                System.out.println(str);
            }
        });
        /***********************forEach使用***************************/
    }
}
