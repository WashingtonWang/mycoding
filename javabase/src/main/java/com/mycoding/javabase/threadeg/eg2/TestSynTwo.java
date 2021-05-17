package com.mycoding.javabase.threadeg.eg2;

public class TestSynTwo {
    TestSynOne tso = new TestSynOne();
    public void two(int i){
        System.out.println(this.toString());
        synchronized (this){
            tso.printSome("two: ");
        }
        //try {
        //    thread.sleep(5000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }
}
