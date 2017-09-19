package com.mycoding.designpatterns.strategy.impl;

import com.mycoding.designpatterns.strategy.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("FlyWithWings fly");
    }
}
