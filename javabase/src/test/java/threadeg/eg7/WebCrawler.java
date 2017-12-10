package threadeg.eg7;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 使用TrackingExecutorService来保存未完成的任务以备后续执行 《java并发编程实战》 P.131
 * user: xiangyu.wang
 * date: 2017/11/18 18:21
 */
public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    private final Set<URL> urlsToCrawl = new HashSet<>();
    private final static long TIMEOUT = 1000;
    private final static TimeUnit UNIT = TimeUnit.SECONDS;

    public synchronized void start(){
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl){
            submitCrawlTask(url);
        }
        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException{
        try {
            saveUncrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT, UNIT)){
                saveUncrawled(exec.getCancelledTask());
            }
        }finally {
            exec = null;
        }
    }

    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled){
        for (Runnable task : uncrawled){
            urlsToCrawl.add(((CrawlTask)task).getPage());
        }
    }

    private void submitCrawlTask(URL u){
        exec.execute(new CrawlTask(u));
    }

    private class CrawlTask implements Runnable{

        private final URL url;

        private CrawlTask(URL url) {
            this.url = url;
        }

        @Override
        public void run() {
            for (URL link : processPage(url)){
                if (Thread.currentThread().isInterrupted()){
                    return;
                }
                submitCrawlTask(link);
            }
        }
        public URL getPage(){
            return url;
        }
    }
}
