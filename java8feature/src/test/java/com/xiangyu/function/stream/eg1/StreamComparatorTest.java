package com.xiangyu.function.stream.eg1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/7/13 17:16
 */
public class StreamComparatorTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(8, 2, 10, 5, 12, 2, 5, 90, 11, 12);
        /**去重*/
        List<Integer> listOut = list.stream().distinct().collect(Collectors.toList());
        listOut.forEach(x -> System.out.print(x + " | "));
        System.out.println();
        /**排序1*/
        list.sort((o1, o2) -> {
            return Integer.compare(o1, o2);
        });
        list.forEach(x -> System.out.print(x + " | "));
        System.out.println();


    }

    public static void sort1(List<Integer> list){
        list.sort((o1, o2) -> {
            return Integer.compare(o1, o2);
        });
        /**
         * list.sort(Integer::compare);
         * */
    }

    public static void sort2(List<Integer> list){
        list.sort(Comparator.comparingInt(Integer::byteValue));
    }
}
