package com.mycoding.designpatterns.observer.obspackage.impl;

import com.mycoding.designpatterns.observer.displaypackage.DisplayElement;
import com.mycoding.designpatterns.observer.obspackage.Observer;

public class StatisicsDisplay implements Observer, DisplayElement{
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("StatisicsDisplay update");
    }

    @Override
    public void display() {
        //显示最小、平均和最大的观测值
        System.out.println("StatisicsDisplay display");
    }
}
