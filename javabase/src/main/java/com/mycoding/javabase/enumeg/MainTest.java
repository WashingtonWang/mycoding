package com.mycoding.javabase.enumeg;

import java.util.Arrays;
import java.util.Collection;

public class MainTest{

    public static void main(String[] args){
        double x = Double.parseDouble("2");
        double y = Double.parseDouble("6");
        test(ExtendedOperation.class, x, y);
        testOR(Arrays.asList(ExtendedOperation.values()), x, y);

    }

    private static <T extends Enum<T> & OperationExt> void test(Class<T> opSet, double x, double y){
        for (OperationExt op : opSet.getEnumConstants())
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }

    private static void testOR(Collection<? extends OperationExt> opSet, double x, double y){
        for (OperationExt op : opSet)
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }

}
