package threadeg.eg5.one;

import java.util.concurrent.*;

/**
 * @Description: 虽然这是最终实现
 * 注意：当缓存的是Future而不是值时，将导致缓存污染问题：如果某个计算补取消或者失败，那么在计算这个结果时将指明计算过程被取消或者失败。
 * 为了避免这种情况，如果Memoizer发现计算被取消，那么将把Future从缓存中移除。如果检测到RuntimeException，那么也会移除Future，
 * 这样将来的计算才可能成功
 * @Auther: wangxiangyu
 * @Date: 2017/11/7 22:00
 */
public class Memoizer<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if (f == null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                //用lambda形式
                Callable<V> eval1 = () -> {
                    return c.compute(arg);
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg,ft);
                if (f == null){
                    f = ft;
                    ft.run();
                }
            }
            try{
                return f.get();
            }catch (CancellationException e){
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
