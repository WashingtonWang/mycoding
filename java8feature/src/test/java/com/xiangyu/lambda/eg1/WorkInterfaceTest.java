package com.xiangyu.lambda.eg1;

import java.util.function.Consumer;

public class WorkInterfaceTest {

    public static void main(String[] args){
        execute(new WorkInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Worker invoked using Anonymous class ");
            }
        });

        execute( () -> System.out.println("Worker invoked using Lambda expression"));
    }

    public static void execute(WorkInterface worker){
        worker.doSomeWork();
    }


}
