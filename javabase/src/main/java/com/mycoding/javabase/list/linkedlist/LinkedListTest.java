package com.mycoding.javabase.list.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/1/8 21:05
 */
public class LinkedListTest {

  public static void main(String[] args) {
    descendingIteratorTest();
  }

  public static void descendingIteratorTest(){
    LinkedList<String> list1 = new LinkedList<>();
    list1.add("1");
    list1.add("2");
    list1.add("3");
    list1.add("4");
    list1.add("5");

    list1.forEach(s -> System.out.print(s + " "));
    System.out.println();

    Iterator<String> it = list1.descendingIterator();
    while (it.hasNext()){
      System.out.print(it.next() + " ");
    }
    System.out.println();
  }

}
