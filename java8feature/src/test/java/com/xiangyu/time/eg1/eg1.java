package com.xiangyu.time.eg1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: java8 新增加的 time 库下的类   《写给大忙人的 javase8》
 * @Auther: wangxiangyu
 * @Date: 2018/1/11 21:49
 */
public class eg1 {
    public static void main(String[] args) throws InterruptedException {
        //java8 新增加的 time 库下的类
        //Instant start = Instant.now();
        //Thread.sleep(3000);
        //Instant end = Instant.now();
        //Duration timeElapsed = Duration.between(start, end);
        //long millis = timeElapsed.toMillis();
        //System.out.println(millis);

        LocalDate today = LocalDate.now();//今天的日期
        System.out.println(today);

        LocalDate programmersDay = LocalDate.of(2017, 1, 1).plusDays(256);
        System.out.println(programmersDay);

        //工作日的校正器：
        //TemporalAdjuster NEXT_WORKDAY = w ->{  //这里可以改为下面的代码
        //    LocalDate result = (LocalDate) w;   //
        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w; //不需要上面的类型转换
            do {
                result = result.plusDays(1);
            }while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });

        //LocalTime 表示一天中的某个时间
        LocalTime rightNow = LocalTime.now();
        System.out.println(rightNow);
        LocalTime bedtTime = LocalTime.of(22, 30);
        System.out.println(bedtTime);
    }
}
