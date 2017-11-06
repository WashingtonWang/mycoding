package threadeg.eg5.one;

/**
 * @Description: 使用HashMap和同步机制来初始化缓存 《java并发编程实战》 P.86
 * @Auther: wangxiangyu
 * @Date: 2017/11/6 21:51
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
