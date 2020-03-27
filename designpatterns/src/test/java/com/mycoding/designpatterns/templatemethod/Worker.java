package com.mycoding.designpatterns.templatemethod;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/3/24 11:29
 */
public class Worker extends Life {
  @Override
  public void work() {
    System.out.println("干活!");
  }

  @Override
  public void goToWork() {
    System.out.println("坐地铁去！");
  }

  @Override
  public void goHome() {
    System.out.println("坐地铁回！");
  }
}
