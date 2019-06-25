package com.xiangyu.function.stream.eg1;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * user: xiangyu.wang
 * date: 2019/5/7 15:10
 */
public class StreamMapTest {

    public static void main(String[] args) {
        mapTest();
    }

    private static void mapTest(){
        List<String> list =  Lists.newArrayList("123", "234", "456", "wang", "wxy", "xiang", "yu");
        list.stream().map(data -> {
           if (data != null){
               System.out.println("11111");
               return list;
           }
           return list;
        })
        .filter(data -> {
            System.out.println("22222");
            return false;
        })
        .filter(data -> {
            System.out.println("3333");
            return data.contains("1");
        })
        .collect(Collectors.toList());

        list.forEach(System.out::println);
    }
}
