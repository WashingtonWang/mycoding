package com.mycoding.designpatterns.observer.obspackage.impl;

import com.mycoding.designpatterns.observer.displaypackage.DisplayElement;
import com.mycoding.designpatterns.observer.obspackage.Observer;

public class ThirdPartyDisplay implements Observer, DisplayElement
{
    @Override
    public void display() {
        //显示基于观测值的其它内容
        System.out.println("ThirdPartyDisplay update");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("ThirdPartyDisplay update");
    }
}
