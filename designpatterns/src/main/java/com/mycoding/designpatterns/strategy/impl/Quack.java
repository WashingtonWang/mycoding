package com.mycoding.designpatterns.strategy.impl;

import com.mycoding.designpatterns.strategy.QuackBehavior;

public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack quack");
    }
}
