package com.mycoding.designpatterns.observer.displaypackage.impl;

import com.mycoding.designpatterns.observer.displaypackage.DisplayElement;
import com.mycoding.designpatterns.observer.subpackage.impl.WeatherDataJava;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplayJava implements Observer, DisplayElement {

    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplayJava(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: "+ temperature + "F degress and "+ humidity + "% humidity");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherDataJava){
            WeatherDataJava weatherDataJava = (WeatherDataJava) o;
            this.temperature = weatherDataJava.getTemperature();
            this.humidity = weatherDataJava.getHumidity();
            display();
        }
    }
}
