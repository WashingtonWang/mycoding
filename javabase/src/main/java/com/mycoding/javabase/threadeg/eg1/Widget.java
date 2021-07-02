package com.mycoding.javabase.threadeg.eg1;

public class Widget {

    public  void doSomething(){
        synchronized(this){
            System.out.println(this.getClass());
            System.out.println("i am is widget");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
