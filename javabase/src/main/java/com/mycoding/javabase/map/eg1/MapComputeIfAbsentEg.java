package com.mycoding.javabase.map.eg1;

import java.util.*;

/**
 * @deprecated ComputeIfAbsent使用方法
 * user: xiangyu.wang
 * date: 2017/12/18 18:05
 */
public class MapComputeIfAbsentEg {
    public static void main(String[] args) {
        //普通方式
        //Map<String,List<String>> com.mycoding.javabase.map = new HashMap();
        //com.mycoding.javabase.map.put("1", 1);
        //com.mycoding.javabase.map.put("2", 2);
        //com.mycoding.javabase.map.put("1", 3);


        Map<String, List<String>> mapCom = new HashMap();
        mapCom.computeIfAbsent("a", k -> new ArrayList<String>()).add("222");
        mapCom.computeIfAbsent("b", k -> new ArrayList<String>()).add("333");
        mapCom.computeIfAbsent("a", k -> new ArrayList<String>()).add("444");
        mapCom.computeIfAbsent("b", k -> new ArrayList<String>()).add("555");
        mapCom.computeIfAbsent("a", k -> new ArrayList<String>()).add("111");

        for (Map.Entry<String, List<String>> entry : mapCom.entrySet()){
            System.out.println(entry.getValue());
        }

    }
}
