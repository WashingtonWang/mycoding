package com.xiangyu.function.collection.list.eg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @Description:
 * user: xiangyu.wang
 * date: 2017/12/22 14:51
 */
public class ListRemoveEg {
    public static void main(String[] args) {
        // 使用迭代器删除列表元素
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            if (it.next().length() > 3){
                it.remove();
            }
        }
        //com.mycoding.javabase.list.forEach(s -> System.out.println(s));

        // 使用removeIf()结合匿名名内部类实现
        ArrayList<String> listRemoveIf = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        listRemoveIf.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 3;
            }
        });
        //listRemoveIf.forEach(System.out::println);

        // 使用removeIf()结合Lambda表达式实现
        ArrayList<String> listLambda = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        listLambda.removeIf(s -> s.length() > 3);
        listLambda.forEach(System.out::println);
    }
}
