package com.mycoding.javabase.threadeg.eg5.one;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description: Memoizer3 的实现要比Memoizer2和1的实现好很多
 * 注意：它还是有个缺陷，即仍然存在两个线程计算出相同值的漏洞，这个漏洞的发生概率要远比Memoizer2小，
 * 但是ompute方法中的if代码块仍然是非原子的的"先检查后执行"的操作,因此两个线程仍有可能在同一时间内调用
 * compute方法来计算相同的值 (no)
 * @Auther: wangxiangyu
 * @Date: 2017/11/7 21:46
 */
public class Memoizer3<A, V> implements Computable<A, V>{
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            //用lamdba形式
            Callable<V> eval1 = () -> {
                return c.compute(arg);
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();   //在这里将调用c.compute
        }
        try{
            return f.get();
        }catch (ExecutionException e){
            throw new RuntimeException(e);
        }
    }
}
