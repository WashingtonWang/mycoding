package com.mycoding.designpatterns.observer.subpackage.impl;

import com.mycoding.designpatterns.observer.subpackage.Subject;

public class ConcreteSubject implements Subject{

    private int state;

    @Override
    public void registerObserver() {
        System.out.println("registerObserver");
    }

    @Override
    public void removeObserver() {
        System.out.println("removeObserver");
    }

    @Override
    public void notfyObservers() {
        System.out.println("notfyObservers");
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
