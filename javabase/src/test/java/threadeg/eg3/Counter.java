package threadeg.eg3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Counter {
    private long value = 0;

    public  long getValue(){
        return value;
    }

    public  long increment(){
        if (value == Long.MAX_VALUE)
            throw new IllegalArgumentException("counter overflow");
        return ++value;
    }

    public static void main(String[] args){
        Counter c = new Counter();
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    c.increment();
                    System.out.println(c.getValue() == 99 ? 99 : "");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        es.shutdown();
    }
}
