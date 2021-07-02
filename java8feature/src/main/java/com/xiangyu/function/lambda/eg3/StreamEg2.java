package com.xiangyu.function.lambda.eg3;

import java.util.*;
import java.util.stream.Stream;

/**
 * user: xiangyu.wang
 * date: 2017/10/28 11:11
 */
public class StreamEg2 {

    /**
     * 用stream 计算累加结果
     * @param numbers
     */
    public static void addTest(Stream<Integer> numbers){
        int he = numbers.reduce(0, (sum, number) -> sum + number);
        System.out.println(he);
    }

    /**
     * 针对基本类型 stream 有相对应的stream 可以提高性能
     * @param album
     */
    public static void printTrackLengthStatistics(Album album){
        IntSummaryStatistics trackLengthStats = album.getTracks().stream()
                .mapToInt(track -> track.getLength())
                .summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }

    public static void main(String[] args){
        addTest(Stream.of(1,2,3));
    }

}
