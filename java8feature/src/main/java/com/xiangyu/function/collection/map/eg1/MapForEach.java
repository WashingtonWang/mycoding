package com.xiangyu.function.collection.map.eg1;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * user: xiangyu.wang
 * date: 2017/12/22 15:52
 */
public class MapForEach {
    public static void main(String[] args) {
        // Java7以及之前迭代Map
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        //for (Map.Entry<Integer, String> entry : com.mycoding.javabase.map.entrySet()){
        //    System.out.println(entry.getKey() + " = " + entry.getValue());
        //}

        // 使用forEach()结合匿名内部类迭代Map
        HashMap<Integer, String> mapInner = new HashMap<>();
        mapInner.put(1, "one");
        mapInner.put(2, "two");
        mapInner.put(3, "three");
        //mapInner.forEach(new BiConsumer<Integer, String>() {
        //    @Override
        //    public void accept(Integer k, String v) {
        //        System.out.println(k + " = " + v);
        //    }
        //});

        // 使用forEach()结合Lambda表达式迭代Map
        HashMap<Integer, String> mapLambda = new HashMap<>();
        mapLambda.put(1, "one");
        mapLambda.put(2, "two");
        mapLambda.put(3, "three");
        mapLambda.forEach((k,v) -> System.out.println(k + " = " + v));
    }
}
