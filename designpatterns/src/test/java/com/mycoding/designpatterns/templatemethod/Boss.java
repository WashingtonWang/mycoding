package com.mycoding.designpatterns.templatemethod;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/3/24 11:27
 */
public class Boss extends Life {
  @Override
  public void work() {
    System.out.println("给员工分配工作！");
  }

  @Override
  public void goToWork() {
    System.out.println("去开车!");
  }

  @Override
  public void goHome() {
    System.out.println("开车回!");
  }

}