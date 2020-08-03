package com.xiangyu.function.stream.eg1;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/7/31 14:23
 */
public class StreamGroupByTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        List<Person> persons = new ArrayList();
        persons.add(new Person(1, "李四", 10, BigDecimal.ONE, "北京"));
        persons.add(new Person(2, "王五", 21, BigDecimal.TEN, "北京"));
        persons.add(new Person(3, "张六", 34, BigDecimal.ZERO, "天津"));
        persons.add(new Person(4, "李四", 6, BigDecimal.ONE, "北京"));
        persons.add(new Person(5, "王五", 55, BigDecimal.ONE, "天津"));

        Map<BigDecimal, List<Person>> map = persons.stream().collect(Collectors.groupingBy(Person::getGoodsQty));
        System.out.println(map);
        Map<String, List<Person>> map1 = persons.stream().collect(Collectors.groupingBy(person -> {
            return person.getName() + person.getAddress();
        }));
        System.out.println(map1);

        Map<BigDecimal, Set<String>> map2 = persons.stream().collect(Collectors.groupingBy(Person::getGoodsQty, Collectors.mapping(Person::getAddress, Collectors.toSet())));
        System.out.println(map2);
    }

    public static void test2() {
        List<String> items =
                Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        // 分组
        Map<String, List<String>> result1 = items.stream().collect(
                Collectors.groupingBy(
                        Function.identity()
                )
        );
        System.out.println(result1);
    }

    public static void test3() {
        List<String> items =
                Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        // 分组
        Map<String, Long> result1 = items.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
        System.out.println(result1);
    }
}