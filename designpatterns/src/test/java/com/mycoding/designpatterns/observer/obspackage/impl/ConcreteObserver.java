package com.mycoding.designpatterns.observer.obspackage.impl;

import com.mycoding.designpatterns.observer.obspackage.Observer;

public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        System.out.println("update");
    }
}
