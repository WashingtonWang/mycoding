package com.xiangyu.function.stream.eg1;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xiangyu.wang on 2017/7/1.
 */
@Data
@Builder
public class Person {
    private int no;
    private String name;
    private int age;
}
