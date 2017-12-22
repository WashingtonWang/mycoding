package com.xiangyu.function.collection.map.eg1;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * user: xiangyu.wang
 * date: 2017/12/22 16:20
 */
public class MapReplaceAllEg {
    public static void main(String[] args) {
        // Java7以及之前替换所有Map中所有映射关系
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        //for (Map.Entry<Integer, String> entry : map.entrySet()){
        //    entry.setValue(entry.getValue().toUpperCase());
        //    System.out.println(map.get(entry.getKey()));
        //}

        // 使用replaceAll()结合匿名内部类实现
        HashMap<Integer, String> mapInner = new HashMap<>();
        mapInner.put(1, "one");
        mapInner.put(2, "two");
        mapInner.put(3, "three");
        mapInner.replaceAll(new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer k, String v) {
                return v.toUpperCase();
            }
        });
        //mapInner.forEach((k,v) -> System.out.println(k + " = " + v));

        // 使用replaceAll()结合Lambda表达式实现
        HashMap<Integer, String> mapLambda = new HashMap<>();
        mapLambda.put(1, "one");
        mapLambda.put(2, "two");
        mapLambda.put(3, "three");
        mapLambda.replaceAll((k, v) -> v.toUpperCase());
        mapLambda.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
