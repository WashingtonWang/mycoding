package threadeg.eg7;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 通过“”
 * user: xiangyu.wang
 * date: 2017/11/18 14:10
 */
public class IndexingService {

    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }


    public void start(){
        producer.start();
        consumer.start();
    }

    public void stop(){
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException{
        consumer.join();
    }

    class CrawlerThread extends Thread{
        public void run(){
            try {
                crawl(root);
            }catch (InterruptedException e){
                //
            }finally {
                while (true){
                    try {
                        queue.put(POISON);
                    }catch (InterruptedException e1){
                        //重新尝试
                    }
                }
            }

        }

        private void crawl(File root) throws InterruptedException{

        }
    }

    class IndexerThread extends Thread{
        public void run(){
            try {
                while (true){
                    File file = queue.take();
                    if (file == POISON){
                        break;
                    }else{
                        //一个方法
                        //indexFile(file);
                    }
                }
            }catch (InterruptedException consumed){
                //
            }
        }
    }



}
