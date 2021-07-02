package com.mycoding.javabase.abstractclass;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/2/7 下午12:51
 */
public class AbstractClassTest {
    public static void main(String[] args) {
        AbstractClassRoot root = new SubClass();
        root.doThings();
    }
}

abstract class AbstractClassRoot {
    public void todo(String things) {
        System.out.println("root: " + things);
    }

    abstract void doThings();
}

class SubClass extends AbstractClassRoot {

    @Override
    void doThings() {
        System.out.println("subClass");
    }
}
