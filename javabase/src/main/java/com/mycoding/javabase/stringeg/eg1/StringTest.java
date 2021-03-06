package com.mycoding.javabase.stringeg.eg1;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/15 09:50
 *
 * 总结
 * 1.只在常量池上创建常量
 *     String a1 = "AA";
 *
 * 2.只在堆上创建对象
 *     String a2 = new String("A") + new String("A");
 *
 * 3.在堆上创建对象，在常量池上创建常量
 *     String a3 = new String("AA");
 *
 * 4.在堆上创建对象，在常量池上创建引用
 *     String a4 = new String("A") + new String("A");//只在堆上创建对象AA
 *     a4.intern();//将该对象AA的引用保存到常量池上
 *
 * 5.在堆上创建对象，在常量池上创建常量，在常量池上创建引用（不可能）
 *     String a5 = new String("A") + new String("A");//只在堆上创建对象
 *     a5.intern();//在常量池上创建引用
 *     String a6 = "AA";//此时不会再在常量池上创建常量AA，而是将a5的引用返回给a6
 *     System.out.println(a5 == a6); //true
 *
 *
 * 6.
 * 	            常量池上常量 	常量池上引用 	堆上对象
 *  常量池上常量 	    X 	    不共存 	    共存
 *  常量池上引用  	不共存 	X 	        共存
 *    堆上对象 	    共存 	共存 	    X
 * ————————————————
 * 版权声明：本文为CSDN博主「小页睡着了zZ」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/u013366617/article/details/83618361
 */
public class StringTest {
    public static void main(String[] args) {
        //test1();
        //test3();
        //test4();
        //test5();
        test6();
        //internTest1();
    }

    /**
     * 1.直接使用双引号创建字符串
     * <p>
     *  判断这个常量是否存在于常量池，
     *   如果存在，
     *    判断这个常量是存在的引用还是常量，
     *     如果是引用，返回引用地址指向的堆空间对象，
     *     如果是常量，则直接返回常量池常量，
     *   如果不存在，
     *     在常量池中创建该常量，并返回此常量
     */
    public static void test1() {
        String a1 = "AA";//在常量池上创建常量AA
        String a2 = "AA";//直接返回已经存在的常量AA
        System.out.println(a1 == a2); //true

        String a3 = new String("QQ");    //在堆上创建对象AA
        a3.intern(); //在常量池上创建对象AA的引用
        String a4 = "QQ"; //常量池上存在引用AA，直接返回该引用指向的堆空间对象，即a3
        System.out.println(a3 == a4); //false
    }

    /**
     * 2.new String创建字符串
     * <p>
     *  首先在堆上创建对象(无论堆上是否存在相同字面量的对象),
     *  然后判断常量池上是否存在字符串的字面量，
     *   如果不存在
     *    在常量池上创建常量
     *   如果存在
     *    不做任何操作
     */
    public static void test3() {
        String a1 = new String("BB");
        String a2 = new String("BB");
        System.out.println(a1 == a2); //false

        //如果常量池上不存在常量AA,也不存在引用AA，则创建常量AA
        String a3 = new String("CC");
        System.out.println(a3 == a3.intern()); //false
    }

    /**
     * 3.双引号相加
     * <p>
     *  判断这两个常量、相加后的常量在常量池上是否存在
     *   如果不存在
     *    则在常量池上创建相应的常量
     *   如果存在
     *    判断这个常量是存在的引用还是常量，
     *     如果是引用，返回引用地址指向的堆空间对象，
     *     如果是常量，则直接返回常量池常量，
     */
    public static void test4() {
        String a1 = "DD" + "FF";//在常量池上创建常量AA、BB和AABB，并返回AABB

        //常量池上存在常量AABB
        String a2 = "DDFF";
        String a3 = "DD" + "FF";
        System.out.println(a2 == a3); //true

        //常量池上存在引用AABB
        String a4 = new String("GG") + new String("HH"); //在堆上创建对象AA、BB和AABB，在常量池上创建常量AA和BB
        a4.intern();
        String a5 = "GG" + "HH";
        System.out.println(a4 == a5); //true

    }

    /**
     * 4.两个new String相加
     *
     *  首先会创建这两个对象以及相加后的对象
     *  然后判断常量池中是否存在这两个对象的字面量常量
     *   如果存在
     *    不做任何操作
     *   如果不存在
     *    则在常量池上创建对应常量
     */
    public static void test5() {
        //常量AA不存在，所以第一步在常量池中创建了常量AA
        String a2 = new String("II") + new String("JJ");
        String a3 = new String("I") + new String("I"); //创建对象AA
        System.out.println(a3 == a3.intern()); //false

        //只在堆上创建AABB对象，没有在常量池中创建常量AABB
        String a4 = new String("KK") + new String("KK");
        System.out.println(a4 == a4.intern()); //true
    }

    /**
     * 5.双引号字符串与new String字符串
     *
     *  首先创建两个对象，一个是new String的对象，一个是相加后的对象
     *  然后判断双引号常量与new String的字面量在常量池是否存在
     *   如果存在
     *    不做操作
     *   如果不存在
     *    则在常量池上创建对象的常量
     */
    public static void test6() {
        String a1 = "LLMM";
        String a2 = "LL" + new String("MM");
        System.out.println(a1 == a2.intern());//true
        System.out.println(a2 == a2.intern()); //false

    }

    /**
     * String.intern()分析
     * <p>
     *  判断这个常量是否存在于常量池。
     *   如果存在
     *    判断存在内容是引用还是常量，
     *     如果是引用，
     *      返回引用地址指向堆空间对象，
     *     如果是常量，
     *      直接返回常量池常量
     *   如果不存在，
     *    将当前对象引用复制到常量池,并且返回的是当前对象的引用
     * ————————————————
     * 版权声明：本文为CSDN博主「小页睡着了zZ」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/u013366617/article/details/83618361
     */
    public static void internTest1() {
        String a1 = "NN";
        System.out.println(a1 == a1.intern()); //true
        String a2 = new String("O") + new String("O");
        a2.intern();
        String a3 = new String("O") + new String("O");
        System.out.println(a2 == a3.intern());//true
        System.out.println(a3 == a3.intern());//false
        String a4 = new String("P") + new String("P");
        System.out.println(a4 == a4.intern()); //true
    }

    public static void test2() {
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = new String("abc");
        String s4 = new String("abc").intern();

        System.out.println("s1 == s2 " + s1 == s2);
        System.out.println("s1 == s4 " + s1 == s4);
        System.out.println("s2 == s3 " + s2 == s3);
        System.out.println("s2 == s4 " + s2 == s4);
    }
}
