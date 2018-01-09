package com.xiangyu.function.collection.map.eg1;

import java.util.HashMap;

/**
 * user: xiangyu.wang
 * date: 2017/12/22 16:05
 */
public class MapGetOrDefaultEg {
    public static void main(String[] args) {
        // 查询Map中指定的值，不存在时使用默认值
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        // Java7以及之前做法
        if (map.containsKey(4)){
            System.out.println(map.get(4));
        }else{
            System.out.println("NoValue");
        }

        // Java8使用Map.getOrDefault()
        System.out.println(map.getOrDefault(4, "NoValue"));
    }
}
