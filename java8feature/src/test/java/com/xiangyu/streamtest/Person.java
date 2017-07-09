package com.xiangyu.streamtest;

/**
 * Created by xiangyu.wang on 2017/7/1.
 */
public class Person {
    private int no;
    private String name;
    private int age;

    public Person(int no, String name, int age) {
        this.no = no;
        this.name = name;
        this.age = age;
    }

    public int getAge(){
        //System.out.println(age);
        return age;
    }
    public Person(int no, String name) {
        this.no = no;
        this.name = name;
    }
    public String getName() {
        //System.out.println(name);
        return name;
    }
}
