package com.mycoding.javabase.passbyvalue;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/9/26 10:24
 */
public class PassByValueExample {


    /**
     * 静态语句块在类初始化时运行一次。
     */
    static {
        System.out.println("123456");
    }

    public static void main(String[] args) {

        /**
         * Java 的参数是以值传递的形式传入方法中，而不是引用传递。
         *
         * 以下代码中 Dog dog 的 dog 是一个指针，存储的是对象的地址。在将一个参数传入一个方法时，本质上是将对象的地址以值的方式传递到形参中。
         * 因此在方法中使指针引用其它对象，那么这两个指针此时指向的是完全不同的对象，在一方改变其所指向对象的内容时对另一方没有影响。
         */
        Dog dog = new Dog("A");
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        func(dog);
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        System.out.println(dog.getName());          // A

        PassByValueExample pbe = new PassByValueExample();
        PassByValueExample pbe2 = new PassByValueExample();
    }

    private static void func(Dog dog) {
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        dog = new Dog("B");
        System.out.println(dog.getObjectAddress()); // Dog@74a14482
        System.out.println(dog.getName());          // B
    }
}

class Dog {

    String name;

    Dog(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getObjectAddress() {
        return super.toString();
    }
}
