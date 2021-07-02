package com.mycoding.javabase.staticeg;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/2/20 下午1:04
 */
public class StaticBlock {
    public StaticBlock() {
        System.out.print("默认构造方法！--");
    }

    //非静态代码块
    {
        System.out.print("非静态代码块！--");
    }

    //静态代码块
    static {
        System.out.print("静态代码块！--");
    }

    private static void test() {
        System.out.print("静态方法中的内容! --");
        {
            System.out.print("静态方法中的代码块！--");
        }

    }

    public static void main(String[] args) {
        StaticBlock test = new StaticBlock();
        StaticBlock.test();//静态代码块！--静态方法中的内容! --静态方法中的代码块！--
    }
}
