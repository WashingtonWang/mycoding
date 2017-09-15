package com.xiangyu.function.eg2;

import com.xiangyu.function.eg1.Func;

import javax.naming.BinaryRefAddr;
import javax.sound.midi.Soundbank;
import java.util.function.*;

public class FunctionTest {

    public static void main(String[] args){
        Function<Integer, Integer> incr1 = x -> x + 1;
        Function<Integer, Integer> multiply = x -> x * 2;
        int x = 5;
        System.out.println("f(x)=x+1,when x="+x+",f(x)="+incr1.apply(x));
        System.out.println("f(x)=x+1,g(x)=2x,when x="+x+",f(g(x))="+incr1.compose(multiply).apply(x));
        System.out.println("f(x)=x+1,g(x)=2x,when x="+x+",g(f(x))="+incr1.andThen(multiply).apply(x));
        System.out.println("compose vs andThen:f(g(x))="+incr1.compose(multiply).apply(x)+","+multiply.andThen(incr1).apply(x));

        //Function<Integer,Function<Integer,Integer>> makeAdder = z -> y -> z + y;
        //int x = 2;
        //Function<Integer, Integer> add1 = makeAdder.apply(1);
        //System.out.println("f(x)=x+1, when x= "+x+",f(x)="+ add1.apply(x));
        //Function<Integer, Integer> add5 = makeAdder.apply(5);
        //System.out.println("f(x)=x+5, when x= "+x+",f(x)="+ add5.apply(x));
        //
        ////二元函数
        //BiFunction<Integer, Integer, Integer> mult = (a,b) -> a * b;
        //System.out.println("f(z)=x*y, when x=3,y=5, then f(z)="+ mult.apply(3,5));

        //operator
        //UnaryOperator<Integer> add = x -> x + 1;
        //System.out.println(add.apply(1));
        //
        //BinaryOperator<Integer> addxy = (x,y) -> x + y;
        //System.out.println(addxy.apply(3,5));
        //
        //BinaryOperator<Integer> min = BinaryOperator.minBy((o1,o2) -> o1 - o2);
        //System.out.println(min.apply(100,200));
        //BinaryOperator<Integer> max = BinaryOperator.maxBy((o1,o2) -> o1 - o2);
        //System.out.println(max.apply(100,200));

        //predicate
        //FunctionTest test = new FunctionTest();
        //test.printBigValue(10,val -> val > 5);
        //test.printBigValueAnd(10, val -> val > 5);
        //test.printBigValueAnd(6, val -> val > 5);

        //consumer
        //Consumer<Integer> consumer = System.out::println;
        //consumer.accept(100);
        //
        ////use function, you always need one return value
        //Function<Integer, Integer> function = x -> {
        //    System.out.println(x);
        //    return x;
        //};
        //function.apply(200);

        //supplier
        //Supplier<FunctionTest> anotherSupplier;
        //for (int i = 0; i < 10; i++){
        //    anotherSupplier = FunctionTest::new;
        //    System.out.println(anotherSupplier.get());
        //}

    }

    public void printBigValue(int value, Predicate<Integer> predicate){
        if (predicate.test(value))
            System.out.println(value);
    }

    public void printBigValueAnd(int value, Predicate<Integer> predicate){
        if (predicate.and(v -> v < 8).test(value))
            System.out.println("value < 8 : "+ value);
        else
            System.out.println("value should < 8 at least. ");
    }
}
