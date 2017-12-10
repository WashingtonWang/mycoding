package threadeg.eg7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 使用volatile 类型的域来保存取消状态 《java并发编程实战》 P.112
 * @Auther: wangxiangyu
 * @Date: 2017/11/15 22:07
 */
public class PrimeGenerator implements Runnable{

    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

    /*****************************************************************************/

    /**
     * 使用上面的方法 一个仅运行一秒钟的素数生成器 《java并发编程实战》 P.113
     * @return
     * @throws InterruptedException
     */
    List<BigInteger> aSecondOfPrimes() throws InterruptedException{
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try{
            //睡眠
            //SECONDS.sleep(1);
        }finally {
            generator.cancel();
        }
        return generator.get();
    }

}

/**
 * 通过中断来取消  《java并发编程实战》 P.115
 */
class PrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run(){
        try{
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()){
                queue.put(p = p.nextProbablePrime());
            }
        }catch (InterruptedException consumed){
            /*  允许线程退出 */
        }
    }
    public void cancel(){
        interrupt();
    }
}
