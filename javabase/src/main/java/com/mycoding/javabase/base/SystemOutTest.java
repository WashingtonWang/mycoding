package com.mycoding.javabase.base;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/12/25 下午12:41
 * 在 try 块或者 catch 语句中执行 return 语句时，finally 语句会执行；在 try 块或者 catch 语句中执行 System.exit() 时，
 * finally 语句不会执行。
 */
public class SystemOutTest {
    public static void main(String[] args) {
        returnTryExec();
        returnCatchExec();
        exitTryExec();
        exitCatchExec();
    }

    public static int returnTryExec() {
        try {
            return 0;
        } catch (Exception e) {
        } finally {
            System.out.println("finally returnTryExec");
            return -1;
        }
    }

    public static int returnCatchExec() {
        try {
        } catch (Exception e) {
            return 0;
        } finally {
            System.out.println("finally returnCatchExec");
            return -1;
        }
    }

    public static void exitTryExec() {
        try {
            System.exit(0);
        } catch (Exception e) {
        } finally {
            System.out.println("finally exitTryExec");
        }
    }

    public static void exitCatchExec() {
        try {
        } catch (Exception e) {
            System.exit(0);
        } finally {
            System.out.println("finally exitCatchExec");
        }
    }
}
