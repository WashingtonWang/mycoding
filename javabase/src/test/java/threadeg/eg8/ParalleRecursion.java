package threadeg.eg8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 将串行递归转换为并行递归   《JAVA并发编程实战》  P.150
 * user: xiangyu.wang
 * date: 2017/12/2 11:38
 */
public class ParalleRecursion {
    /**
     * @Description: 串行递归
     * @param nodes
     * @param results
     * @param <T>
     */
    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results){
        for (Node<T> n : nodes){
            results.add((T) n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    /**
     * @deprecated 并行递归
     * @param exec
     * @param nodes
     * @param results
     * @param <T>
     */
    public <T> void parallelRecursive(final Executor exec, List<Node<T>> nodes, final Collection<T> results){
        for (final Node n : nodes){
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    results.add((T) n.compute());
                }
            });
            parallelRecursive(exec, n.getChildren(), results);
        }
    }

    /**
     * @deprecated 等待通过并行方式计算的结果  《java并发编程实战》  P.151
     * @param nodes
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException{
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedDeque<>();
        parallelRecursive(exec, nodes, resultQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }

    private class Node<T> {
        public String compute(){
            return "";
        }

        public List<Node<T>> getChildren(){
            return new ArrayList<Node<T>>();
        }
    }
}
