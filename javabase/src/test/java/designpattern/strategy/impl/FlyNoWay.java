package designpattern.strategy.impl;

import designpattern.strategy.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("FlyNoWay fly");
    }
}
