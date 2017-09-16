package designpattern.strategy.impl;

import designpattern.strategy.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("FlyWithWings fly");
    }
}
