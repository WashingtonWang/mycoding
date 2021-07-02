package com.mycoding.javabase.classloader.eg1;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/3/27 15:16
 */
public class ClassloaderDemo {
  public static void main(String[] args) {
    System.out.println("ClassLodarDemo's ClassLoader is " + ClassloaderDemo.class.getClassLoader());
    System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassloaderDemo.class.getClassLoader().getParent());
    System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassloaderDemo.class.getClassLoader().getParent().getParent());
  }
}
