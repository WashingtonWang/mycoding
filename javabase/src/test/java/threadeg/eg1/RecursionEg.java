package threadeg.eg1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 由 运行结果 后可得出 递归 方法是用不到 同步的  因为递归是在线程自身的栈里去进行操作的。。
 * java 没有支持 尾递归
 */
public class RecursionEg {

    private int add(int j){

        //synchronized(this){
            if (j == 100){
                return j;
            }else{
                System.out.println(Thread.currentThread().getName() + " : "+ j);
                return add(++j);
            }
        //}

        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }

    public static void main(String[] args){
        RecursionEg re = new RecursionEg();
        /**
         * 测试类是否线程安全 用线程池 此种方法，更容易测试 线程安全问题！切记
         */
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    re.add(0);
                }
            });
        }
        es.shutdown();
    }

}
