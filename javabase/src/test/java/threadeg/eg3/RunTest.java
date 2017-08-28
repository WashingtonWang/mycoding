package threadeg.eg3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunTest{
    private int value = 0;

    public int getNext(){
        //AtomicInteger ai = new AtomicInteger(0);
        //ai.incrementAndGet()
        synchronized (this){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value++;
        }
    }

    public static void main(String[] args){
        RunTest rt = new RunTest();
        /**
         * 测试类是否线程安全 用线程池 此种方法，更容易测试 线程安全问题！切记
         */
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()  + " || "+ rt.getNext() );
                    System.out.print(rt.getNext() == 99? 99 : "");
                }
            });
        }
        es.shutdown();
    }
}
