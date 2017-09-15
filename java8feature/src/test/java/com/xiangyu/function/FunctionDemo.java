package com.xiangyu.function;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by xiangyu.wang on 2017/7/31.
 */
public class FunctionDemo {
    static void modifyTheValue(int valueToBeOperated, Function<Integer,Integer> function){
        int newValue = function.apply(valueToBeOperated);

        System.out.println(newValue);
    }

    public static void main(String[] args){
        //int incr = 20;
        //int myNumber = 10;
        //modifyTheValue(myNumber, val -> val + incr);
        //myNumber = 15;
        //modifyTheValue(myNumber, val -> val * 10);
        //modifyTheValue(myNumber, val -> val - 100);
        //modifyTheValue(myNumber, val -> "somthing".length() + val - 100);

        //Function<String, String> function = (x) -> {
        //    System.out.print(x + ": ");
        //    return "Function";
        //};
        //System.out.println(function.apply("hello world"));

        //Predicate<String> pre = (x) -> {
        //    System.out.print(x);
        //    return false;
        //};
        //System.out.println(": " + pre.test("hello world"));
    }
}
