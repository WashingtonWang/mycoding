package com.mycoding.designpatterns.observer.subpackage.impl;

import com.mycoding.designpatterns.observer.obspackage.Observer;
import com.mycoding.designpatterns.observer.subpackage.Subject;

import java.util.ArrayList;

public class WeatherData implements Subject{

    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers = new ArrayList();
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("WeatherData registerObserver");
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i > 0){
            observers.remove(o);
        }
        System.out.println("WeatherData removeObserver");
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++){
            Observer observer = (Observer) observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
        System.out.println("WeatherData notifyObservers");
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    //WeaherData  其它方法
    void getTemperature(){

    }

    void getHumidity(){

    }

    void getPressure(){

    }
}
