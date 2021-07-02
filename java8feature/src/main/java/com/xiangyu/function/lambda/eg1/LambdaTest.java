package com.xiangyu.function.lambda.eg1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaTest {

    public static void main(String[] args){
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("Hello from thread");
        //    }
        //}).start();
        //
        //new Thread(() -> System.out.println("Hello from thread")).start();
        //
        //Button button = new Button();
        //button.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        System.out.println("The button was clicked using old fashion code!");
        //    }
        //});
        //
        //button.addActionListener((e) -> {
        //    System.out.println("The button was clicked using old fashion code!");
        //});

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        System.out.println("Print all numbers:");
        eveluate(list, (n) -> true);

        System.out.println("Print no numbers");
        eveluate(list, (n) -> false);

        System.out.println("Print even numbers:");
        eveluate(list, (n)-> n%2 == 0 );

        System.out.println("Print odd numbers:");
        eveluate(list, (n)-> n%2 == 1 );

        System.out.println("Print numbers greater than 5:");
        eveluate(list, (n)-> n > 5 );

    }

    public static void eveluate(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n : list){
            if (predicate.test(n))
                System.out.println(n+" ");
        }
    }
}
