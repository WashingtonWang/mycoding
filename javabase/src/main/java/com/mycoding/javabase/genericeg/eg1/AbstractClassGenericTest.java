package com.mycoding.javabase.genericeg.eg1;

import java.io.Serializable;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/1/28 下午5:24
 */
public class AbstractClassGenericTest {
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        mainClass.setId(0L);
        mainClass.setName("main");
        System.out.println(mainClass);

        SubClassOne subClassOne = new SubClassOne();
        subClassOne.setId(1L);
        subClassOne.setName("one");
        subClassOne.setOne(1L);
        System.out.println(subClassOne);

        SubClassTwo subClassTwo = new SubClassTwo();
        subClassTwo.setId(2L);
        subClassTwo.setName("two");
        subClassTwo.setTwo(1L);
        System.out.println(subClassTwo);
    }
}

class MainClass<T> implements Serializable {
    private Long id;
    private String Name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

class SubClassOne extends MainClass<SubClassOne> implements Serializable{
    private Long one;

    public Long getOne() {
        return one;
    }

    public void setOne(Long one) {
        this.one = one;
    }
}

class SubClassTwo extends MainClass<SubClassTwo> implements Serializable{
    private Long two;

    public Long getTwo() {
        return two;
    }

    public void setTwo(Long two) {
        this.two = two;
    }
}