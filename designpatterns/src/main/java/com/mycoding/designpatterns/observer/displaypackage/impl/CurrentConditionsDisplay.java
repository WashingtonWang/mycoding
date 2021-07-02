package com.mycoding.designpatterns.observer.displaypackage.impl;

import com.mycoding.designpatterns.observer.displaypackage.DisplayElement;
import com.mycoding.designpatterns.observer.obspackage.Observer;
import com.mycoding.designpatterns.observer.subpackage.Subject;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: "+ temperature + "F degress and "+ humidity + "% humidity");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
}
