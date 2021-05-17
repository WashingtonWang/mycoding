package com.mycoding.javabase.genericeg.eg1;

import java.util.*;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/9/30 14:39
 * @Link http://www.mybatis.cn/archives/97.html
 */
public class GenericEg1 {
    public static void main(String[] args) {

        test2();
    }

    /**
     * 我们看输出发现，class1和class2居然是同一个类型ArrayList，在运行时我们传入的类型变量String和Integer都被丢掉了。
     * Java语言泛型在设计的时候为了兼容原来的旧代码，Java的泛型机制使用了“擦除”机制
     */
    public static void test1() {
        Class<?> class1=new ArrayList<String>().getClass();
        Class<?> class2=new ArrayList<Integer>().getClass();
        System.out.println(class1);        //class java.util.ArrayList
        System.out.println(class2);        //class java.util.ArrayList
        System.out.println(class1.equals(class2));    //true
    }

    /**
     * 下面的代码里，我们想在运行时获取类的类型参数，但是我们看到返回的都是“形参”。在运行期我们是获取不到任何已经声明的类型信息的。
     * 注意：
     * 编译器虽然会在编译过程中移除参数的类型信息，但是会保证类或方法内部参数类型的一致性。
     *
     * 泛型参数将会被擦除到它的第一个边界（边界可以有多个，重用 extends 关键字，通过它能给与参数类型添加一个边界）。
     * 编译器事实上会把类型参数替换为它的第一个边界的类型。如果没有指明边界，那么类型参数将被擦除到Object。
     *
     * Java泛型擦除的缺陷及补救措施
     *
     * 泛型类型不能显式地运用在运行时类型的操作当中，例如：转型、instanceof 和 new。因为在运行时，所有参数的类型信息都丢失了。
     * 类似下面的代码都是无法通过编译的：
     *     public class Erased<T> {
     *         private final int SIZE = 100;
     *         public static void f(Object arg) {
     *             //编译不通过
     *             if (arg instanceof T) {
     *             }
     *             //编译不通过
     *             T var = new T();
     *             //编译不通过
     *             T[] array = new T[SIZE];
     *             //编译不通过
     *             T[] array = (T) new Object[SIZE];
     *         }
     *     }
     *
     *  我们可以通过下面的代码来解决泛型的类型信息由于擦除无法进行类型判断的问题：
     *  class GenericType<T>{
     *     Class<?> classType;
     *
     *     public GenericType(Class<?> type) {
     *         classType=type;
     *     }
     *
     *     public boolean isInstance(Object object) {
     *         return classType.isInstance(object);
     *     }
     *  }
     *
     *  泛型代码中不能new T()的原因有两个，一是因为擦除，不能确定类型；而是无法确定T是否包含无参构造函数。
     * 为了避免这两个问题，我们使用显式的工厂模式：
     * 使用工厂方法来创建实例
     * interface Factory<T>{
     *     T create();
     * }
     *
     * class Creater<T>{
     *     T instance;
     *     public <F extends Factory<T>> T newInstance(F f) {
     *         instance=f.create();
     *         return instance;
     *     }
     * }
     *
     * class IntegerFactory implements Factory<Integer>{
     *     @Override
     *     public Integer create() {
     *         Integer integer=new Integer(9);
     *         return integer;
     *     }
     * }
     * 我们通过工厂模式+泛型方法来创建实例对象，上面代码中我们创建了一个IntegerFactory工厂，
     * 用来创建Integer实例，以后代码有变动的话，我们可以添加新的工厂类型即可。
     *
     */
    public static void test2(){
        List<Table> tableList = new ArrayList<Table>();
        Map<Room, Table> maps = new HashMap<Room, Table>();
        House<Room> house = new House<Room>();
        Particle<Long, Double> particle = new Particle<Long, Double>();
        System.out.println(Arrays.toString(tableList.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(maps.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(house.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(particle.getClass().getTypeParameters()));
    }
}

class Table {}
class Room {}
class House<Q> {}
class Particle<POSITION, MOMENTUM> {}
