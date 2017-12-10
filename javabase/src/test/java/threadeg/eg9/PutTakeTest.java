package threadeg.eg9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 测试BoundedBuffer的生产者 —— 消费者程序 《java并发编程实战》  P.210
 * @Auther: wangxiangyu
 * @Date: 2017/12/10 21:52
 */
public class PutTakeTest {
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    //private final CyclicBarrier barrier;
}
