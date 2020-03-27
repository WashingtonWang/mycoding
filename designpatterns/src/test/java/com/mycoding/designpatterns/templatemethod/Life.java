package com.mycoding.designpatterns.templatemethod;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/3/24 11:25
 * 说明：可以看到，抽象类中定义了公共的方法起床；也定义了去工作和回家的两个子类不必实现的方法；
 * 还有一个具体工作的方法，必须由子类去实现；而一天的生活，通过oneday方法已经定义好了，执行顺序也是固定的；
 * 也就是所有子类的方法调用顺序都是固定的了，这就是模板方法。
 *
 * 说明：可以看到，不管是老板还是员工，都必须实现一个具体工作的方法，因为他们的工作内容是完全不一样的；
 * 而上下班的交通工具却可以自己选择实现或不实现；起床又是公有的，所以定义在了抽象类中，这里也不需要去实现。
 *
 * 优缺点及适用场景
 * 优点：模板方法所不变的行为搬到超类，去除子类中的重复代码，提供了一个很好了代码复用平台；
 * 缺点：当类的功能越来越多，变得复杂时，抽象类的管理和扩展就变得复杂了。
 * 适用场景：一次性实现一个算法的不变的部分，并将可变的行为留给子类来实现。
 * 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复。
 */
public abstract class Life {

  private void getUp(){
    System.out.println("早上起床！");
  }

  public void goHome(){

  }

  public abstract void work();

  public void goToWork(){}

  public void oneDay(){
    getUp();
    goToWork();
    work();
    goHome();
  }

  public static void main(String[] args) {
    Boss boss = new Boss();
    boss.oneDay();
    System.out.println("-----------------------------");
    Worker worker = new Worker();
    worker.oneDay();
  }
}
