package com.xiangyu.lambdaeg.eg2;

/**
 * 看一下 lamdba 生成的东西
 * user: xiangyu.wang
 * date: 2017/10/31 17:20
 */
public class LambdaPrinciple {
    @FunctionalInterface
    interface Action{
        void run(String s);
    }

    public void action(Action action){
        action.run("hello");
    }

    public static void main(String[] args){
        new LambdaPrinciple().action((String s) -> System.out.println("**"+s+"**"));
    }
}
