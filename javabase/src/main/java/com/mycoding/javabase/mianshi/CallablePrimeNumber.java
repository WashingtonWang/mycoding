package com.mycoding.javabase.mianshi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/29 21:58
 */
public class CallablePrimeNumber {
    private static int threadPoolSize = Runtime.getRuntime().availableProcessors();

    private static int numberParts = Runtime.getRuntime().availableProcessors();
    private static AtomicInteger integer = new AtomicInteger();


    private static boolean isPrimeNumber(int src) {
        double sqrt = Math.sqrt(src);
        if (src < 2) {
            return false;
        }
        if (src == 2 || src == 3) {
            return true;
        }
        if (src % 2 == 0) {// 先判断是否为偶数，若偶数就直接结束程序
            return false;
        }
        for (int i = 3; i <= sqrt; i+=2) {
            if (src % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int getPrimeNums(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (isPrimeNumber(i)) {
                integer.getAndIncrement();
            }
        }
        return integer.get();
    }

    public void sumPrimeNums(int number) {
        int nums = number / numberParts;
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < numberParts; i++) {
            int start = i * nums + 1;
            int end = (numberParts - i == 1) ? number : start + nums - 1;
            callableList.add(() -> getPrimeNums(start, end));
        }
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        try {
            executor.invokeAll(callableList, 10000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("count--------->" + integer.get());
    }

    public static void main(String[] args) {
        //CallablePrimeNumber number = new CallablePrimeNumber();
        //long startTime = System.currentTimeMillis();
        //number.sumPrimeNums(10000);
        //long endTime = System.currentTimeMillis();
        //System.out.println((endTime - startTime) + "ms");

        CallablePrimeNumber cp = new CallablePrimeNumber();
        cp.threadTest(10000);

    }

    public int getSum(int start, int end){
        for (int i = start; i <= end; i++){
            if (isPrimeNumber(i)){
                integer.getAndIncrement();
            }
        }
        return integer.get();
    }

    public void threadTest(int number){
        int nums = number / numberParts;
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < numberParts; i++){
            int start = i * nums + 1;
            int end = (numberParts - i == 1) ? number : start + nums -1;
            callableList.add(() -> getSum(start, end));
        }

        ExecutorService es = Executors.newFixedThreadPool(threadPoolSize);

        try {
            List<Future<Integer>> result = es.invokeAll(callableList);
            result.forEach(task ->{
                try {
                    int i = task.get();
                    System.out.println("iiiii: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        es.shutdown();
        System.out.println("result: " + integer.get());
    }
}
