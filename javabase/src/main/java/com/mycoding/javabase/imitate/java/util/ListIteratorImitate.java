package com.mycoding.javabase.imitate.java.util;

/**
 * user: xiangyu.wang
 * date: 2018/6/4 18:31
 */
public interface ListIteratorImitate<E> extends IteratorImitate<E> {
    // Query Operations
    boolean hasNextIm();

    E nextIm();

    boolean hasPreviousIm();

    E previousIm();

    int nextIndexIm();

    int previousIndexIm();

    // Modification Operations
    void removeIm();

    void setIm(E e);

    void addIm(E e);
}
