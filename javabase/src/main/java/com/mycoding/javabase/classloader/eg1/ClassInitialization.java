package com.mycoding.javabase.classloader.eg1;

import java.util.Random;

/**
 * user: xiangyu.wang
 * date: 2018/1/11 17:54
 */
public class ClassInitialization {
    public static Random rand = new Random(47);

    public static void main(String[] args) throws Exception{
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");

        System.out.println(Initable.staticFinal);

        System.out.println(Initable.staticFinal2);

        System.out.println(Initable2.staticNonFianl);

        Class initable3 = Class.forName("com.mycoding.javabase.classloader.eg1.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}

class Initable{
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initizlizing Initable");
    }
}

class Initable2{
    static int staticNonFianl = 147;
    static {
        System.out.println("Initizlizing Initable2");
    }
}

class Initable3{
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}


