package com.mycoding.javabase.atinterface.eg1;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FirstAnnotation {
    String value() default "FirstAnnotation";
}
