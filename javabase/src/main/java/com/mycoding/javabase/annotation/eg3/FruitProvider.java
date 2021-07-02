package com.mycoding.javabase.annotation.eg3;

import java.lang.annotation.*;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/7 13:46
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {

    public int id() default -1;

    public String name() default "";

    public String address() default "";

}
