package com.mycoding.integertest;

/**
 * Created by wangxiangyu on 2017/7/22.
 */
public class IntegerTest {

    public static void main(String[] args){
        Integer a = 100;
        Integer b = 200;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        long g = 300L;
        //System.out.println(c == d);
        //System.out.println(e == f);
        //System.out.println(c == (a+b));
        //System.out.println(c.equals(a+b));
        //System.out.println(g == (a+b));
        //System.out.println(g.equals(a+b));
        //Integer x = 123456;
        //int ab = 123456;
        //System.out.println(x == ab);
        //System.out.println(x.equals(ab));
        //System.out.println(g == (a+b));
        //Integer aa = 128;
        //Integer bb = 128;
        //Long aa = 128L;
        //Long bb = 128l;
        //String aa = new String("aa");
        String aa = new String("a")+"a";
        String bb = "aa";
        System.out.println(aa == bb);
        System.out.println(aa.equals(bb));

    }

}
