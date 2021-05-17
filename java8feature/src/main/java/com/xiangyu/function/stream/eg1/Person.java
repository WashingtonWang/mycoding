package com.xiangyu.function.stream.eg1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by xiangyu.wang on 2017/7/1.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int no;
    private String name;
    private int age;
    private BigDecimal goodsQty;
    private String address;
}
