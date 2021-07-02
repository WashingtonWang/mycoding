package com.mycoding.javabase.threadeg.eg2;

import com.mycoding.javabase.threadeg.eg1.Widget;

public class TestClassSyn {

    //private static  AtomicInteger number = new AtomicInteger(0);
    //private static int sum = 0;

    private static int number = 0;


    private synchronized void abc(Widget widget){
        //synchronized (this.getClass()){
        //sum = number.addAndGet(1);
        widget.doSomething();
            number++;
            System.out.println(number);
        //}
    }

    public static void main(String[] args) throws Exception{
        //Thread[] threads = new Thread[10];
        //for (int i = 0; i < 10; i++){
        //    threads[i] = new Thread(new Runnable() {
        //        @Override
        //        public void run() {
        //            for (int j = 0; j < 1000; j++)
        //                abc();
        //        }
        //    });
        //    threads[i].start();
        //}
        TestSynTwo tsa = new TestSynTwo();
        for (int i = 0; i < 100; i++){
            number = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //TestClassSyn tcs = new TestClassSyn();
                    ////synchronized (tcs.getClass()){
                    //Widget widget = new Widget();
                    //System.out.println(widget);
                    //    tcs.abc(widget);
                    ////}
                    //
                    //try {
                    //    System.out.println(Thread.currentThread().getName());
                    //    Thread.currentThread().sleep(2000);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}
                    //
                    //System.out.println(number);

                    tsa.two(number);
                    TestSynThree tsb = new TestSynThree();
                    tsb.three(number);
                    //TestSynTwo.two(Thread.currentThread(), number);
                    System.out.println(Thread.currentThread().getId());

                }
            }).start();
        }
    }

    //class Test{
    //    public synchronized void aa(){
    //        System.out.println(number++);
    //    }
    //}

}





