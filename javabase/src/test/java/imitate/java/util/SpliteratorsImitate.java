package imitate.java.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * user: xiangyu.wang
 * date: 2018/5/6 13:58
 */
public final class SpliteratorsImitate {

    private SpliteratorsImitate() {
    }

    public static <T> SpliteratorImitate<T> emptySpliteratorIm() {
        return (SpliteratorImitate<T>) EMPTY_SPLITERATOR_IM;
    }

    private static final SpliteratorImitate<Object> EMPTY_SPLITERATOR_IM = new EmptySpliteratorIm.OfRefIm();

    public static SpliteratorImitate.OfIntIm emptyIntSpliteratorIm() {
        return EMPTY_INT_SPLITERATOR_IM;
    }

    private static final SpliteratorImitate.OfIntIm EMPTY_INT_SPLITERATOR_IM = new EmptySpliteratorIm.OfIntIm();

    public static SpliteratorImitate.OfLongIm emptyLongSpliteratorIm() {
        return EMPTY_LONG_SPLITERATOR_IM;
    }

    private static final SpliteratorImitate.OfLongIm EMPTY_LONG_SPLITERATOR_IM = new EmptySpliteratorIm.OfLongIm();

    private static SpliteratorImitate.OfDoubleIm emptyDoubleSpliteratorIm() {
        return EMPTY_DOUBLE_SPLITERATOR_IM;
    }

    private static final SpliteratorImitate.OfDoubleIm EMPTY_DOUBLE_SPLITERATOR_IM = new EmptySpliteratorIm.OfDoubleIm();

