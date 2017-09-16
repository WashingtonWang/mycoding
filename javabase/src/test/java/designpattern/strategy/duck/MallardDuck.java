package designpattern.strategy.duck;

import designpattern.strategy.impl.FlyWithWings;
import designpattern.strategy.impl.Quack;

public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void swim() {
        System.out.println("MallardDuck swim");
    }

    public void display() {
        System.out.println("MallardDuck display");
    }

}
