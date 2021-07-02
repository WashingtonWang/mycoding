package com.mycoding.javabase.dynamicproxy.eg1;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 代理模式是设计模式中非常重要的一种类型，而设计模式又是编程中非常重要的知识点，
 * 特别是在业务系统的重构中，更是有举足轻重的地位。代理模式从类型上来说，可以分为静态代理和动态代理两种类型。
 *
 * 对于客人来说，他肯定希望我们所有的产品都有一层杏仁，这样客人最喜欢了。
 * 为了满足客人的需求，那如果我们的产品有 100 种（饼干、酸奶等），我们是不是得写 100 个代理类呢？
 * 有没有一种方式可以让我们只写一次实现（撒杏仁的实现），但是任何类型的产品（蛋糕、面包、饼干、酸奶等）都可以使用呢？
 * 其实在 Java 中早已经有了针对这种情况而设计的一个接口，专门用来解决类似的问题，它就是动态代理 —— InvocationHandler。
 *
 * 动态代理与静态代理的区别是静态代理只能针对特定一种产品（蛋糕、面包、饼干、酸奶）做某种代理动作（撒杏仁），
 * 而动态代理则可以对所有类型产品（蛋糕、面包、饼干、酸奶等）做某种代理动作（撒杏仁）。
 *
 *
 * 动态代理的几种实现方式:
 * 动态代理其实指的是一种设计模式概念，指的是通过代理来做一些通用的事情，常见的应用有权限系统、日志系统等，都用到了动态代理。
 * 而 Java 动态代理只是动态代理的一种实现方式而已，动态代理还有另外一种实现方式，即 CGLib（Code Generation Library）。
 * Java 动态代理只能针对实现了接口的类进行拓展，所以细心的朋友会发现我们的代码里有一个叫 MachineCake 的接口。
 * 而 CGLib 则没有这个限制，因为 CGLib 是使用继承原有类的方式来实现代理的。
 *
 * 对比 Java 动态代理和 CGLib 动态代理两种实现方式，你会发现 Java 动态代理适合于那些有接口抽象的类代理，
 * 而 CGLib 则适合那些没有接口抽象的类代理。
 * @Auther: wangxiangyu
 * @Date: 2018/1/28 15:49
 */
//蛋糕店
public class CakeShop {
    public static void main(String[] args) {
        /******静态代理 start*****/
        //可以给各种各样的蛋糕加上杏仁
        System.out.println("静态代理=============================");
        FruitCakeMachine fruitCakeMachine = new FruitCakeMachine();
        ApricotCakeProxy apricotCakeProxy = new ApricotCakeProxy(fruitCakeMachine);
        apricotCakeProxy.makeCake();
        apricotCakeProxy = new ApricotCakeProxy(new ChocolateCakeMachine());
        apricotCakeProxy.makeCake();
        //可以给各种各样的面包加上杏仁
        RedBeanBreadMachine redBeanBreadMachine = new RedBeanBreadMachine();
        ApricotBreadProxy apricotBreadProxy = new ApricotBreadProxy(redBeanBreadMachine);
        apricotBreadProxy.makeBread();
        CurrantBreadMachine currantBreadMachine = new CurrantBreadMachine();
        apricotBreadProxy = new ApricotBreadProxy(currantBreadMachine);
        apricotBreadProxy.makeBread();
        /******静态代理 end*****/
        /*********java动态代理 start*********/
        //动态代理(可以同时给蛋糕、面包等加杏仁)
        //给蛋糕加上杏仁
        System.out.println("java动态代理=============================");
        FruitCakeMachine fcm = new FruitCakeMachine();
        ApricotHandler apricotHandler = new ApricotHandler(fcm);
        CakeMachine cm = (CakeMachine) Proxy.newProxyInstance(fcm.getClass().getClassLoader(),
                fcm.getClass().getInterfaces(), apricotHandler);
        cm.makeCake();
        /*********java动态代理 end*********/
        /*********cglib动态代理 start*********/
        //CGLib动态代理(可以同时给蛋糕、面包等加杏仁)
        System.out.println("cglib动态代理=============================");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FruitCakeMachine.class);
        enhancer.setCallback(new ApricotInterceptor());
        FruitCakeMachine fcmCglib = (FruitCakeMachine) enhancer.create();
        fcmCglib.makeCake();

        /*********cglib动态代理 end*********/
    }
}

class ApricotInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        methodProxy.invokeSuper(o, objects);
        System.out.println("adding apricot...");
        return o;
    }
}

//java动态代理
class ApricotHandler implements InvocationHandler {
    private Object object;
    public ApricotHandler(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args); //调用真正的蛋糕机做蛋糕
        System.out.println("adding apricot...");
        return result;
    }
}


//杏仁蛋糕代理
class ApricotCakeProxy implements CakeMachine{
    private CakeMachine cakeMachine;
    public ApricotCakeProxy(CakeMachine cakeMachine){
        this.cakeMachine = cakeMachine;
    }
    @Override
    public void makeCake() {
        cakeMachine.makeCake();
        System.out.println("adding apricot...");
    }
}
//杏仁面包代理
class ApricotBreadProxy implements BreadMachine{
    private BreadMachine breadMachine;
    public ApricotBreadProxy(BreadMachine breadMachine){
        this.breadMachine = breadMachine;
    }
    @Override
    public void makeBread() {
        breadMachine.makeBread();
        System.out.println("adding apricot...");
    }
}

//做蛋糕的机器
interface CakeMachine {
    void makeCake();
}
//专门做水果蛋糕的机器
class FruitCakeMachine implements CakeMachine{
    @Override
    public void makeCake() {
        System.out.println("Making a fruit cake...");
    }
}
//专门做巧克力蛋糕的机器
class ChocolateCakeMachine implements CakeMachine{
    @Override
    public void makeCake() {
        System.out.println("making a Chocolate Cake...");
    }
}
//做面包的机器
interface BreadMachine{
    void makeBread();
}
//专门做红豆面包的机器
class RedBeanBreadMachine implements BreadMachine{
    @Override
    public void makeBread() {
        System.out.println("making red bean bread....");
    }
}
//专门做葡萄干面包的机器
class CurrantBreadMachine implements BreadMachine{
    @Override
    public void makeBread() {
        System.out.println("making currant bread...");
    }
}



