package map.imitate;

/**
 * user: xiangyu.wang
 * date: 2018/3/20 18:07
 */
public class LinkedHashMapImitate<K, V> extends HashMapImitate<K, V> implements MapImitate<K, V> {

    private static final long serialVersionUID = 9065290304327413794L;

    static class EntryIm<K, V> extends HashMapImitate.NodeIm<K, V> {
        EntryIm<K, V> before, after;

        EntryIm(int hash, K key, V value, NodeIm<K, V> next) {
            super(hash, key, value, next);
        }
    }


}
