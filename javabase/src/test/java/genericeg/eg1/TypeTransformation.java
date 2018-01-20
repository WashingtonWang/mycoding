package genericeg.eg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  逆变与协变用来描述类型转换（type transformation）后的继承关系，
 *  其定义：如果A、B表示类型，f(⋅)表示类型转换，≤表示继承关系（比如，A≤B表示A是由B派生出来的子类）
 *  f(⋅)是逆变（contravariant）的，当A≤B时有f(B)≤f(A)成立；
 *  f(⋅)是协变（covariant）的，当A≤B时有f(A)≤f(B)成立；
 *  f(⋅)是不变（invariant）的，当A≤B时上述两个式子均不成立，即f(A)与f(B)相互之间没有继承关系。
 *
 *  作者：开发者小王
 *  链接：https://www.jianshu.com/p/2bf15c5265c5
 *  來源：简书
 *  著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 *  注意：Java中数组是协变的，可以向子类型的数组赋予基类型的数组引用 但是泛型是不变的
 * user: xiangyu.wang
 * date: 2018/1/10 9:16
 */
public class TypeTransformation {
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[12];
        fruit[0] = new Apple();
        fruit[0] = new Jonathan();
        try {
            fruit[0] = new Fruit();
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            fruit[0] = new Orange();
        }catch (Exception e){
            System.out.println(e);
        }

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1);
        System.out.println(c1 == c2);

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple());
        readFrom(appleList);
    }

    /**
     * 原因在于，List<? extends Fruit>也可以合法的指向一个List<Orange>，
     * 显然往里面放Apple、Fruit、Object都是非法的。编译器不知道List<? extends Fruit>所持有的具体类型是什么，
     * 所以一旦执行这种类型的向上转型，你就将丢失掉向其中传递任何对象的能力。
     * 作者：开发者小王
     * 链接：https://www.jianshu.com/p/2bf15c5265c5
     * 來源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param fruits
     */
    //顺变
    static void readTo(List<? extends Fruit> fruits){
        //fruits.add(new Apple());//编译不通过
        //fruits.add(new Fruit());//编译不通过
        //fruits.add(new Jonathan());//编译不通过
    }

    //逆变
    static void writeTo(List<? super Apple> apples){
        apples.add(new Apple());
        apples.add(new Jonathan());
        //apples.add(new Fruit()); //编译不通过
    }

    static void readFrom(List<? extends Apple> apples){
        Apple apple = apples.get(0);
        Fruit fruit = apples.get(0);
        //Jonathan jonathan = apples.get(0); //编译不能过
        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(new Apple());
        fruitList.add(new Apple());
        List<Apple> appleList = new ArrayList<>();
        //appleList.add(new Apple());
        //appleList.add(new Apple());
        Collections.copy(fruitList, appleList);
        //Collections.copy(appleList, fruitList);
    }
}

class Fruit{  }
class Apple extends Fruit{  }
class Jonathan extends Apple{ }
class Orange extends Fruit{ }
