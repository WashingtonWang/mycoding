package designpattern.strategy.duck;

import designpattern.strategy.impl.FlyNoWay;
import designpattern.strategy.impl.Quack;

public class ModelDuck extends Duck{

    public ModelDuck(){
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    void swim() {
        System.out.println("ModelDuck swim");
    }

    @Override
    void display() {
        System.out.println("ModelDuck display");
    }
}
