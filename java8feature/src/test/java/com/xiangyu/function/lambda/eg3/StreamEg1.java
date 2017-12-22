package com.xiangyu.function.lambda.eg3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEg1 {

    public static void main(String[] args){
        List<String> collected = Stream.of("a","b","c")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(collected);

        List<Integer> together = Stream.of(Arrays.asList(1,2), Arrays.asList(3,4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        System.out.println(together);

        /**MIN*/
        List<Track> tracks = Arrays.asList(new Track("Bakai",123),
                new Track("Violets for your Furs",324),
                new Track("Time Was",234));
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(Track::getName))
                .get();
        System.out.println(shortestTrack.getName());

        /**reduce*/
        int count = Stream.of(1,2,3)
                .reduce(0,(acc, element) -> acc + element);
        System.out.println(count);
        //把上面的代码分解为如下：
        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
        int count1 = accumulator.apply(
                accumulator.apply(accumulator.apply(0,1),2),
        3);
        System.out.println(count1);

    }
}
