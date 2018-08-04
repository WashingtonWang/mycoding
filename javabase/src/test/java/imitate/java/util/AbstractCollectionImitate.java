package imitate.java.util;

import java.util.Arrays;

/**
 * user: xiangyu.wang
 * date: 2018/6/4 19:27
 */
public abstract class AbstractCollectionImitate<E> implements CollectionImitate<E> {
    protected AbstractCollectionImitate() {
    }

    // Query Operations
    public abstract IteratorImitate<E> iteratorIm();

    public abstract int sizeIm();

    public boolean isEmptyIm(){
        return sizeIm() == 0;
    }

    public boolean containsIm(Object o){
        IteratorImitate<E> it = iteratorIm();
        if (o == null){
            while (it.hasNextIm())
                if (it.nextIm() == null)
                    return true;
        }else{
            while (it.hasNextIm())
                if (o.equals(it.nextIm()))
                    return true;
        }
        return false;
    }

    public Object[] toArrayIm(){
        Object[] r = new Object[sizeIm()];
        IteratorImitate<E> it = iteratorIm();
        for (int i = 0; i < r.length; i++){
            if (!it.hasNextIm())
                return Arrays.copyOf(r, i);
            r[i] = it.nextIm();
        }
        return it.hasNextIm() ? finishToArrayIm(r, it) : r;
    }

    public <T> T[] toArrayIm(T[] a){
        int size
    }

    private static final int MAX_ARRAY_SIZE_IM = Integer.MAX_VALUE - 8;

    private static <T> T[] finishToArrayIm(T[] r, IteratorImitate<?> it){
        int i = r.length;
        while (it.hasNextIm()){
            int cap = r.length;
            if (i == cap){
                int newCap = cap + (cap >> 1) + 1;
                if (newCap - MAX_ARRAY_SIZE_IM > 0)
                    newCap = hugeCapacity(cap + 1);
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T) it.nextIm();
        }
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    private static int hugeCapacity(int minCapacity){
        if (minCapacity < 0)
            throw new OutOfMemoryError("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE_IM) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE_IM;
    }

    // Modification Operations

    public boolean add(E e){
        throw new UnsupportedOperationException();
    }
}
