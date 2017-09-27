package com.mycoding.designpatterns.observer.obspackage.impl;

import com.mycoding.designpatterns.observer.displaypackage.DisplayElement;
import com.mycoding.designpatterns.observer.obspackage.Observer;

public class ForecastDisplay implements Observer, DisplayElement {
    @Override
    public void display() {
        System.out.println("ForecastDisplay display");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        //显示天气预报
        System.out.println("ForecastDisplay update");
    }
}
