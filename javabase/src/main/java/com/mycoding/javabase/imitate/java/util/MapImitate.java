package com.mycoding.javabase.imitate.java.util;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 模拟 java.Map
 * user: xiangyu.wang
 * date: 2018/3/15 17:45
 */
public interface MapImitate<K, V> {
    int sizeIm();

    boolean isEmptyIm();

    boolean containsKeyIm(Object key);

    boolean containsValueIm(Object value);

    V getIm(Object key);

    V putIm(K key, V value);

    V removeIm(Object k);

    void putAllIm(MapImitate<? extends K, ? extends V> m);

    void clearIm();

    Set<K> keySetIm();

    Collection<V> valuesIm();

    Set<MapImitate.EntryIm<K, V>> entrySetIm();

    boolean equals(Object o);

    int hashCode();

    interface EntryIm<K, V> {
        K getKeyIm();

        V getValueIm();

        V setValueIm(V value);

        public static <K extends Comparable<? super K>, V> Comparator<MapImitate.EntryIm<K, V>> comparingByKeyIm() {
            return (Comparator<MapImitate.EntryIm<K, V>> & Serializable) (c1, c2) -> c1.getKeyIm().compareTo(c2.getKeyIm());
        }

        public static <K, V extends Comparable<? super V>> Comparator<MapImitate.EntryIm<K, V>> comparingByValueIm() {
            return (Comparator<MapImitate.EntryIm<K, V>> & Serializable) (c1, c2) -> c1.getValueIm().compareTo(c2.getValueIm());
        }

        public static <K, V> Comparator<MapImitate.EntryIm<K, V>> comparingByKeyIm(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<EntryIm<K, V>> & Serializable) (c1, c2) -> cmp.compare(c1.getKeyIm(), c2.getKeyIm());
        }

        public static <K, V> Comparator<MapImitate.EntryIm<K, V>> comparingByValueIm(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<EntryIm<K, V>> & Serializable) (c1, c2) -> cmp.compare(c1.getValueIm(), c2.getValueIm());
        }
    }

    default V getOrDefaultIm(Objects key, V defaultValue) {
        V v;
        return (((v = getIm(key)) != null) || containsKeyIm(key)) ? v : defaultValue;
    }

    default void forEachIm(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (MapImitate.EntryIm<K, V> entryIm : entrySetIm()) {
            K k;
            V v;

            try {
                k = entryIm.getKeyIm();
                v = entryIm.getValueIm();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the com.mycoding.javabase.map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }

    default void replaceAllIm(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        for (MapImitate.EntryIm<K, V> entryIm : entrySetIm()) {
            K k;
            V v;
            try {
                k = entryIm.getKeyIm();
                v = entryIm.getValueIm();
            } catch (IllegalStateException ise) {
                throw new ConcurrentModificationException(ise);
            }
            v = function.apply(k, v);

            try {
                entryIm.setValueIm(v);
            } catch (IllegalStateException ise) {
                throw new ConcurrentModificationException(ise);
            }
        }
    }

    default V putIfAbsentIm(K key, V value) {
        V v = getIm(key);
        if (v == null) {
            v = putIm(key, value);
        }
        return v;
    }

    default boolean removeIm(Object key, Object value) {
        Object curValue = getIm(key);
        if (!Objects.equals(curValue, value) || (curValue == null && !containsKeyIm(key))) {
            return false;
        }
        removeIm(key);
        return true;
    }

    default boolean replaceIm(K key, V oldValue, V newValue) {
        Object curValue = getIm(key);
        if (!Objects.equals(curValue, oldValue) || curValue == null && !containsKeyIm(key)) {
            return false;
        }
        putIm(key, newValue);
        return true;
    }

    default V replaceIm(K key, V value) {
        V curValue;
        if (((curValue = getIm(key)) != null) || containsKeyIm(key)) {
            curValue = putIm(key, value);
        }
        return curValue;
    }

    default V computeIfAbsentIm(K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V v;
        if ((v = getIm(key)) == null) {
            V newValue;
            if ((newValue = mappingFunction.apply(key)) != null) {
                putIm(key, newValue);
                return newValue;
            }
        }
        return v;
    }

    default V computeIfPresentIm(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue;
        if ((oldValue = getIm(key)) != null) {
            V newValue = remappingFunction.apply(key, oldValue);
            if (newValue != null) {
                putIm(key, newValue);
                return newValue;
            } else {
                removeIm(key);
                return null;
            }
        } else {
            return null;
        }
    }

    default V computeIm(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = getIm(key);
        V newValue = remappingFunction.apply(key, oldValue);
        if (newValue == null) {
            if (oldValue != null || containsKeyIm(key)) {
                removeIm(key);
                return null;
            } else {
                return null;
            }
        } else {
            putIm(key, newValue);
            return newValue;
        }
    }

    default V mergeIm(K key, V value, BiFunction<? super V, ? super V,   ? extends V> remappingFunction){
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = getIm(key);
        V newValue = (oldValue == null) ? value : remappingFunction.apply(oldValue, value);
        if (newValue == null){
            removeIm(key);
        }else{
            putIm(key, value);
        }
        return newValue;
    }

}
