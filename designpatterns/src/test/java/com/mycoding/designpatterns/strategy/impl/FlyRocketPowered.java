package com.mycoding.designpatterns.strategy.impl;

import com.mycoding.designpatterns.strategy.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("FlyRocketPowered fly");
    }
}
