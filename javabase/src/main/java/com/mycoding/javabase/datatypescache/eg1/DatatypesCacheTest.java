package com.mycoding.javabase.datatypescache.eg1;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/9/26 09:33
 */
public class DatatypesCacheTest {

    /**
     * IntegerCache是Integer的表态内部类
     * */

    public static void main(String[] args) {
        //integerTest1();
        stringTest1();
    }

    /**
     * s1 和 s2 采用 new String() 的方式新建了两个不同字符串，而 s3 和 s4 是通过 s1.intern() 方法取得一个字符串引用。
     * intern() 首先把 s1 引用的字符串放到 String Pool 中，然后返回这个字符串引用。因此 s3 和 s4 引用的是同一个字符串。
     *
     * 如果是采用 "bbb" 这种字面量的形式创建字符串，会自动地将字符串放入 String Pool 中。
     *
     * 在 Java 7 之前，String Pool 被放在运行时常量池中，它属于永久代。而在 Java 7，String Pool 被移到堆中。
     * 这是因为永久代的空间有限，在大量使用字符串的场景下会导致 OutOfMemoryError 错误。
     *
     * new String("abc") 使用这种方式一共会创建两个字符串对象（前提是 String Pool 中还没有 "abc" 字符串对象）。
     * "abc" 属于字符串字面量，因此编译时期会在 String Pool 中创建一个字符串对象，指向这个 "abc" 字符串字面量；
     * 而使用 new 的方式会在堆中创建一个字符串对象。
     */
    public static void stringTest1(){
        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);           // false
        System.out.println(s1.equals(s2));
        String s3 = s1.intern();
        String s4 = s1.intern();
        System.out.println(s3 == s4);           // true

        String s5 = "bbb";
        String s6 = "bbb";
        System.out.println(s5 == s6);  // true
    }

    /**
     * Integer -128——127 缓存
     */
    public static void integerTest1() {
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);    // false
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
        System.out.println(z == k);   // true
    }
}
