package com.mycoding.javabase.imitate.java.util;

import com.mycoding.javabase.imitate.java.lang.IterableImitate;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * user: xiangyu.wang
 * date: 2018/5/15 18:10
 */
public interface CollectionImitate<E> extends IterableImitate<E> {

    int sizeIm();

    boolean isEmptyIm();

    boolean containsIm(Object o);

    IteratorImitate<E> iteratorIm();

    Object[] toArrayIm();

    <T> T[] toArrayIm(T[] a);

    boolean addIm(E e);

    boolean removeIm(Object o);

    boolean containsAllIm(CollectionImitate<?> c);

    boolean addAllIm(CollectionImitate<? extends E> c);

    boolean removeAllIm(CollectionImitate<?> c);

    default boolean removeIfIm(Predicate<? super E> filter){
        Objects.requireNonNull(filter);
        boolean removed = false;
        final IteratorImitate<E> each = iteratorIm();
        while (each.hasNextIm()){
            if (filter.test(each.nextIm())){
                each.removeIm();
                removed = true;
            }
        }
        return removed;
    }

    boolean retainAllIm(CollectionImitate<?> c);

    void clearIm();

    boolean equals(Object o);

    int hashCode();

    default SpliteratorImitate<E> spliteratorIm(){
        return SpliteratorsImitate.spliteratorIm(this, 0);
    }

    //todo 以下两个方法需要 在StreamSupportImitate里添加(以后需要再做)
    //default Stream<E> stremIm(){
    //    return StreamSupport.stream(spliteratorIm(), false);
    //}
    //
    //default Stream<E> parallelStream(){
    //    return StreamSupport.stream(spliteratorIm(), true);
    //}

}
