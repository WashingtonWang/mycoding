package com.mycoding.designpatterns.strategy.duck;

import com.mycoding.designpatterns.strategy.impl.FlyWithWings;
import com.mycoding.designpatterns.strategy.impl.Quack;

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
