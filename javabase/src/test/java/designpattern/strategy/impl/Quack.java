package designpattern.strategy.impl;

import designpattern.strategy.QuackBehavior;

public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack quack");
    }
}
