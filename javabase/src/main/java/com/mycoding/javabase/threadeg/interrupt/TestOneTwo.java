package com.mycoding.javabase.threadeg.interrupt;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/4/29 07:56
 */
public class TestOneTwo {
    private Long age;
    public String name;
    private static String aa;

    public static class Interr implements Test {
        public void sout1() {
            System.out.println(aa);
        }
    }

    public class In {
        public void sout2() {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        Interr interr = new Interr();
        interr.ss();

        Test te = new Interr();
        te.ss();

        Test.st();
    }
}

interface Test {
    default void ss() {
        System.out.println("1 11");
    }

    static void st() {
        System.out.println("222");
    }
}
