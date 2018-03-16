package threadeg.util.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 写个 线程池工具类
 * user: xiangyu.wang
 * date: 2018/2/7 18:02
 */
public class ThreadPoolUtil {
    public static void test(){
        ExecutorService es = ExecutorServiceInstance.INSTANCE.getInstance();
        System.out.println(es);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread(ThreadPoolUtil::test).start();
        }
    }

    private enum ExecutorServiceInstance{
        INSTANCE;

        private ExecutorService es;

        ExecutorServiceInstance(){
            es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        }
        public ExecutorService getInstance(){
            return es;
        }
    }
}

