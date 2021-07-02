package com.xiangyu.algorithm.ksinterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/24 19:39
 * 4. 前K个高频单词(B)
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * ["i", "love", "leetcode", "i", "love", "coding"]
 */
public class TopK {
    public static void main(String[] args) {
        String[] array = {"i", "love", "leetcode", "i", "love", "coding"};
        test(array, 3);
    }

    public static void test(String[] array, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (String s : array) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry.getKey());
        }
        list.sort((o1, o2) -> map.get(o1).equals(map.get(o2)) ? o1.compareTo(o2) : o2.compareTo(o1));

        List<String> result = list.subList(0, k);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
