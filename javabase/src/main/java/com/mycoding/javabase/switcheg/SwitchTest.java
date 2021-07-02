package com.mycoding.javabase.switcheg;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/1/27 上午11:53
 */
public class SwitchTest {
    public static void main(String[] args) {
        switchExe();
        System.out.println("main done");
    }

    public static void switchExe() {
        int i = 1;
        switch (i) {
            case 0:
                System.out.println(i);
                break;
            case 1:
                System.out.println(i);
                break;
            case 2:
                System.out.println(i);
                break;
        }
        System.out.println("exe done");
    }
    
    
}


