package com.xiangyu.function.stream.eg1;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/7/22 17:41
 */
public class StreamSumTest {
    public static void main(String[] args) {
        Person p1 = Person.builder().goodsQty(new BigDecimal(12)).build();
        Person p2 = Person.builder().goodsQty(new BigDecimal(13)).build();
        Person p3 = Person.builder().goodsQty(null).build();
        List<Person> list = Arrays.asList(p1, p2, p3);
        test1(list);
    }

    public static void test1(List<Person> myList) {
        BigDecimal sum = myList.stream().map(p ->{
            return Optional.ofNullable(p.getGoodsQty()).orElse(BigDecimal.ZERO);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }
}
