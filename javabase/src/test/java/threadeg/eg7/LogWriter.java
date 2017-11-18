package threadeg.eg7;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 不支持关闭的生产者 消费者日志服务 《java并发编程实战》 P.125
 * 注意：此类并没有相应的关闭方法或者取消操作 所以此类并不完整  请看LogService类
 * user: xiangyu.wang
 * date: 2017/11/18 11:14
 */
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    public LogWriter(BlockingQueue<String> queue, LoggerThread logger) {
        this.queue = queue;
        this.logger = logger;
    }

    public void start(){
        logger.start();
    }

    public void log(String msg) throws InterruptedException{
        queue.put(msg);
    }

    private class LoggerThread extends Thread{
        private final PrintWriter writer;

        private LoggerThread(PrintWriter writer) {
            this.writer = writer;
        }

        public void run(){
            try {
                while (true){
                    writer.println(queue.take());
                }
            }catch (InterruptedException ignored){

            }finally {
                writer.close();
            }
        }
    }
}
