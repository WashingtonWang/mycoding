package threadeg.eg8;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  创建一个固定大小的线程池，并采用有界队列以及调用者运行饱和策略   《java并发编程实战》 P.145
 * @Auther: wangxiangyu
 * @Date: 2017/11/30 22:25
 */
public class FixedExecutor {
    private void test(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(), 0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }


}
