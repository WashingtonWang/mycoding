package com.mycoding.javabase.callback.eg1;

public class CallMeTest {

    public static void main(String[] args){
        EventNotifier ren = new EventNotifier();
        CallMe a = new CallMe("Call me A");
        CallMe b = new CallMe("Call me B");

        ren.regist(a);
        ren.regist(b);

        ren.doWork();
    }
}
