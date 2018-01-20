package com.xiangyu.function.lambda.eg4;

import com.sun.org.apache.regexp.internal.RE;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * user: xiangyu.wang
 * date: 2018/1/6 9:28
 */
public class Testss {
    public static void main(String[] args) {
        //Stream<String> echos = Stream.generate(() -> "Echo");
        //echos.forEach(System.out::println);
        //Stream<String> ary = Arrays.asList("1","2").stream();
        //ary.forEach(System.out::println);
        //echos.forEach(a -> {
        //    if (a.length() > 4)
        //        System.out.println(a);
        //    else
        //        System.out.println("循环着");
        //});
        //Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        //integers.forEach(System.out::println);

        //Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        //long s = randoms.count();
        //System.out.println(s);
        //
        //Stream<Character> combind = Stream.concat(characterStream("Hello"), characterStream("World"));
        //combind.forEach(System.out::print);

        //Object[] powers = Stream.iterate(1.0, p -> p * 2)
        //        .peek(e -> System.out.println("Fetching " + e))
        //        .limit(20).toArray();

        List<String> words = Arrays.asList("Wang","Xiang","Yu","nb");
        //Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
        //longestFirst.forEach(System.out::println);

        //Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        //largest.ifPresent(s -> System.out.println("largeste: " + s));

        //findFirst()方法时 会返回第一个匹配的元素
        //Optional<String> startWithsW = words.stream().filter(s -> s.startsWith("W")).findFirst();
        //startWithsW.ifPresent(a -> System.out.println("startsW:  " + a));

        //当使用findAny()方法时 会返回第一个匹配的元素
        //当使用parallel 时  返回的结果 可能不同 多运行几次下面的例子会出现不同的结果
        //Optional<String> endWiths = words.stream().parallel().filter(s -> s.endsWith("g")).findAny();
        //endWiths.ifPresent(a -> System.out.println("endWiths:  " + a));

        //flatMap把两个stream 组合成一个
        //List<String> list = new ArrayList<>();
        //list.add("hello");
        //list.add("world");
        //list.stream().flatMap(Testss::characterStream).forEach(System.out::print);

        //此处为聚合操作的一种形式
        //int result = words.stream().reduce(0,
        //        (total, word) -> total + word.length(),
        //        (total1, total2) -> total1 + total2);
        //System.out.println(result);

        //下面的方式更简单并且 效率更高
        //int result1 = words.stream().mapToInt(String::length).sum();
        //System.out.println(result1);

        //生成数组
        //String[] arr = words.stream().toArray(String[]::new);
        //Stream.of(arr).forEach(System.out::print);

        //生成HashSet
        //Set<String> result = words.stream().collect(Collectors.toCollection(TreeSet::new));
        //result.forEach(System.out::print);

        //得到IntSummaryStatistics 这个类型的结果  包含总和，最大值，最小值，平均值，等方法
        //IntSummaryStatistics intSummary = words.stream().collect(Collectors.summarizingInt(String::length));
        //System.out.println(intSummary.getMax());

        //不包括上限
        //IntStream nine = IntStream.range(0, 100);
        //nine.forEach(System.out::println);
        //包括上限
        //IntStream nine1 = IntStream.rangeClosed(0, 100);
        //nine1.forEach(System.out::println);

    }

    public static Stream<Character> characterStream(String s){
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()){
            result.add(c);
        }
        return result.stream();
    }
}
