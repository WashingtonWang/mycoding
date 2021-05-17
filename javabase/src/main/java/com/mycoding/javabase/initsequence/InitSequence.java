package com.mycoding.javabase.initsequence;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/9/26 13:49
 * 初始化顺序
 */
public class InitSequence {

    /**
     * 静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
     * 最后才是构造函数的初始化。
     *
     *     存在继承的情况下，初始化顺序为：
     *     父类（静态变量、静态语句块）
     *     子类（静态变量、静态语句块）
     *     父类（实例变量、普通语句块）
     *     父类（构造函数）
     *     子类（实例变量、普通语句块）
     *     子类（构造函数）
     */

    public static String staticField = "静态变量";

    static {
        System.out.println("静态语句块");
    }

    public String field = "实例变量";

    {
        System.out.println("普通语句块");
    }

    public InitSequence() {
        System.out.println("构造函数");
    }

    public static void main(String[] args) {
        InitSequence is = new InitSequence();
        System.out.println(staticField);
        System.out.println(is.field);
        System.out.println(is);
    }
}
