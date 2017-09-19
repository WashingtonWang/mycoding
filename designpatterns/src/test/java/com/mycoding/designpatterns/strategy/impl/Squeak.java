package com.mycoding.designpatterns.strategy.impl;

import com.mycoding.designpatterns.strategy.QuackBehavior;

public class Squeak implements QuackBehavior {
    public void quack() {
        System.out.println("Squeak quack");
    }
}
