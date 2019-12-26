package com.xiangyu.function.collection.map.eg1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/12/11 11:09
 */
public class MapComputeTest {

  public static void main(String[] args) {
    //Map<String, List<String>> map = computeIfAbsentTest();

    Map<String, List<String>> map = computeIfPresentTest();

    map.forEach((k, v) ->{
      System.out.print(k);
      System.out.print("->");
      v.forEach(l -> System.out.print(l + " | "));
      System.out.print(" ");
    });

  }

  public static Map<String, List<String>> computeIfAbsentTest() {

    Map<String, List<String>> map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      map.computeIfAbsent(String.valueOf(i), k -> new LinkedList<>()).add("123");
    }

    for (int i = 0; i < 15; i++) {
      map.computeIfAbsent(String.valueOf(i), k -> new LinkedList<>()).add("456");
    }

    System.out.println("总数量为：" + map.entrySet().size());

    return map;
  }

  public static Map<String, List<String>> computeIfPresentTest() {

    Map<String, List<String>> map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      map.computeIfPresent(String.valueOf(i), (k, v) ->{
        v.stream().filter(s -> !s.contains("123"));
        return v;
      });
    }

    //for (int i = 0; i < 15; i++) {
    //  map.computeIfPresent(String.valueOf(i), k -> new LinkedList<>()).add("456");
    //}

    System.out.println("总数量为：" + map.entrySet().size());

    return map;
  }
}
