package com.mycoding.javabase.atinterface.eg1;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecondAnnotation {
    String name() default "Hrmzone";
    String url() default "hrmzone.cn";
}
