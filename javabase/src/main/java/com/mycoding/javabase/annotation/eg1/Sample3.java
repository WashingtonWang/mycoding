package com.mycoding.javabase.annotation.eg1;

import java.util.ArrayList;
import java.util.List;

public class Sample3 {

    @ExceptionTest({
            IndexOutOfBoundsException.class,
            NullPointerException.class
    })
    public static void doublyBad(){
        List<String> list = new ArrayList<>();
        list.addAll(5,null);
    }
}
