package com.mycoding.javabase.threadeg.eg2;

public class TestSynOne {
    private static int sum = 0;
    private final String lock = "lock";
    public void printSome(String name){
        //System.out.println(this.toString());
        //synchronized (this){
            sum++;
            System.out.println(name+" | "+sum);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
    }
}
