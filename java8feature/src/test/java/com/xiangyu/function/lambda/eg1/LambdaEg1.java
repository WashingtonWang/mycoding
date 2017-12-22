package com.xiangyu.function.lambda.eg1;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class LambdaEg1 {
    Runnable r1 = () -> {
        System.out.println(this);
    };

    Runnable r2 = () -> {
        System.out.println(toString());
    };

    private static Callable<String> helloCallable(String name){
        String hello = "Hello";
        System.out.println(name);
        return () -> (hello + ", "+ name);
    }

    public static <T,U extends Comparable<? super U>> Comparator<T> comparingOne(Function<T, U> keyExtractor){
        return (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }

    public static void main(String[] args){
        //Supplier<Runnable> c = () -> () -> {
        //    System.out.println("hello");
        //};
        //boolean flag = true;
        //Callable<Integer> a = flag ? (() -> 23) : (() -> 42);

        //Object o = (Runnable)() -> {
        //    System.out.println("hello");
        //};

        //new LambdaEg1().r1.run();
        //new LambdaEg1().r2.run();

        Callable<String> name = helloCallable("wangxy");

        /**
         * lambda 表达式对 值 封闭，对 变量 开放的原文是：lambda expressions close over values, not variables
         * 在这里增加一个例子以说明这个特性：
         *   int sum = 0;
             list.forEach(e -> { sum += e.size(); }); // Illegal, close over values
             List<Integer> aList = new List<>();
             list.forEach(e -> { aList.add(e); }); // Legal, open over variables
         */
    }
}
