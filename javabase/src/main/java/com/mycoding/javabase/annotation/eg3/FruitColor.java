package com.mycoding.javabase.annotation.eg3;

import java.lang.annotation.*;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/7 13:43
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    public enum Color {
        BLUE, RED, GREEN
    };

    Color fruitColor() default Color.GREEN;
}
