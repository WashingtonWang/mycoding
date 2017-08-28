package threadeg.eg1;

import java.util.concurrent.TimeUnit;

public class StopThread {

    public static void main(String[] args) throws InterruptedException {
        //notStopThread();
        //yesStopThread();
        sopThread();
    }

    /**
     * 最简单的实现同步
     */
    private static volatile boolean stopReuested;
    private static void sopThread() throws InterruptedException{
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopReuested)
                    i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopReuested = true;
    }


    /**
     * 下面的程序是正确的！用了synchronized 方法，并且是，读，写方法都同步了，如果只同步写或者读，都是错误的
     *
     * 其实最简单的方法是 把变量前 加上 volative (保证变量是可见的)
     */
    private static boolean yesStopRequested;
    private static synchronized void requestStop(){
        yesStopRequested = true;
    }
    private static synchronized boolean stopRequested(){
        return yesStopRequested;
    }
    private static void yesStopThread() throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested())
                    i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }

    /**
     * 下面的程序 不会在1秒钟后停止，因为虚拟机将这个代码：
     * while(!done)
     *  i++;
     *  转化成这样：
     *  if(!done)
     *    while(true)
     *      i++;
     * @throws InterruptedException
     */

    private static boolean notStopReuested;
    private static void notStopThread() throws InterruptedException{
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!notStopReuested)
                    i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        notStopReuested = true;
    }
}
