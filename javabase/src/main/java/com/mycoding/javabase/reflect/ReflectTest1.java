package com.mycoding.javabase.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/9/26 14:32
 */
public class ReflectTest1 {

    public static void main(String[] args) throws Exception{
        //test();
        invokeTest();
    }

    public static void test() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> c = methodClass.class;
        Object object = c.newInstance();
        Method[] methods = c.getMethods();
        Method[] declaredMethods = c.getDeclaredMethods();
        //获取methodClass类的add方法
        Method method = c.getMethod("add", int.class, int.class);
        //getMethods()方法获取的所有方法
        System.out.println("getMethods获取的方法：");
        for(Method m: methods)
            System.out.println(m);
        //getDeclaredMethods()方法获取的所有方法
        System.out.println("getDeclaredMethods获取的方法：");
        for(Method m:declaredMethods)
            System.out.println(m);
    }

    public static void invokeTest() throws Exception{
        Class<?> klass = methodClass.class;
        //创建methodClass的实例
        Object obj = klass.newInstance();
        //获取methodClass类的add方法
        Method method = klass.getMethod("add",int.class,int.class);
        //调用method对应的方法 => add(1,4)
        Object result = method.invoke(obj,1,4);
        System.out.println(result);
    }

    /**
     * 其中的Array类为java.lang.com.mycoding.javabase.reflect.Array类。我们通过Array.newInstance()创建数组对象，它的原型是:
     *
     *  public static Object newInstance(Class<?> componentType, int length)
     *         throws NegativeArraySizeException {
     *         return newArray(componentType, length);
     *     }
     *
     * 而 newArray 方法是一个 native 方法
     * @throws ClassNotFoundException
     */
    public static void arrayTest() throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls,25);
        //往数组里添加内容
        Array.set(array,0,"hello");
        Array.set(array,1,"Java");
        Array.set(array,2,"fuck");
        Array.set(array,3,"Scala");
        Array.set(array,4,"Clojure");
        //获取某一项的内容
        System.out.println(Array.get(array,3));
    }
}

class methodClass {
    public final int fuck = 3;
    public int add(int a,int b) {
        return a+b;
    }
    public int sub(int a,int b) {
        return a+b;
    }
}