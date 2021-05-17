package com.mycoding.javabase.map.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/5/19 17:47
 */
public class HashMapRemoveEg {
    public static void main(String[] args) {
        Map<Short, String> map = new HashMap<>();
        for (short i = 0; i < 100; i++){
            map.put(i, String.valueOf(i));
            map.remove(i - 1);
        }
        System.out.println(map.size());
    }
}
