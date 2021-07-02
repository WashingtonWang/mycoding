package com.mycoding.javabase.imitate.java.util;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

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
                putValIm(hashIm(key), key, value, false, evict);
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

    public V get(Object key) {
        NodeIm<K, V> e;
        return (e = getNodeIm(hashIm(key), key)) == null ? null : e.value;
    }

    final NodeIm<K, V> getNodeIm(int hash, Object key) {
        NodeIm<K, V>[] tab;
        NodeIm<K, V> first = null, e;
        int n;
        K k = null;
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && ((k = first.key) == key) || (key != null && key.equals(k)))
                return first;
        }
        if ((e = first.next) != null) {
            if (first instanceof TreeNodeIm)
                return ((TreeNodeIm<K, V>) first).getTreeNodeIm(hash, key);
            do {
                if (e.hash == hash && ((k = e.key) == key) || (key != null && key.equals(k)))
                    return e;
            } while ((e = e.next) != null);
        }
        return null;
    }

    @Override
    public boolean containsKeyIm(Object key) {
        return false;
    }

    final V putValIm(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        NodeIm<K, V>[] tab;
        NodeIm<K, V> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resizeIm()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNodeIm(hash, key, value, null);
        else {
            NodeIm<K, V> e;
            K k = null;
            if (p.hash == hash && ((k = p.key) == key) || (key != null && key.equals(k)))
                e = p;
            else if (p instanceof TreeNodeIm)
                e = ((TreeNodeIm<K, V>) p).putTreeValIm(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNodeIm(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD_IM - 1)
                            treeifyBinIm(tab, hash);
                        break;
                    }
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) {
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeImAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resizeIm();
        afterNodeImInsertion(evict);
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
                    else if (e instanceof TreeNodeIm)
                        ((TreeNodeIm<K, V>) e).splitIm(this, newTab, j, oldCap);
                    else {
                        NodeIm<K, V> loHead = null, loTail = null;
                        NodeIm<K, V> hiHead = null, hiTail = null;
                        NodeIm<K, V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            } else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    final void treeifyBinIm(NodeIm<K, V>[] tab, int hash) {
        int n, index;
        NodeIm<K, V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY_IM)
            resizeIm();
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            TreeNodeIm<K, V> hd = null, tl = null;
            do {
                TreeNodeIm<K, V> p = replacementTreeNodeIm(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                hd.treeifyIm(tab);
        }
    }

    public void putAll(MapImitate<? extends K, ? extends V> m) {
        putMapEntriesIm(m, true);
    }

    public V remove(Object key) {
        NodeIm<K, V> e;
        return (e = removeNodeIm(hashIm(key), key, null, false, true)) == null ?
                null : e.value;
    }

    final NodeIm<K, V> removeNodeIm(int hash, Object key, Object value, boolean matchValue, boolean movable) {
        NodeIm<K, V>[] tab;
        NodeIm<K, V> p;
        int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {
            NodeIm<K, V> node = null, e;
            K k = null;
            V v;
            if (p.hash == hash && ((k = p.key) == key) || (key != null) && key.equals(k))
                node = p;
            else if ((e = p.next) != null) {
                if (p instanceof TreeNodeIm)
                    node = ((TreeNodeIm<K, V>) p).getTreeNodeIm(hash, key);
                else {
                    do {
                        if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                    } while ((e = e.next) != null);
                }
            }
            if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
                if (node instanceof TreeNodeIm)
                    ((TreeNodeIm<K, V>) node).removeTreeNodeIm(this, tab, movable);
                else if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                afterNodeImRemoval(node);
                return node;
            }
        }
        return null;
    }

    public void clearIm() {
        NodeIm<K, V>[] tab;
        modCount++;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; i++)
                tab[i] = null;
        }
    }

    public boolean containsValueIm(Object value) {
        NodeIm<K, V>[] tab;
        V v;
        if ((tab = table) != null && size > 0) {
            for (int i = 0; i < tab.length; i++) {
                for (NodeIm<K, V> e = tab[i]; e != null; e = e.next) {
                    if ((v = e.value) == value || (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }

    public Set<K> keySetIm() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new KeySetIm();
            keySet = ks;
        }
        return ks;
    }

    final class KeySetIm extends AbstractSet<K> {
        public final int size() {
            return size;
        }

        public final void clear() {
            HashMapImitate.this.clearIm();
        }

        public final Iterator<K> iterator() {
            return new KeyIteratorIm();
        }

        public final boolean contains(Object o) {
            return containsKeyIm(o);
        }

        public final boolean remove(Object key) {
            return removeNodeIm(hashIm(key), key, null, false, true) != null;
        }

        public final Spliterator<K> spliterator() {
            return new KeySpliteratorIm<>(HashMapImitate.this, 0, -1, 0, 0);
        }

        public final void forEach(Consumer<? super K> action) {
            NodeIm<K, V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; i++) {
                    for (NodeIm<K, V> e = tab[i]; e != null; e = e.next)
                        action.accept(e.key);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }

    public Collection<V> values() {
        Collection<V> vs = values;
        if (vs == null) {
            vs = new ValuesIm();
            values = vs;
        }
        return vs;
    }

    final class ValuesIm extends AbstractCollection<V> {

        @Override
        public final int size() {
            return size;
        }

        public final void clear() {
            HashMapImitate.this.clearIm();
        }

        @Override
        public final Iterator<V> iterator() {
            return new ValueIteratorIm();
        }

        public final boolean contains(Object o) {
            return containsValueIm(o);
        }

        public final Spliterator<V> spliterator() {
            return new ValueSpliteratorIm<>(HashMapImitate.this, 0, -1, 0, 0);
        }

        public final void forEach(Consumer<? super V> action) {
            NodeIm<K, V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; i++) {
                    for (NodeIm<K, V> e = tab[i]; e != null; e = e.next)
                        action.accept(e.value);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }

    public Set<MapImitate.EntryIm<K, V>> entrySetIm() {
        Set<MapImitate.EntryIm<K, V>> es;
        return (es = entryImSet) == null ? (entryImSet = new EntrySetIm()) : es;
    }

    final class EntrySetIm extends AbstractSet<MapImitate.EntryIm<K, V>> {

        public final void clear() {
            HashMapImitate.this.clearIm();
        }

        @Override
        public Iterator<EntryIm<K, V>> iterator() {
            return new EntryIteratorIm();
        }

        public final boolean contains(Object o) {
            if (!(o instanceof MapImitate.EntryIm))
                return false;
            MapImitate.EntryIm<?, ?> e = (EntryIm<?, ?>) o;
            Object key = e.getKeyIm();
            NodeIm<K, V> candidate = getNodeIm(hashIm(key), key);
            return candidate != null && candidate.equals(e);
        }

        public final boolean remove(Object o) {
            if (o instanceof MapImitate.EntryIm) {
                MapImitate.EntryIm<?, ?> e = (EntryIm<?, ?>) o;
                Object key = e.getKeyIm();
                Object value = e.getValueIm();
                return removeNodeIm(hashIm(key), key, value, true, true) != null;
            }
            return false;
        }

        public final Spliterator<MapImitate.EntryIm<K, V>> spliterator() {
            return new EntrySpliteratorIm<>(HashMapImitate.this, 0, -1, 0, 0);
        }

        public final void forEach(Consumer<? super MapImitate.EntryIm<K, V>> action) {
            NodeIm<K, V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; i++) {
                    for (NodeIm<K, V> e = tab[i]; e != null; e = e.next)
                        action.accept(e);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        @Override
        public int size() {
            return size;
        }
    }

    // Overrides of JDK8 Map extension methods

    public V getOrDefaultIm(Object key, V defaultValue) {
        NodeIm<K, V> e;
        return (e = getNodeIm(hashIm(key), key)) == null ? defaultValue : e.value;
    }

    public V putIfAbsentIm(K key, V value) {
        return putValIm(hashIm(key), key, value, true, true);
    }

    public boolean removeIm(Object key, Object value) {
        return removeNodeIm(hashIm(key), key, value, true, true) != null;
    }

    public boolean replaceIm(K key, V oldValue, V newValue) {
        NodeIm<K, V> e;
        V v;
        if ((e = getNodeIm(hashIm(key), key)) != null &&
                ((v = e.value) == oldValue || (v != null && v.equals(oldValue)))) {
            e.value = newValue;
            afterNodeImAccess(e);
            return true;
        }
        return false;
    }

    public V replaceIm(K key, V value) {
        NodeIm<K, V> e;
        if ((e = getNodeIm(hashIm(key), key)) != null) {
            V oldValue = e.value;
            e.value = value;
            afterNodeImAccess(e);
            return oldValue;
        }
        return null;
    }

    public V computeIfAbsentIm(K key, Function<? super K, ? extends V> mappingFunction) {
        if (mappingFunction == null)
            throw new NullPointerException();
        int hash = hashIm(key);
        NodeIm<K, V>[] tab;
        NodeIm<K, V> first;
        int n, i;
        int binCount = 0;
        TreeNodeIm<K, V> t = null;
        NodeIm<K, V> old = null;
        if (size > threshold || (tab = table) == null || (n = tab.length) == 0)
            n = (tab = resizeIm()).length;
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNodeIm)
                old = (t = (TreeNodeIm<K, V>) first).getTreeNodeIm(hash, key);
            else {
                NodeIm<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
            V oldValue;
            if (old != null && (oldValue = old.value) != null) {
                afterNodeImAccess(old);
                return oldValue;
            }
        }
        V v = mappingFunction.apply(key);
        if (v == null)
            return null;
        else if (old != null) {
            old.value = v;
            afterNodeImAccess(old);
            return v;
        } else if (t != null)
            t.putTreeValIm(this, tab, hash, key, v);
        else {
            tab[i] = newNodeIm(hash, key, v, first);
            if (binCount >= TREEIFY_THRESHOLD_IM - 1)
                treeifyBinIm(tab, hash);
        }
        ++modCount;
        ++size;
        afterNodeImInsertion(true);
        return v;
    }

    public V computeIfPresentIm(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null)
            throw new NullPointerException();
        NodeIm<K, V> e;
        V oldValue;
        int hash = hashIm(key);
        if ((e = getNodeIm(hash, key)) != null && (oldValue = e.value) != null) {
            V v = remappingFunction.apply(key, oldValue);
            if (v != null) {
                e.value = v;
                afterNodeImAccess(e);
                return v;
            } else
                removeNodeIm(hash, key, null, false, true);
        }
        return null;
    }

    public V computeIm(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null)
            throw new NullPointerException();
        int hash = hashIm(key);
        NodeIm<K, V>[] tab;
        NodeIm<K, V> first;
        int n, i;
        int binCount = 0;
        TreeNodeIm<K, V> t = null;
        NodeIm<K, V> old = null;
        if (size > threshold || (tab = table) == null || (n = tab.length) == 0)
            n = (tab = resizeIm()).length;
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNodeIm)
                old = (t = (TreeNodeIm<K, V>) first).getTreeNodeIm(hash, key);
            else {
                NodeIm<K, V> e = first;
                K k = null;
                do {
                    if (e.hash == hash && ((k = e.key) == key) || (key != null && key.equals(k))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        V oldValue = (old == null) ? null : old.value;
        V v = remappingFunction.apply(key, oldValue);
        if (old != null) {
            if (v != null) {
                old.value = v;
                afterNodeImAccess(old);
            } else
                removeNodeIm(hash, key, null, false, true);
        } else if (v != null) {
            if (t != null)
                t.putTreeValIm(this, tab, hash, key, v);
            else {
                tab[i] = newNodeIm(hash, key, v, first);
                if (binCount >= TREEIFY_THRESHOLD_IM - 1)
                    treeifyBinIm(tab, hash);
            }
            ++modCount;
            ++size;
            afterNodeImInsertion(true);
        }
        return v;
    }

    public V mergeIm(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if (value == null)
            throw new NullPointerException();
        if (remappingFunction == null)
            throw new NullPointerException();
        int hash = hashIm(key);
        NodeIm<K, V>[] tab;
        NodeIm<K, V> first;
        int n, i;
        int binCount = 0;
        TreeNodeIm<K, V> t = null;
        NodeIm<K, V> old = null;
        if ((size > threshold || (tab = table) == null || (n = tab.length) == 0))
            n = (tab = resizeIm()).length;
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNodeIm)
                old = (t = (TreeNodeIm<K, V>) first).getTreeNodeIm(hash, key);
            else {
                NodeIm<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        if (old != null) {
            V v;
            if (old.value != null)
                v = remappingFunction.apply(old.value, value);
            else
                v = value;
            if (v != null) {
                old.value = v;
                afterNodeImAccess(old);
            } else
                removeNodeIm(hash, key, null, false, true);
            return v;
        }
        if (value != null) {
            if (t != null)
                t.putTreeValIm(this, tab, hash, key, value);
            else {
                tab[i] = newNodeIm(hash, key, value, first);
                if (binCount >= TREEIFY_THRESHOLD_IM - 1)
                    treeifyBinIm(tab, hash);
            }
            ++modCount;
            ++size;
            afterNodeImInsertion(true);
        }
        return value;
    }

    public void forEachIm(BiConsumer<? super K, ? super V> action) {
        NodeIm<K, V>[] tab;
        if (action == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (NodeIm<K, V> e = tab[i]; e != null; e = e.next)
                    action.accept(e.key, e.value);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    public void replaceAllIm(BiFunction<? super K, ? super V, ? extends V> function) {
        NodeIm<K, V>[] tab;
        if (function == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; i++) {
                for (NodeIm<K, V> e = tab[i]; e != null; e = e.next)
                    e.value = function.apply(e.key, e.value);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    /* ------------------------------------------------------------ */
    // Cloning and serialization
    public Object clone() {
        HashMapImitate<K, V> result;
        try {
            result = (HashMapImitate<K, V>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
        result.reinitialize();
        result.putMapEntriesIm(this, false);
        return result;
    }

    final float loadFactor() {
        return loadFactor;
    }

    final int capacity() {
        return (table != null) ? table.length : (threshold > 0) ? threshold : DEFAULT_INITIAL_CAPACITY_IM;
    }

    private void writeObjectIm(ObjectOutputStream s) throws IOException {
        int buckets = capacity();
        s.defaultWriteObject();
        s.writeInt(buckets);
        s.writeInt(size);
        internalWriteEntries(s);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        reinitialize();
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new InvalidObjectException("Illegal load factor: " + loadFactor);
        s.readInt();
        int mapppings = s.readInt();
        if (mapppings < 0)
            throw new InvalidObjectException("Illegal mapping count: " + mapppings);
        else if (mapppings > 0) {
            float lf = Math.min(Math.max(0.25f, loadFactor), 4.0f);
            float fc = (float) mapppings / lf + 1.0f;
            int cap = ((fc < DEFAULT_INITIAL_CAPACITY_IM) ? DEFAULT_INITIAL_CAPACITY_IM :
                    (fc >= MAXIMUM_CAPACITY_IM) ? MAXIMUM_CAPACITY_IM : tableSizeForIm((int) fc));
            float ft = (float) cap * lf;
            threshold = ((cap < MAXIMUM_CAPACITY_IM && ft < MAXIMUM_CAPACITY_IM) ? (int) ft : Integer.MAX_VALUE);
            NodeIm<K, V>[] tab = (NodeIm<K, V>[]) new NodeIm[cap];
            table = tab;
            for (int i = 0; i < mapppings; i++) {
                K key = (K) s.readObject();
                V value = (V) s.readObject();
                putValIm(hashIm(key), key, value, false, false);
            }
        }
    }

    /* ------------------------------------------------------------ */
    // iterators
    abstract class HashIteratorIm{
        NodeIm<K, V> next;
        NodeIm<K, V> current;
        int expectedModCount;
        int index;

        HashIteratorIm(){
            expectedModCount = modCount;
            NodeIm<K, V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0){
                do { } while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNextIm(){
            return next != null;
        }

        final NodeIm<K, V> nextNodeIm(){
            NodeIm<K, V>[] t;
            NodeIm<K, V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null){
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void removeIm(){
            NodeIm<K, V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            K key = p.key;
            removeNodeIm(hashIm(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }

    final class KeyIteratorIm extends HashIteratorIm implements Iterator<K>{

        @Override
        public boolean hasNext() {
            return hasNextIm();
        }

        @Override
        public K next() {
            return super.nextNodeIm().key;
        }
    }

    final class ValueIteratorIm extends HashIteratorIm implements Iterator<V>{

        @Override
        public boolean hasNext() {
            return hasNextIm();
        }

        @Override
        public V next() {
            return super.nextNodeIm().value;
        }
    }

    final class EntryIteratorIm extends HashIteratorIm implements Iterator<MapImitate.EntryIm<K, V>>{

        @Override
        public boolean hasNext() {
            return hasNextIm();
        }

        @Override
        public EntryIm<K, V> next() {
            return super.nextNodeIm();
        }
    }

    /* ------------------------------------------------------------ */
    // spliterators
    static class HashMapImSpliterator<K, V> {
        final HashMapImitate<K, V> map;
        NodeIm<K, V> current;
        int index;
        int fence;
        int est;
        int expectedModCount;

        HashMapImSpliterator(HashMapImitate<K, V> m, int origin, int fence, int est, int expectedModCount) {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence() {
            int hi;
            if ((hi = fence) < 0) {
                HashMapImitate<K, V> m = map;
                est = m.size;
                expectedModCount = m.modCount;
                NodeIm<K, V>[] tab = m.table;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            return hi;
        }

        public final long estimateSize() {
            getFence();
            return (long) est;
        }
    }

    static final class KeySpliteratorIm<K, V> extends HashMapImSpliterator<K, V> implements Spliterator<K> {
        KeySpliteratorIm(HashMapImitate<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }


        @Override
        public boolean tryAdvance(Consumer<? super K> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            NodeIm<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        K k = current.key;
                        current = current.next;
                        action.accept(k);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public KeySpliteratorIm<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null : new KeySpliteratorIm<>(map, lo, index = mid, est >>>= 1, expectedModCount);
        }

        public void forEachRemaining(Consumer<? super K> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            HashMapImitate<K, V> m = map;
            NodeIm<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi && (i = index) >= 0 && (i < (index = hi) || current != null)) {
                NodeIm<K, V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p.key);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) | Spliterator.DISTINCT;
        }
    }

    static final class ValueSpliteratorIm<K, V> extends HashMapImSpliterator<K, V> implements Spliterator<V> {
        ValueSpliteratorIm(HashMapImitate<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override
        public boolean tryAdvance(Consumer<? super V> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            NodeIm<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        V v = current.value;
                        current = current.next;
                        action.accept(v);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public ValueSpliteratorIm<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null : new ValueSpliteratorIm<>(map, lo, index = mid, est >>>= 1, expectedModCount);
        }

        public void forEachRemaining(Consumer<? super V> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            HashMapImitate<K, V> m = map;
            NodeIm<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi && (i = index) >= 0 && (i < (index = hi) || current != null)) {
                NodeIm<K, V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p.value);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
        }
    }

    static final class EntrySpliteratorIm<K, V> extends HashMapImSpliterator<K, V>
            implements Spliterator<MapImitate.EntryIm<K, V>> {

        EntrySpliteratorIm(HashMapImitate<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override
        public boolean tryAdvance(Consumer<? super EntryIm<K, V>> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            NodeIm<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        NodeIm<K, V> e = current;
                        current = current.next;
                        action.accept(e);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public EntrySpliteratorIm<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null : new EntrySpliteratorIm<>(map, lo, index = mid, est >>> 1, expectedModCount);
        }

        public void forEachRemaining(Consumer<? super MapImitate.EntryIm<K, V>> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            HashMapImitate<K, V> m = map;
            NodeIm<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi && (i = index) >= 0 && (i < (index = hi) || current != null)) {
                NodeIm<K, V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) | Spliterator.DISTINCT;
        }
    }

    /* ------------------------------------------------------------ */
    // LinkedHashMap support
    NodeIm<K, V> newNodeIm(int hash, K key, V value, NodeIm<K, V> next) {
        return new NodeIm<>(hash, key, value, next);
    }

    NodeIm<K, V> replacementNodeIm(NodeIm<K, V> p, NodeIm<K, V> next) {
        return new NodeIm<>(p.hash, p.key, p.value, next);
    }

    TreeNodeIm<K, V> newTreeNodeIm(int hash, K key, V value, NodeIm<K, V> next) {
        return new TreeNodeIm<>(hash, key, value, next);
    }

    TreeNodeIm<K, V> replacementTreeNodeIm(NodeIm<K, V> p, NodeIm<K, V> next) {
        return new TreeNodeIm<>(p.hash, p.key, p.value, next);
    }

    void reinitialize() {
        table = null;
        entryImSet = null;
        keySet = null;
        values = null;
        modCount = 0;
        threshold = 0;
        size = 0;
    }

    void afterNodeImAccess(NodeIm<K, V> p) {
    }

    void afterNodeImInsertion(boolean evict) {
    }

    void afterNodeImRemoval(NodeIm<K, V> p) {
    }

    void internalWriteEntries(ObjectOutputStream s) throws IOException {
        NodeIm<K, V>[] tab;
        if (size > 0 && (tab = table) != null) {
            for (int i = 0; i < tab.length; ++i) {
                for (NodeIm<K, V> e = tab[i]; e != null; e = e.next) {
                    s.writeObject(e.key);
                    s.writeObject(e.value);
                }
            }
        }
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
            if (root == null || root.right == null || (rl = root.left) == null || rl.left == null) {
                tab[index] = first.untreeifyIm(map);
                return;
            }
            TreeNodeIm<K, V> p = this, pl = left, pr = right, replacement;
            if (pl != null && pr != null) {
                TreeNodeIm<K, V> s = pr, sl;
                while ((sl = s.left) != null)
                    s = sl;
                boolean c = s.red;
                s.red = p.red;
                p.red = c;
                TreeNodeIm<K, V> sr = s.right;
                TreeNodeIm<K, V> pp = p.parent;
                if (s == pr) {
                    p.parent = s;
                    s.right = p;
                } else {
                    TreeNodeIm<K, V> sp = s.parent;
                    if ((p.parent = sp) != null) {
                        if (s == sp.left)
                            sp.left = p;
                        else
                            sp.right = p;
                    }
                    if ((s.right = pr) != null)
                        pr.parent = s;
                }
                p.left = null;
                if ((p.right = sr) != null)
                    sr.right = p;
                if ((s.left = pl) != null)
                    pl.parent = s;
                if ((s.parent = pp) != null)
                    root = s;
                else if (p == pp.left)
                    pp.left = s;
                else
                    pp.right = s;
                if (sr != null)
                    replacement = sr;
                else
                    replacement = p;
            } else if (pl != null)
                replacement = pl;
            else if (pr != null)
                replacement = pr;
            else
                replacement = p;
            if (replacement != p) {
                TreeNodeIm<K, V> pp = replacement.parent = p.parent;
                if (pp == null)
                    root = replacement;
                else if (p == pp.left)
                    pp.left = replacement;
                else
                    pp.right = replacement;
                p.left = p.right = p.parent = null;
            }
            TreeNodeIm<K, V> r = p.red ? root : balanceDeletionIm(root, replacement);
            if (replacement == p) {
                TreeNodeIm<K, V> pp = p.parent;
                p.parent = null;
                if (pp != null) {
                    if (p == pp.left)
                        pp.left = null;
                    else if (p == pp.right)
                        pp.right = null;
                }
            }
            if (movable)
                moveRootToFrontIm(tab, r);
        }

        final void splitIm(HashMapImitate<K, V> map, NodeIm<K, V>[] tab, int index, int bit) {
            TreeNodeIm<K, V> b = this;
            TreeNodeIm<K, V> loHead = null, loTail = null;
            TreeNodeIm<K, V> hiHead = null, hiTail = null;
            int lc = 0, hc = 0;
            for (TreeNodeIm<K, V> e = b, next; e != null; e = next) {
                next = (TreeNodeIm<K, V>) e.next;
                e.next = null;
                if ((e.hash & bit) == 0) {
                    if ((e.prev = loTail) == null)
                        loHead = e;
                    else
                        loTail.next = e;
                    loTail = e;
                    ++lc;
                } else {
                    if ((e.prev = hiTail) == null)
                        hiHead = e;
                    else
                        hiTail.next = e;
                    hiTail = e;
                    ++hc;
                }
                if (loHead != null) {
                    if (lc <= UNTREEIFY_THRESHOLD_IM)
                        tab[index] = loHead.untreeifyIm(map);
                    else {
                        tab[index] = loHead;
                        if (hiHead != null)
                            loHead.treeifyIm(tab);
                    }
                }
                if (hiHead != null) {
                    if (hc <= UNTREEIFY_THRESHOLD_IM)
                        tab[index + bit] = hiHead.untreeifyIm(map);
                    else {
                        tab[index + bit] = hiHead;
                        if (loHead != null)
                            hiHead.treeifyIm(tab);
                    }
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

        static <K, V> TreeNodeIm<K, V> balanceDeletionIm(TreeNodeIm<K, V> root, TreeNodeIm<K, V> x) {
            for (TreeNodeIm<K, V> xp, xpl, xpr; ; ) {
                if (x == null || x == root)
                    return root;
                else if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                } else if (x.red) {
                    x.red = false;
                    return root;
                } else if ((xpl = xp.left) == x) {
                    if ((xpr = xp.right) != null && xpr.red) {
                        xpr.red = false;
                        xp.red = true;
                        root = rotateLeftIm(root, xp);
                        xpr = (xp = x.parent) == null ? null : xp.right;
                    }
                    if (xpr == null)
                        x = xp;
                    else {
                        TreeNodeIm<K, V> sl = xpr.left, sr = xpr.right;
                        if ((sr == null || !sr.red) && (sl == null || !sl.red)) {
                            xpr.red = true;
                            x = xp;
                        } else {
                            if (sr == null || !sr.red) {
                                if (sl != null)
                                    sl.red = false;
                                xpr.red = true;
                                root = rotateRightIm(root, xpr);
                                xpr = (xp = x.parent) == null ? null : xp.right;
                            }
                            if (xpr != null) {
                                xpr.red = (xp == null) ? false : xp.red;
                                if ((sr = xpr.right) != null)
                                    sr.red = false;
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateLeftIm(root, xp);
                            }
                            x = root;
                        }
                    }
                } else {
                    if (xpl != null && xpl.red) {
                        xpl.red = false;
                        xp.red = true;
                        root = rotateRightIm(root, xp);
                        xpl = (xp = xp.parent) == null ? null : xp.left;
                    }
                    if (xpl == null)
                        x = xp;
                    else {
                        TreeNodeIm<K, V> sl = xpl.left, sr = xpl.right;
                        if ((sl == null || !sl.red) && (sr == null || !sr.red)) {
                            xpl.red = true;
                            x = xp;
                        } else {
                            if (sl == null || !sl.red) {
                                if (sr != null)
                                    sr.red = false;
                                xpl.red = true;
                                root = rotateLeftIm(root, xpl);
                                xpl = (xp = xp.parent) == null ? null : xp.left;
                            }
                            if (xpl != null) {
                                xpl.red = (xp == null) ? false : xp.red;
                                if ((sl = xpl.left) != null)
                                    sl.red = false;
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateRightIm(root, xp);
                            }
                            x = root;
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