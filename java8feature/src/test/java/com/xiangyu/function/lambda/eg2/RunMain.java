package com.xiangyu.function.lambda.eg2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * user: xiangyu.wang
 * date: 2017/10/28 14:48
 */
public class RunMain {
    public static void parentDefaultUsed(){
        Parent parent = new ParentImpl();
        parent.welcome();
        System.out.println(parent.getLastMessage());
    }

    public static void testOptional(){
        Optional<String> a = Optional.of("a");
        System.out.println(a.get());
        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);
        System.out.println(emptyOptional.isPresent());
        System.out.println(a.isPresent());
        System.out.println(emptyOptional.orElse("b"));
        System.out.println(emptyOptional.orElseGet(()->"c"));
    }

    /**
     * stream中的元素顺序(如果是有序集合 stream的元素出现就按出现顺序排列，如果是无序集合，stream中元素也是无序的
     * 注意：（stream有些操作有时会产生顺序）
     */
    public static void testCollectOrder(){
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        List<Integer> sameOrder = numbers.stream()
                .collect(Collectors.toList());
        System.out.println(numbers.equals(sameOrder));

        Set<Integer> sets = new HashSet<>(Arrays.asList(4,3,2,1));
        List<Integer> setOrder = sets.stream().collect(Collectors.toList());

        List<Integer> ll = sets.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
        System.out.println(ll);

        List<Integer> listOne = Arrays.asList(4,2,3,1);
        List<Integer> resultList = listOne.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
        System.out.println(resultList);

        /**
         * stream 有序操作
         */
        Set<Integer> numbersOne = new HashSet<>(Arrays.asList(3,4,1,2));
        List<Integer> sameOrderOne = numbersOne.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(numbers.equals(sameOrderOne));

        List<Integer> unordered = numbersOne.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
        System.out.println(unordered);

    }

    public static void main(String[] args){
        //parentDefaultUsed();
        //testOptional();
        testCollectOrder();
    }
}