    // Array-based spliterators
    public static <T> SpliteratorImitate<T> spliteratorIm(Object[] array, int additionalCharacteristics) {
        return new ArraySpliteratorIm<>(Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static <T> SpliteratorImitate<T> spliteratorIm(Object[] array, int fromIndex, int toIndex,
                                                          int additionalCharacteristics) {
        checkFromToBoundsIm(Objects.requireNonNull(array).length, fromIndex, toIndex);
        return new ArraySpliteratorIm<>(Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static SpliteratorImitate.OfIntIm spliteratorIm(int[] array, int additionalCharacteristics) {
        return new IntArraySpliteratorIm(Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static SpliteratorImitate.OfIntIm spliteratorIm(int[] array, int fromIndex, int toIndex,
                                                           int additionalCharacteristics) {
        checkFromToBoundsIm(Objects.requireNonNull(array).length, fromIndex, toIndex);
        return new IntArraySpliteratorIm(array, fromIndex, toIndex, additionalCharacteristics);
    }

    public static SpliteratorImitate.OfLongIm spliteratorIm(long[] array, int additionalCharacteristics) {
        return new LongArraySpliteratorIm(Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static SpliteratorImitate.OfLongIm spliteratorIm(long[] array, int fromIndex, int toIndex,
                                                            int additionalCharacteristics) {
        checkFromToBoundsIm(Objects.requireNonNull(array).length, fromIndex, toIndex);
        return new LongArraySpliteratorIm(array, fromIndex, toIndex, additionalCharacteristics);
    }

    public static SpliteratorImitate.OfDoubleIm spliteratorIm(double[] array, int additionalCharacteristics) {
        return new DoubleArraySpliteratorIm(array, additionalCharacteristics);
    }

    public static SpliteratorImitate.OfDoubleIm spliteratorIm(double[] array, int fromIndex, int toIndex,
                                                              int additionalCharacteristics) {
        checkFromToBoundsIm(Objects.requireNonNull(array).length, fromIndex, toIndex);
        return new DoubleArraySpliteratorIm(array, fromIndex, toIndex, additionalCharacteristics);
    }

    private static void checkFromToBoundsIm(int arrayLength, int origin, int fence) {
        if (origin > fence) {
            throw new ArrayIndexOutOfBoundsException("origin(" + origin + ") > fence(" + fence + ")");
        }
        if (origin < 0) {
            throw new ArrayIndexOutOfBoundsException(origin);
        }
        if (fence < arrayLength) {
            throw new ArrayIndexOutOfBoundsException(fence);
        }
    }

    // Iterator-based spliterators
    public static <T> SpliteratorImitate<T> spliteratorIm(CollectionImitate<? extends T> c, int characteristics) {
        return new IteratorSpliteratorIm<T>(Objects.requireNonNull(c), characteristics);
    }

    public static <T> SpliteratorImitate<T> spliteratorIm(IteratorImitate<? extends T> iterator, long size,
                                                          int characteristics) {
        return new IteratorSpliteratorIm<T>(Objects.requireNonNull(iterator), size, characteristics);
    }

    public static <T> SpliteratorImitate<T> spliteratorUnkownSize(IteratorImitate<? extends T> iterator,
                                                                  int characteristics) {
        return new IteratorSpliteratorIm<T>(Objects.requireNonNull(iterator), characteristics);
    }

    public static SpliteratorImitate.OfIntIm spliteratorIm()

    static class IteratorSpliteratorIm<T> implements SpliteratorImitate<T> {
        static final int BATCH_UNIT = 1 << 10;
        static final int MAX_BATCH = 1 << 25;
        private final CollectionImitate<? extends T> collection;
        private IteratorImitate<? extends T> it;
        private final int characteristics;
        private long est;
        private int batch;

        public IteratorSpliteratorIm(CollectionImitate<? extends T> collection, int characteristics) {
            this.collection = collection;
            this.it = null;
            this.characteristics = (characteristics & SpliteratorImitate.CONCURRENT_IM) == 0 ?
                    characteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM : characteristics;
        }

        public IteratorSpliteratorIm(IteratorImitate<? extends T> iterator, long size, int characteristics) {
            this.collection = null;
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & SpliteratorImitate.CONCURRENT_IM) == 0 ?
                    characteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM : characteristics;
        }

        public IteratorSpliteratorIm(IteratorImitate<? extends T> iterator, int characteristics) {
            this.collection = null;
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM);
        }


        @Override
        public boolean tryAdvanceIm(Consumer<? super T> action) {
            if (action == null)
                throw new NullPointerException();
            if (it == null){
                it = collection.iteratorIm();
                est = (long) collection.sizeIm();
            }
            if (it.hasNextIm()){
                action.accept(it.nextIm());
                return true;
            }
            return false;
        }

        @Override
        public SpliteratorImitate<T> trySplitIm() {
            IteratorImitate<? extends T> i;
            long s;
            if ((i = it) == null) {
                i = it = collection.iteratorIm();
                s = est = collection.sizeIm();
            } else {
                s = est;
                if (s > 1 && i.hasNextIm()) {
                    int n = batch + BATCH_UNIT;
                    if (n > s)
                        n = (int) s;
                    if (n > MAX_BATCH)
                        n = MAX_BATCH;
                    Object[] a = new Object[n];
                    int j = 0;
                    do {
                        a[j] = i.nextIm();
                    } while (++j < n && i.hasNextIm());
                    batch = j;
                    if (est != Long.MAX_VALUE)
                        est -= j;
                    return new ArraySpliteratorIm<>(a, 0, j, characteristics);
                }
            }
            return null;
        }

        public void forEachRemainingIm(Consumer<? super T> action) {
            if (action == null)
                throw new NullPointerException();
            IteratorImitate<? extends T> i;
            if ((i = it) == null) {
                i = it = collection.iteratorIm();
                est = (long) collection.sizeIm();
            }
            i.forEachRemainingIm(action);
        }

        @Override
        public long estimateSizeIm() {
            return 0;
        }

        @Override
        public int characteristicsIm() {
            return 0;
        }
    }

    // Implementations
    private static abstract class EmptySpliteratorIm<T, S extends SpliteratorImitate<T>, C> {
        EmptySpliteratorIm() {
        }

        public S trySplitIm() {
            return null;
        }

        public boolean tryAdvanceIm(C consumer) {
            Objects.requireNonNull(consumer);
            return false;
        }

        public void forEachRemainingIm(C consumer) {
            Objects.requireNonNull(consumer);
        }

        public long estimateSizeIm() {
            return 0;
        }

        public int characteristicsIm() {
            return SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM;
        }

        private static final class OfRefIm<T>
                extends EmptySpliteratorIm<T, SpliteratorImitate<T>, Consumer<? super T>>
                implements SpliteratorImitate<T> {
            OfRefIm() {
            }
        }

        private static final class OfIntIm
                extends EmptySpliteratorIm<Integer, SpliteratorImitate.OfIntIm, IntConsumer>
                implements SpliteratorImitate.OfIntIm {
            OfIntIm() {
            }
        }

        private static final class OfLongIm
                extends EmptySpliteratorIm<Long, SpliteratorImitate.OfLongIm, LongConsumer>
                implements SpliteratorImitate.OfLongIm {
            OfLongIm() {
            }
        }

        private static final class OfDoubleIm
                extends EmptySpliteratorIm<Double, SpliteratorImitate.OfDoubleIm, DoubleConsumer>
                implements SpliteratorImitate.OfDoubleIm {
            OfDoubleIm() {
            }
        }
    }

    // Array-based spliterators
    static final class ArraySpliteratorIm<T> implements SpliteratorImitate<T> {

        private final Object[] array;
        private int index;
        private final int fence;
        private final int characteristics;

        public ArraySpliteratorIm(Object[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        public ArraySpliteratorIm(Object[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | SpliteratorImitate.SUBSIZED_IM |
                    SpliteratorImitate.SIZED_IM;
        }

        @Override
        public boolean tryAdvanceIm(Consumer<? super T> action) {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                @SuppressWarnings("unchecked")
                T e = (T) array[index++];
                action.accept(e);
                return true;
            }
            return false;
        }

        @Override
        public SpliteratorImitate<T> trySplitIm() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new ArraySpliteratorIm<>(array, lo, index = mid, characteristics);
        }

        public void forEachRemainingIm(Consumer<? super T> action) {
            Object[] a;
            int i, hi;
            if (action == null)
                throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do {
                    action.accept((T) a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public long estimateSizeIm() {
            return (long) (fence - index);
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        public Comparator<? super T> getComparator() {
            if (hasCharacteristicsIm(SpliteratorImitate.SORTED_IM))
                return null;
            throw new IllegalStateException();
        }
    }

    static final class IntArraySpliteratorIm implements SpliteratorImitate.OfIntIm {
        private final int[] array;
        private int index;
        private final int fence;
        private final int characteristics;

        public IntArraySpliteratorIm(int[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        public IntArraySpliteratorIm(int[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM;
        }

        @Override
        public long estimateSizeIm() {
            return (long) (fence - index);
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        public Comparator<? super Integer> getComparatorIm() {
            if (hasCharacteristicsIm(SpliteratorImitate.SORTED_IM))
                return null;
            throw new IllegalStateException();
        }

        @Override
        public OfIntIm trySplitIm() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new IntArraySpliteratorIm(array, lo, index = mid, characteristics);
        }

        public void forEachRemainingIm(IntConsumer action) {
            int[] a;
            int i, hi;
            if (action == null)
                throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index - hi)) {
                do {
                    action.accept(a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvanceIm(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }
    }

    static final class LongArraySpliteratorIm implements SpliteratorImitate.OfLongIm {
        private final long[] array;
        private int index;
        private final int fence;
        private final int characteristics;

        public LongArraySpliteratorIm(long[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        public LongArraySpliteratorIm(long[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM;
        }

        @Override
        public long estimateSizeIm() {
            return (long) (fence - index);
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        public Comparator<? super Long> getComparatorIm() {
            if (hasCharacteristicsIm(SpliteratorImitate.SORTED_IM))
                return null;
            throw new IllegalStateException();
        }

        @Override
        public OfLongIm trySplitIm() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new LongArraySpliteratorIm(array, lo, index = mid, characteristics);
        }

        public void forEachRemainingIm(LongConsumer action) {
            long[] a;
            int i, hi;
            if (action == null)
                throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do {
                    action.accept(a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvanceIm(LongConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }
    }

    static final class DoubleArraySpliteratorIm implements SpliteratorImitate.OfDoubleIm {
        private final double[] array;
        private int index;
        private final int fence;
        private final int characteristics;

        public DoubleArraySpliteratorIm(double[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        public DoubleArraySpliteratorIm(double[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM;
        }

        @Override
        public long estimateSizeIm() {
            return (long) (fence - index);
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        public Comparator<? super Double> getComparatorIm() {
            if (hasCharacteristicsIm(SpliteratorImitate.SORTED_IM))
                return null;
            throw new IllegalStateException();
        }

        @Override
        public OfDoubleIm trySplitIm() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new DoubleArraySpliteratorIm(array, lo, index = mid, characteristics);
        }

        public void forEachRemainingIm(DoubleConsumer action) {
            double[] a;
            int i, hi;
            if (action == null)
                throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do {
                    action.accept(a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvanceIm(DoubleConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }
    }
}
