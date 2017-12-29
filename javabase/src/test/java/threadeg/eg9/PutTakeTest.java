package threadeg.eg9;

import java.util.concurrent.CyclicBarrier;
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
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nPairs;

    PutTakeTest(int capacity, int npairs, int ntrials){
        this.bb = new BoundedBuffer<>(capacity);
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }


    public static void main(String[] args) {
        new PutTakeTest(10, 10, 100000).test(); //示例参数
        pool.shutdown();
    }

    void test(){
        try{
            for (int i  = 0; i < nPairs; i++){
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await(); //等待所有的线程就绪
            barrier.await(); //等待所有的线程执行完成
            System.out.println("putSum.get() : " + putSum.get() + "takeSum.get()" + takeSum.get());
            System.out.println(putSum.get() == takeSum.get());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            try{
                int seed = (this.hashCode() ^ (int)System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i){
                    bb.put(seed);
                    sum += seed;
                    //seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    class Consumer implements Runnable{

        @Override
        public void run() {
            try{
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i){
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
