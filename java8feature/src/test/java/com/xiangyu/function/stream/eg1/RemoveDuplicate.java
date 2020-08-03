package com.xiangyu.function.stream.eg1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * 去掉集合中 相同属性的对象
 * user: xiangyu.wang
 * date: 2018/4/19 15:47
 */
public class RemoveDuplicate {
    public static void main(String[] args) {
        Person p1 = new Person(1, "张三", 5, BigDecimal.ONE, "北京");
        Person p2 = new Person(2, "李四", 6, BigDecimal.ONE, "北京");
        Person p3 = new Person(3, "张三", 6, BigDecimal.ONE, "北京");

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        //方式一
        //Set<Person> personSetString = new TreeSet<>(Comparator.comparing(Person::getName));
        //personSetString.addAll(list);
        //personSetString.forEach(System.out::println);
        //方式二
        List<Person> lp = list.stream().collect(collectingAndThen(
                toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))), ArrayList::new));
        lp.forEach(System.out::println);
    }
}
