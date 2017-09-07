package designpattern.strategy.impl;

import designpattern.strategy.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("FlyRocketPowered fly");
    }
}
