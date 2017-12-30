package threadeg.eg10;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * @Description: 使用AbstractQueuedSynchronizer 实现的二元闭锁  《JAVA并发编程实战》  P.256
 * user: xiangyu.wang
 * date: 2017/12/30 15:12
 */
public class OneShotLatch {

    private final Sync sync = new Sync();
    public void signal(){
        sync.releaseShared(0);
    }
    public void await() throws InterruptedException{
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedLongSynchronizer{
        protected int tryAcquireShared(int ignored){
            //如果闭锁是开的(state = 1)，那么这个操作将成功，否则将失败
            return (getState() == 1) ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignored){
            setState(1); //现在打开闭锁
            return true;
        }
    }
}
