package com.mycoding.javabase.exceptioneg.eg1;

/**
 *
 *  以下对所有的例子进行总结
    1 try、catch、finally语句中，在如果try语句有return语句，则返回的之后当前try中变量此时对应的值，
        此后对变量做任何的修改，都不影响try中return的返回值
    2 如果finally块中有return 语句，则返回try或catch中的返回语句忽略。
    3 如果finally块中抛出异常，则整个try、catch、finally块中抛出异常
         所以使用try、catch、finally语句块中需要注意的是
    1 尽量在try或者catch中使用return语句。通过finally块中达到对try或者catch返回值修改是不可行的。
    2 finally块中避免使用return语句，因为finally块中如果使用return语句，会显示的消化掉try、catch块中的异常信息，屏蔽了错误的发生
    3 finally块中避免再次抛出异常，否则整个包含try语句块的方法回抛出异常，并且会消化掉try、catch块中的异常

 * user: xiangyu.wang
 * date: 2018/3/20 14:32
 */
public class TryCatchFinally {

    public static String test1(){
        String t = "";
        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            //e.printStackTrace();
            return t;
        } finally {
            t = "finally";
        }
    }

    @SuppressWarnings("all")
    public static String test2(){
        String t = "";
        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            //e.printStackTrace();
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    @SuppressWarnings("all")
    public static String test3(){
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            //e.printStackTrace();
            return t;
        } finally {
            t = "finally";
            //return t;
        }
    }

    @SuppressWarnings("all")
    public static String test4(){
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            //e.printStackTrace();
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    @SuppressWarnings("all")
    public static String test5(){
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            //e.printStackTrace();
            return t;
        } finally {
            t = "finally";
            //return t;
        }
    }

    @SuppressWarnings("all")
    public static String test6(){
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            //e.printStackTrace();
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    @SuppressWarnings("all")
    public static String test7(){
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException  e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            //return t;
        }
    }

    @SuppressWarnings("all")
    public static String test8(){
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException  e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    @SuppressWarnings("all")
    public static String test9(){
        String t = "";
        try {
            t = "try";
            return t;
        } catch (NullPointerException  e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            String.valueOf(null);
            return t;
        }
    }

    public static void main(String[] args) {
        //System.out.println("test1: " + TryCatchFinally.test1());
        //System.out.println("test2: " + TryCatchFinally.test2());
        //System.out.println("test3: " + TryCatchFinally.test3());
        //System.out.println("test4: " + TryCatchFinally.test4());
        //System.out.println("test5: " + TryCatchFinally.test5());
        //System.out.println("test6: " + TryCatchFinally.test6());
        //System.out.println("test7: " + TryCatchFinally.test7());
        //System.out.println("test8: " + TryCatchFinally.test8());
        //System.out.println("test9: " + TryCatchFinally.test9());
    }
}
