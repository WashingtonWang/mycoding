package designpattern.strategy;

import designpattern.strategy.duck.Duck;
import designpattern.strategy.duck.ModelDuck;
import designpattern.strategy.impl.FlyRocketPowered;

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

