package com.mycoding.designpatterns.strategy.impl;

import com.mycoding.designpatterns.strategy.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("FlyNoWay fly");
    }
}
