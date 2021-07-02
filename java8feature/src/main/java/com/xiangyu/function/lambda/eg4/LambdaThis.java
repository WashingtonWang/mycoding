package com.xiangyu.function.lambda.eg4;

/**
 * @Description: 在lamdba表达式中用的this 是外面创建lambda的对象引用  《写给大忙人看的java se8》  P.13
 * @Auther: wangxiangyu
 * @Date: 2018/1/4 21:49
 */
public class LambdaThis {

    public static void main(String[] args) {
        LambdaThis a = new LambdaThis();
        a.doWork();
    }

    private void doWork(){
        Runnable runnable = () ->{
            System.out.println(this.toString());
        };
        runnable.run();

    }

    @Override
    public String toString() {
        System.out.println("i to string ");
        return "123";
    }
}
