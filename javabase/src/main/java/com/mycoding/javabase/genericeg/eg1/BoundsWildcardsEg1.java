package com.mycoding.javabase.genericeg.eg1;

/**
 * 泛型通配符
 * user: xiangyu.wang
 * date: 2018/3/16 13:36
 */
public class BoundsWildcardsEg1 {

    public static void main(String[] args) {
        //extends 通配符只能取  不能存
        Plate<? extends FruitEg1> p = new Plate<>(new AppleEg1());
        //p.setItem(new FruitEg1());
        //p.setItem(new BananaEg1());
        FruitEg1 fe = p.getItem();
        //super 通配符只能存  不能取
        Plate<? super FruitEg1> ps = new Plate<>(new FruitEg1());
        ps.setItem(new FruitEg1());
        ps.setItem(new AppleEg1());
        //ps.setItem(new FoodEg1());
        AppleEg1 ae = (AppleEg1) ps.getItem();
        FruitEg1 fe1 = (FruitEg1) ps.getItem();

    }

}
//level 1
class FoodEg1{ }

//level 2
class FruitEg1 extends FoodEg1{ }
class MeatEg1 extends FoodEg1{ }

//level 3
class AppleEg1 extends FruitEg1{ }
class BananaEg1 extends FruitEg1{ }
class PortEg1 extends MeatEg1{ }
class BeefEg1 extends MeatEg1{ }

//level 4
class RedAppleEg1 extends AppleEg1{ }
class GreenAppleEg1 extends AppleEg1{ }


class Plate<T>{
    private T item;
    public Plate(T t){
        this.item = t;
    }
    public void setItem(T t){
        this.item = t;
    }
    public T getItem(){
        return item;
    }
}



