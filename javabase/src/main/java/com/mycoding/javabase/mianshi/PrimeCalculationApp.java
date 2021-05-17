package com.mycoding.javabase.mianshi;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/29 22:09
 */
public class PrimeCalculationApp {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 1.线程服务提供者（线程池大小与主机核心默认是一样的）
        ExecutorService executor = Executors.newWorkStealingPool();
        // 2.可回调的线程的列表
        PrimeCalculationThread server1 = new PrimeCalculationThread(1, 2500);
        PrimeCalculationThread server2 = new PrimeCalculationThread(2501, 5000);
        PrimeCalculationThread server3 = new PrimeCalculationThread(5001, 7500);
        PrimeCalculationThread server4 = new PrimeCalculationThread(7501, 10000);
        List<Callable<Integer>> list = Arrays.asList(server1, server2, server3, server4);
        // 3.批量执行所有的线程
        Integer collect = executor.invokeAll(list)
                .stream()
                .map(future -> {
                    Integer sum = 0;
                    try {
                        sum = future.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return sum;
                }).collect(Collectors.summingInt(Integer::intValue));
        System.out.println("素数的数量 : " + collect);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }
}

/**
 * 范围内(列如小于等于1000)的多线程计算素数的方法
 */
class PrimeCalculationThread implements Callable<Integer> {
    private int start;
    private int end;

    public PrimeCalculationThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        int j;
        for (int i = start; i <= end; i++) {
            j = 2;
            for (; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j > Math.sqrt(i)) {
                // System.out.println(i);
                sum++;
            }
        }
        return sum;
    }
}
