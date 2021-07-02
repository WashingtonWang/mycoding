package com.mycoding.javabase.imitate.java.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * todo Arrays 待实现
 * user: xiangyu.wang
 * date: 2018/6/4 18:01
 */
public interface ListImitate<E> extends CollectionImitate<E> {

    int sizeIm();

    boolean isEmptyIm();

    boolean containsIm(Object o);

    IteratorImitate<E> iteratorIm();

    Object[] toArrayIm();

    <T> T[] toArrayIm(T[] a);

    // Modification Operations
    boolean addIm(E e);

    boolean removeIm(Object o);

    // Bulk Modification Operations
    boolean containsAllIm(CollectionImitate<?> c);

    boolean addAllIm(CollectionImitate<? extends E> c);

    boolean addAllIm(int index, CollectionImitate<? extends E> c);

    boolean removeAllIm(CollectionImitate<?> c);

    boolean retainAllIm(CollectionImitate<?> c);

    default void replaceAllIm(UnaryOperator<E> operator){
        Objects.requireNonNull(operator);
        final ListIteratorImitate<E> li = this.listIteratorIm();
        while (li.hasNextIm()){
            li.setIm(operator.apply(li.nextIm()));
        }
    }

    default void sortIm(Comparator<? super E> c){
        Object[] a = this.toArrayIm();
        Arrays.sort(a, (Comparator) c);
        ListIteratorImitate<E> i = this.listIteratorIm();
        for (Object e : a){
            i.nextIm();
            i.setIm((E) e);
        }
    }

    void clearIm();

    boolean equals(Object o);

    int hashCode();

    E getIm(int index);

    E setIm(int index, E element);

    void addIm(int index, E element);

    E removeIm(int index);

    int indexOfIm(Object o);

    int lastIndexOfIm(Object o);

    // List Iterators
    ListIteratorImitate<E> listIteratorIm();

    ListIteratorImitate<E> listIteratorIm(int index);

    ListImitate<E> subListIm(int fromIndex, int toIndex);

    default SpliteratorImitate<E> spliteratorIm(){
        return SpliteratorsImitate.spliteratorIm(this, SpliteratorImitate.ORDERED_IM);
    }
}
