package com.mycoding.javabase.classeg.eg;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/1/9 下午11:16
 */
public class ClassEg1 {
    public static void main(String[] args) {
        //isAssignableFromTest();
        //superClassTest();
        //packageTest();
        //getInterfacesTest();
        getGenericInterfacesTest();
    }

    private static void getInterfacesTest(){
        A a = new A();
        C c = new F();
        Class<?>[] classes = a.getClass().getInterfaces();
        System.out.println(classes);

        Class<?>[] classes1 = c.getClass().getInterfaces();
        System.out.println(classes1);
    }

    private static void getGenericInterfacesTest(){
        A a = new A();
        C c = new F();
        Type[] classes = a.getClass().getGenericInterfaces();
        System.out.println(classes);

        Type[] classes1 = c.getClass().getGenericInterfaces();
        System.out.println(classes1);
        System.out.println(c.getClass().getEnclosingMethod());
    }

    private static void packageTest() {
        A a = new A();
        C c = new F();
        System.out.println(a.getClass().getPackage());
        System.out.println(c.getClass().getPackage());
    }

    //test superClass method
    private static void superClassTest() {
        A a = new A();
        B b = new B();
        C c = new F();

        System.out.println("a-> " + a.getClass().getSuperclass());
        System.out.println("b-> " + b.getClass().getSuperclass());
        System.out.println("c-> " + c.getClass().getSuperclass());
    }

    //test toString method
    private static void classToStringTest() {
        List<String> list = new ArrayList<>();

        Integer it = 1;
        System.out.println(it.getClass().toString());
        System.out.println(it.getClass().toGenericString());

        System.out.println(list.getClass().toString());
        System.out.println(list.getClass().toGenericString());
    }

    private static void isAssignableFromTest() {
        A a = new A();
        B b = new B();
        A ba = new B();
        System.out.println("1-------------");
        System.out.println(A.class.isAssignableFrom(a.getClass()));
        System.out.println(B.class.isAssignableFrom(b.getClass()));
        System.out.println(A.class.isAssignableFrom(b.getClass()));
        System.out.println(B.class.isAssignableFrom(a.getClass()));
        System.out.println(A.class.isAssignableFrom(ba.getClass()));
        System.out.println(B.class.isAssignableFrom(ba.getClass()));
        System.out.println("2-------------");
        System.out.println(a.getClass().isAssignableFrom(A.class));
        System.out.println(b.getClass().isAssignableFrom(B.class));
        System.out.println(a.getClass().isAssignableFrom(B.class));
        System.out.println(b.getClass().isAssignableFrom(A.class));
        System.out.println(ba.getClass().isAssignableFrom(A.class));
        System.out.println(ba.getClass().isAssignableFrom(B.class));
        System.out.println("3-------------");
        System.out.println(Object.class.isAssignableFrom(b.getClass()));
        System.out.println(Object.class.isAssignableFrom("abc".getClass()));
        System.out.println("4-------------");
        System.out.println("a".getClass().isAssignableFrom(Object.class));
        System.out.println("abc".getClass().isAssignableFrom(Object.class));
    }
}


class A {

}

class B extends A {
}

interface C {

}

interface D extends C {
}

class F extends A implements D {
}
