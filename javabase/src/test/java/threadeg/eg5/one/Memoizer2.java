package threadeg.eg5.one;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:  使用HashMap和同步机制来初始化缓存 《java并发编程实战》 P.87
 * 注意：将HashMap换成ConcurrentMap 把synchronized去掉
 * 这样的性能会好些，但是这样存在一个漏洞，当两个线程同时调用ompute时可能会导致计算得到相同的值 (no)
 *
 * @Auther: wangxiangyu
 * @Date: 2017/11/6 21:54
 */
public class Memoizer2<A,V> implements Computable<A,V> {

    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null){
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
