package threadeg.eg9;

/**
 * @Description:  在基于散列的Map中使用锁分段技术  《java并发编程实战》  P.197
 * @Auther: wangxiangyu
 * @Date: 2017/12/10 10:35
 */
public class StripedMap {
    //使用同步策略：buckets[n] 由locks[n%N_LOCKS]来保护
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++){
            locks[i] = new Object();
        }
    }

    private final int hash(Object key){
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key){
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]){
            for (Node m = buckets[hash]; m != null; m = m.next()){
                if (m.key.equals(key)){
                    return m.value();
                }
            }
        }
        return null;
    }

    public void clear(){
        for (int i = 0; i < buckets.length; i++){
            synchronized (locks[i % N_LOCKS]){
                buckets[i] = null;
            }
        }
    }

    private static class Node{
        private String key;
        Node next(){
            return new Node();
        }
        Object value(){
            return new Object();
        }
    }
}
