package imitate.javautil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Set;

/**
 * user: xiangyu.wang
 * date: 2018/3/19 10:59
 */
public class HashMapImitate<K, V> extends AbstractMapImitate<K, V>
        implements MapImitate<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = -7234327472910014158L;

    static final int DEFAULT_INITIAL_CAPACITY_IM = 1 << 4; //16
    static final int MAXIMUM_CAPACITY_IM = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR_IM = 0.75f;
    static final int TREEIFY_THRESHOLD_IM = 8;
    static final int UNTREEIFY_THRESHOLD_IM = 6;
    static final int MIN_TREEIFY_CAPACITY_IM = 64;

    static class NodeIm<K, V> implements MapImitate.EntryIm<K, V> {

        final int hash;
        final K key;
        V value;
        NodeIm<K, V> next;

        NodeIm(int hash, K key, V value, NodeIm<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKeyIm() {
            return key;
        }

        @Override
        public V getValueIm() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValueIm(V newValue) {
            V oldValue = value;
            this.value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof MapImitate.EntryIm) {
                MapImitate.EntryIm<?, ?> e = (EntryIm<?, ?>) o;
                if (Objects.equals(key, e.getKeyIm()) && Objects.equals(value, e.getValueIm())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* ---------------- Static utilities -------------- */
    static final int hashIm(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static Class<?> comparableClassForIm(Object x) {
        if (x instanceof Comparable) {
            Class<?> c;
            Type[] ts, as;
            Type t;
            ParameterizedType p;
            if ((c = x.getClass()) == String.class) {
                return c;
            }
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; i++) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType) t).getRawType() == Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    static int compareComparablesIm(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 : ((Comparable) k).compareTo(x));
    }

    static final int tableSizeForIm(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY_IM) ? MAXIMUM_CAPACITY_IM : n + 1;
    }

    /* ---------------- Fields -------------- */
    transient NodeIm<K, V>[] table;
    transient Set<MapImitate.EntryIm<K, V>> entryImSet;
    transient int size;
    transient int modCount;
    int threshold;
    final float loadFactor;

    /* ---------------- Public operations -------------- */

    public HashMapImitate(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY_IM) {
            initialCapacity = MAXIMUM_CAPACITY_IM;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeForIm(initialCapacity);
    }

    public HashMapImitate(int initicalCapacity) {
        this(initicalCapacity, DEFAULT_INITIAL_CAPACITY_IM);
    }

    public HashMapImitate() {
        this.loadFactor = DEFAULT_LOAD_FACTOR_IM;
    }

    public HashMapImitate(MapImitate<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR_IM;
    }

    final void putMapEntriesIm(MapImitate<? extends K, ? extends V> m, boolean evict) {
        int s = m.sizeIm();
        if (s > 0) {
            if (table == null) {
                float ft = ((float) s / loadFactor) + 1.0F;
                int t = (ft < (float) MAXIMUM_CAPACITY_IM) ? (int) ft : MAXIMUM_CAPACITY_IM;
                if (t > threshold) {
                    threshold = tableSizeForIm(t);
                }
            } else if (s > threshold) {
                resizeIm();
            }
            for (MapImitate.EntryIm<? extends K, ? extends V> e : m.entrySetIm()) {
                K key = e.getKeyIm();
                V value = e.getValueIm();
                putValIm(hashIm(key), key, value, )
            }
        }
    }

    @Override
    public int sizeIm() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKeyIm(Object key) {
        return false;
    }

    @Override
    public Set<EntryIm<K, V>> entrySetIm() {
        return null;
    }

    final NodeIm<K, V>[] resizeIm() {
        NodeIm<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY_IM) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY_IM && oldCap >= DEFAULT_INITIAL_CAPACITY_IM) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = DEFAULT_INITIAL_CAPACITY_IM;
            newThr = (int) (DEFAULT_LOAD_FACTOR_IM * DEFAULT_INITIAL_CAPACITY_IM);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY_IM && ft < (float) MAXIMUM_CAPACITY_IM ?
                    (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes", "unchecked"})
        NodeIm<K, V>[] newTab = (NodeIm<K, V>[]) new NodeIm[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; j++) {
                NodeIm<K, V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNo)
                }
            }
        }
    }

    NodeIm<K, V> replacementNodeIm(NodeIm<K, V> p, NodeIm<K, V> next) {
        return new NodeIm<>(p.hash, p.key, p.value, next);
    }

    TreeNodeIm<K, V> newTreeNodeIm(int hash, K key, V value, NodeIm<K, V> next) {
        return new TreeNodeIm<>(hash, key, value, next);
    }

    /* ------------------------------------------------------------ */
    // Tree bins
    static final class TreeNodeIm<K, V> extends LinkedHashMapImitate.EntryIm<K, V> {
        TreeNodeIm<K, V> parent;
        TreeNodeIm<K, V> left;
        TreeNodeIm<K, V> right;
        TreeNodeIm<K, V> prev;
        boolean red;

        TreeNodeIm(int hash, K key, V val, NodeIm<K, V> next) {
            super(hash, key, val, next);
        }

        final TreeNodeIm<K, V> rootIm() {
            for (TreeNodeIm<K, V> r = this, p; ; ) {
                if ((p = r.parent) == null)
                    return r;
                r = p;
            }
        }

        static <K, V> void moveRootToFrontIm(NodeIm<K, V>[] tab, TreeNodeIm<K, V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                int index = (n - 1) & root.hash;
                TreeNodeIm<K, V> first = (TreeNodeIm<K, V>) tab[index];
                if (root != first) {
                    NodeIm<K, V> rn;
                    tab[index] = root;
                    TreeNodeIm<K, V> rp = root.prev;
                    if ((rn = root.next) != null)
                        ((TreeNodeIm<K, V>) rn).prev = rp;
                    if (rp != null)
                        rp.next = rn;
                    if (first != null)
                        first.prev = root;
                    root.next = first;
                    root.next = null;
                }
                assert checkInvariantsIm(root);
            }
        }

        final TreeNodeIm<K, V> findIm(int h, Object k, Class<?> kc) {
            TreeNodeIm<K, V> p = this;
            do {
                int ph, dir = 0;
                K pk;
                TreeNodeIm<K, V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h)
                    p = pl;
                else if (ph < h)
                    p = pr;
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    return p;
                else if (pl == null)
                    p = pr;
                else if (pr == null)
                    p = pl;
                else if (kc != null || ((kc = comparableClassForIm(k)) != null) &&
                        (dir = compareComparablesIm(kc, k, pk)) != 0)
                    p = (dir < 0) ? pl : pr;
                else if ((q = pr.findIm(h, k, kc)) != null)
                    return q;
                else
                    p = pl;
            } while (p != null);
            return null;
        }

        final TreeNodeIm<K, V> getTreeNodeIm(int h, Object k) {
            return ((parent != null) ? rootIm() : this).findIm(h, k, null);
        }

        static int tieBreakOrderIm(Object a, Object b) {
            int d;
            if (a == null || b == null || (d = a.getClass().getName().
                    compareTo(b.getClass().getName())) == 0)
                d = (System.identityHashCode(a) <= System.identityHashCode(b) ? -1 : 1);
            return d;
        }

        final void treeifyIm(NodeIm<K, V>[] tab) {
            TreeNodeIm<K, V> root = null;
            for (TreeNodeIm<K, V> x = this, next; x != null; x = next) {
                next = (TreeNodeIm<K, V>) x.next;
                x.left = x.right = null;
                if (root == null) {
                    x.parent = null;
                    x.red = false;
                    root = x;
                } else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (TreeNodeIm<K, V> p = root; ; ) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h)
                            dir = -1;
                        else if (ph < h)
                            dir = 1;
                        else if ((kc == null && (kc = comparableClassForIm(k)) == null) ||
                                (dir = compareComparablesIm(kc, k, pk)) == 0)
                            dir = tieBreakOrderIm(k, pk);
                        TreeNodeIm<K, V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if (dir <= 0)
                                xp.left = x;
                            else
                                xp.right = x;
                            root = balanceInsertionIm(root, x);
                            break;
                        }
                    }
                }
            }
            moveRootToFrontIm(tab, root);
        }

        final NodeIm<K, V> untreeifyIm(HashMapImitate<K, V> map) {
            NodeIm<K, V> hd = null, tl = null;
            for (NodeIm<K, V> q = this; q != null; q = q.next) {
                NodeIm<K, V> p = map.replacementNodeIm(q, null);
                if (tl == null)
                    hd = p;
                else
                    tl.next = p;
                tl = p;
            }
            return hd;
        }

        final TreeNodeIm<K, V> putTreeValIm(HashMapImitate<K, V> map, NodeIm<K, V>[] tab,
                                            int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            TreeNodeIm<K, V> root = (parent != null) ? rootIm() : this;
            for (TreeNodeIm<K, V> p = root; ; ) {
                int dir, ph;
                K pk;
                if ((ph = p.hash) > h)
                    dir = -1;
                else if (ph < h)
                    dir = 1;
                else if ((pk = p.key) == k || (k != null) && k.equals(pk))
                    return p;
                else if ((kc == null) && (kc = comparableClassForIm(k)) == null ||
                        (dir = compareComparablesIm(kc, k, pk)) == 0) {
                    if (!searched) {
                        TreeNodeIm<K, V> q, ch;
                        searched = true;
                        if (((ch = p.left) != null && (q = ch.findIm(h, k, kc)) != null) ||
                                ((ch = p.right) != null && (q = ch.findIm(h, k, kc)) != null))
                            return q;
                    }
                    dir = tieBreakOrderIm(k, pk);
                }

                TreeNodeIm<K, V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    NodeIm<K, V> xpn = xp.next;
                    TreeNodeIm<K, V> x = map.newTreeNodeIm(h, k, v, xpn);
                    if (dir <= 0)
                        xp.left = x;
                    else
                        xp.right = x;
                    xp.next = x;
                    x.parent = x.prev = xp;
                    if (xpn != null)
                        ((TreeNodeIm<K, V>) xpn).prev = x;
                    moveRootToFrontIm(tab, balanceInsertionIm(root, x));
                    return null;
                }
            }
        }

        final void removeTreeNodeIm(HashMapImitate<K, V> map, NodeIm<K, V>[] tab, boolean movable) {
            int n;
            if (tab == null || (n = tab.length) == 0)
                return;
            int index = (n - 1) & hash;
            TreeNodeIm<K, V> first = (TreeNodeIm<K, V>) tab[index], root = first, rl;
            TreeNodeIm<K, V> succ = (TreeNodeIm<K, V>) next, pred = prev;
            if (pred == null)
                tab[index] = first = succ;
            else
                pred.next = succ;
            if (succ != null)
                succ.prev = pred;
            if (first == null)
                return;
            if (root.parent != null)
                root = root.rootIm();
            if (root == null || root.right == null || (rl = root.left) == null || rl.left == null){
                tab[index] = first.untreeifyIm(map);
                return;
            }
            TreeNodeIm<K, V> p = this, pl = left, pr = right, replacement;
            if (pl != null && pr != null){
                TreeNodeIm<K, V> s = pr, sl;
                while ((sl = s.left) != null)
                    s = sl;
                boolean c = s.red; s.red = p.red;p.red = c;
                TreeNodeIm<K, V> sr = s.right;
                TreeNodeIm<K, V> pp = p.parent;
                if (s == pr){

                }
            }
        }

        static <K, V> TreeNodeIm<K, V> balanceInsertionIm(TreeNodeIm<K, V> root, TreeNodeIm<K, V> x) {
            x.red = true;
            for (TreeNodeIm<K, V> xp, xpp, xppl, xppr; ; ) {
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                } else if (!xp.red || (xpp = xp.parent) == null)
                    return root;
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        xppr.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.right) {
                            root = rotateLeftIm(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRightIm(root, xpp);
                            }
                        }
                    }
                } else {
                    if (xppl != null && xppl.red) {
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.left) {
                            root = rotateRightIm(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeftIm(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        static <K, V> TreeNodeIm<K, V> rotateLeftIm(TreeNodeIm<K, V> root, TreeNodeIm<K, V> p) {
            TreeNodeIm<K, V> r, pp, rl;
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null)
                    rl.parent = p;
                if ((pp = r.parent = p.parent) == null)
                    (root = r).red = false;
                else if (pp.left == p)
                    pp.left = r;
                else
                    pp.right = r;
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        static <K, V> TreeNodeIm<K, V> rotateRightIm(TreeNodeIm<K, V> root, TreeNodeIm<K, V> p) {
            TreeNodeIm<K, V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null)
                    lr.parent = p;
                if ((pp = l.parent = p.parent) == null)
                    (root = l).red = false;
                else if (pp.right == p)
                    pp.right = l;
                else
                    pp.left = l;
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        static <K, V> boolean checkInvariantsIm(TreeNodeIm<K, V> t) {
            TreeNodeIm<K, V> tp = t.parent,
                    tl = t.left, tr = t.right,
                    tb = t.prev, tn = (TreeNodeIm<K, V>) t.next;
            if (tb != null && tb.next != t)
                return false;
            if (tn != null && tn.prev != t)
                return false;
            if (tp != null && t != tp.left && t != tp.right)
                return false;
            if (tl != null && (tl.parent != t) || (tl.hash > t.hash))
                return false;
            if (tr != null && (tr.parent != t) || (tr.hash < t.hash))
                return false;
            if (t.red && tl != null && tl.red && tr != null && tr.red)
                return false;
            if (tl != null && !checkInvariantsIm(tl))
                return false;
            if (tr != null && !checkInvariantsIm(tr))
                return false;
            return true;
        }
    }
}
