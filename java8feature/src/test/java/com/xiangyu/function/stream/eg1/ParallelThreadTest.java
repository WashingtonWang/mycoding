package com.xiangyu.function.stream.eg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * user: xiangyu.wang
 * date: 2018/7/26 10:24
 */
public class ParallelThreadTest {
    public static void main(String[] args) {
        test1();
    }

    private static void test1(){
        Integer[] intArray = {1,2,3,4,5,6,7,8};
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        for (Integer i : listOfIntegers){
            if (i == 2){
                listOfIntegers.remove(Integer.valueOf(2));
            }
        }

        listOfIntegers.forEach(System.out::println);

        for (int i = 0; i < listOfIntegers.size(); i++){
            if (i == 1){
                listOfIntegers.remove(Integer.valueOf(2));
            }
        }

        listOfIntegers.forEach(System.out::println);
    }
}
