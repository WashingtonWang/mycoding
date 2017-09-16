package designpattern.strategy.impl;

import designpattern.strategy.QuackBehavior;

public class Squeak implements QuackBehavior {
    public void quack() {
        System.out.println("Squeak quack");
    }
}
