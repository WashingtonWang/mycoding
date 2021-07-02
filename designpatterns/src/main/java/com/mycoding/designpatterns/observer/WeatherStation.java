package com.mycoding.designpatterns.observer;

import com.mycoding.designpatterns.observer.subpackage.impl.WeatherDataJava;

public class WeatherStation {
    public static void main(String[] args){
        //WeatherData weatherData = new WeatherData();
        //CurrentConditionsDisplay display = new CurrentConditionsDisplay(weatherData);
        //
        //weatherData.setMeasurements(80, 65, 30.4f);
        //weatherData.setMeasurements(82, 70, 29.2f);
        //weatherData.setMeasurements(78, 90, 29.3f);

        WeatherDataJava weatherDataJava = new WeatherDataJava();
        weatherDataJava.setMeasurements(80, 65, 30.4f);
        weatherDataJava.setMeasurements(82, 70, 29.2f);
        weatherDataJava.setMeasurements(78, 90, 29.3f);
    }
}
