package com.mycoding.designpatterns.strategy;

import com.mycoding.designpatterns.strategy.duck.Duck;
import com.mycoding.designpatterns.strategy.duck.ModelDuck;
import com.mycoding.designpatterns.strategy.impl.FlyRocketPowered;

public class RunMain {
    public static void main(String[] args){
        //Duck d1 = new MallardDuck();
        //d1.performQuack();
        //d1.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}

