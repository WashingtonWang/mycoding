package imitate.java.util;

import java.util.Comparator;
import java.util.NoSuchElementException;
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

    public static SpliteratorImitate.OfIntIm spliteratorIm(PrimitiveIteratorImitate.OfIntIm iterator, long size,
                                                           int characteristics) {
        return new IntIteratorSpliteratorIm(Objects.requireNonNull(iterator), size, characteristics);
    }

    public static SpliteratorImitate.OfIntIm spliteratorUnknownSizeIm(PrimitiveIteratorImitate.OfIntIm iterator,
                                                                      int characteristics) {
        return new IntIteratorSpliteratorIm(Objects.requireNonNull(iterator), characteristics);
    }

    public static SpliteratorImitate.OfLongIm spliteratorIm(PrimitiveIteratorImitate.OfLongIm iterator, long size,
                                                            int characteristics) {
        return new LongIteratorSpliteratorIm(Objects.requireNonNull(iterator), size, characteristics);
    }

    public static SpliteratorImitate.OfLongIm spliteratorUnknownSizeIm(PrimitiveIteratorImitate.OfLongIm iterator,
                                                                       int characteristics) {
        return new LongIteratorSpliteratorIm(Objects.requireNonNull(iterator), characteristics);
    }

    public static SpliteratorImitate.OfDoubleIm spliterator(PrimitiveIteratorImitate.OfDoubleIm iterator, long size,
                                                            int characteristics) {
        return new DoubleIteratorSpliteratorIm(Objects.requireNonNull(iterator), size, characteristics);
    }

    public static SpliteratorImitate.OfDoubleIm spliteratorUnknownSize(PrimitiveIteratorImitate.OfDoubleIm iterator,
                                                                       int characteristics) {
        return new DoubleIteratorSpliteratorIm(Objects.requireNonNull(iterator), characteristics);
    }

    public static <T> IteratorImitate<T> iteratorIm(SpliteratorImitate<? extends T> spliterator) {
        Objects.requireNonNull(spliterator);
        class AdapterIm implements IteratorImitate<T>, Consumer<T> {
            boolean valueReady = false;
            T nextElement;

            @Override
            public boolean hasNextIm() {
                if (!valueReady)
                    spliterator.tryAdvanceIm(this);
                return valueReady;
            }

            @Override
            public T nextIm() {
                if (!valueReady && !hasNextIm())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }

            @Override
            public void accept(T t) {
                valueReady = true;
                nextElement = t;
            }
        }
        return new AdapterIm();
    }

    public static PrimitiveIteratorImitate.OfIntIm iteratorIm(SpliteratorImitate.OfIntIm spliterator) {
        Objects.requireNonNull(spliterator);
        class AdapterIm implements PrimitiveIteratorImitate.OfIntIm, IntConsumer {
            boolean valueReady = false;
            int nextElement;

            @Override
            public int nextIntIm() {
                if (!valueReady && !hasNextIm())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }

            @Override
            public boolean hasNextIm() {
                if (!valueReady)
                    spliterator.tryAdvanceIm(this);
                return valueReady;
            }

            @Override
            public void accept(int t) {
                valueReady = true;
                nextElement = t;
            }
        }
        return new AdapterIm();
    }

    public static PrimitiveIteratorImitate.OfLongIm iteratorIm(SpliteratorImitate.OfLongIm spliterator) {
        Objects.requireNonNull(spliterator);
        class AdapterIm implements PrimitiveIteratorImitate.OfLongIm, LongConsumer {
            boolean valueReady = false;
            Long nextElement;

            @Override
            public long nextLongIm() {
                if (!valueReady && !hasNextIm())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }

            @Override
            public boolean hasNextIm() {
                if (!valueReady)
                    spliterator.tryAdvanceIm(this);
                return valueReady;
            }

            @Override
            public void accept(long t) {
                valueReady = true;
                nextElement = t;
            }
        }
        return new AdapterIm();
    }

    public static PrimitiveIteratorImitate.OfDoubleIm iteratorIm(SpliteratorImitate.OfDoubleIm spliterator) {
        Objects.requireNonNull(spliterator);
        class AdapterIm implements PrimitiveIteratorImitate.OfDoubleIm, DoubleConsumer {
            boolean valueReady = false;
            double nextElement;

            @Override
            public double nextDoubleIm() {
                if (!valueReady && !hasNextIm())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }

            @Override
            public boolean hasNextIm() {
                if (!valueReady)
                    spliterator.tryAdvanceIm(this);
                return valueReady;
            }

            @Override
            public void accept(double t) {
                valueReady = true;
                nextElement = t;
            }
        }
        return new AdapterIm();
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

    public static abstract class AbstractSpliteratorIm<T> implements SpliteratorImitate<T> {
        static final int BATCH_UNIT = 1 << 10;
        static final int MAX_BATCH = 1 << 25;
        private final int characteristics;
        private long est;
        private int batch;

        protected AbstractSpliteratorIm(long est, int additionalCharacteristics) {
            this.est = est;
            this.characteristics = ((additionalCharacteristics & SpliteratorImitate.SIZED_IM) != 0) ?
                    additionalCharacteristics | SpliteratorImitate.SUBSIZED_IM :
                    additionalCharacteristics;
        }

        static final class HoldingConsumerIm<T> implements Consumer<T> {
            Object value;

            @Override
            public void accept(T t) {
                this.value = t;
            }
        }

        public SpliteratorImitate<T> trySplitIm() {
            HoldingConsumerIm<T> holder = new HoldingConsumerIm<>();
            long s = est;
            if (s > 1 && tryAdvanceIm(holder)) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Object[] a = new Object[n];
                int j = 0;
                do {
                    a[j] = holder.value;
                } while (++j < n && tryAdvanceIm(holder));
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new ArraySpliteratorIm<>(a, 0, j, characteristics);
            }
            return null;
        }

        public long estimateSizeIm() {
            return est;
        }

        public int characteristicsIm() {
            return characteristics;
        }

    }

    public static abstract class AbstractIntSpliteratorIm implements SpliteratorImitate.OfIntIm {
        static final int MAX_BATCH = AbstractSpliteratorIm.MAX_BATCH;
        static final int BATCH_UNIT = AbstractSpliteratorIm.BATCH_UNIT;
        private final int characteristics;
        private long est;
        private int batch;

        protected AbstractIntSpliteratorIm(long est, int additionalCharacteristics) {
            this.est = est;
            this.characteristics = ((additionalCharacteristics & SpliteratorImitate.SIZED_IM) != 0) ?
                    additionalCharacteristics | SpliteratorImitate.SUBSIZED_IM :
                    additionalCharacteristics;
        }

        static final class HoldingIntConsumer implements IntConsumer {
            int value;

            @Override
            public void accept(int value) {
                this.value = value;
            }
        }

        public SpliteratorImitate.OfIntIm trySplitIm() {
            HoldingIntConsumer holder = new HoldingIntConsumer();
            long s = est;
            if (s > 1 && tryAdvanceIm(holder)) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                int[] a = new int[n];
                int j = 0;
                do {
                    a[j] = holder.value;
                } while (++j < n && tryAdvanceIm(holder));
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new IntArraySpliteratorIm(a, 0, j, characteristicsIm());
            }
            return null;
        }

        public long estimateSizeIm() {
            return est;
        }

        public int characteristicsIm() {
            return characteristics;
        }
    }

    public static abstract class AbstractLongSpliteratorIm implements SpliteratorImitate.OfLongIm {
        static final int MAX_BATCH = AbstractSpliteratorIm.MAX_BATCH;
        static final int BATCH_UNIT = AbstractSpliteratorIm.BATCH_UNIT;
        private final int characteristics;
        private long est;
        private int batch;

        protected AbstractLongSpliteratorIm(long est, int additionalCharacteristics) {
            this.est = est;
            this.characteristics = ((additionalCharacteristics & SpliteratorImitate.SIZED_IM) != 0) ?
                    additionalCharacteristics | SpliteratorImitate.SUBSIZED_IM :
                    additionalCharacteristics;
        }

        static final class HoldingLongConsumer implements LongConsumer {
            long value;

            @Override
            public void accept(long value) {
                this.value = value;
            }
        }

        public SpliteratorImitate.OfLongIm trySplitIm() {
            HoldingLongConsumer holder = new HoldingLongConsumer();
            long s = est;
            if (s > 1 && tryAdvanceIm(holder)) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                long[] a = new long[n];
                int j = 0;
                do {
                    a[j] = holder.value;
                } while (++j < n && tryAdvanceIm(holder));
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new LongArraySpliteratorIm(a, 0, j, characteristicsIm());
            }
            return null;
        }

        public long estimateSizeIm() {
            return est;
        }

        public int characteristicsIm() {
            return characteristics;
        }
    }

    public static abstract class AbstractDoublegSpliteratorIm implements SpliteratorImitate.OfDoubleIm {
        static final int MAX_BATCH = AbstractSpliteratorIm.MAX_BATCH;
        static final int BATCH_UNIT = AbstractSpliteratorIm.BATCH_UNIT;
        private final int characteristics;
        private long est;
        private int batch;

        protected AbstractDoublegSpliteratorIm(long est, int additionalCharacteristics) {
            this.est = est;
            this.characteristics = ((additionalCharacteristics & SpliteratorImitate.SIZED_IM) != 0) ?
                    additionalCharacteristics | SpliteratorImitate.SUBSIZED_IM :
                    additionalCharacteristics;
        }

        static final class HoldingDoubleConsumer implements DoubleConsumer {
            double value;

            @Override
            public void accept(double value) {
                this.value = value;
            }
        }

        public SpliteratorImitate.OfDoubleIm trySplitIm() {
            HoldingDoubleConsumer holder = new HoldingDoubleConsumer();
            long s = est;
            if (s > 1 && tryAdvanceIm(holder)) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                double[] a = new double[n];
                int j = 0;
                do {
                    a[j] = holder.value;
                } while (++j < n && tryAdvanceIm(holder));
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new DoubleArraySpliteratorIm(a, 0, j, characteristicsIm());
            }
            return null;
        }

        public long estimateSizeIm() {
            return est;
        }

        public int characteristicsIm() {
            return characteristics;
        }
    }

    // Iterator-based Spliterators

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
            if (it == null) {
                it = collection.iteratorIm();
                est = (long) collection.sizeIm();
            }
            if (it.hasNextIm()) {
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
            if (it == null) {
                it = collection.iteratorIm();
                return est = (long) collection.sizeIm();
            }
            return est;
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        public Comparator<? super T> getComparatorIm() {
            if (hasCharacteristicsIm(SpliteratorImitate.SORTED_IM))
                return null;
            throw new IllegalStateException();
        }
    }

    static final class IntIteratorSpliteratorIm implements SpliteratorImitate.OfIntIm {
        static final int BATCH_UNIT = IteratorSpliteratorIm.BATCH_UNIT;
        static final int MAX_BATCH = IteratorSpliteratorIm.MAX_BATCH;
        private PrimitiveIteratorImitate.OfIntIm it;
        private final int characteristics;
        private long est;
        private int batch;

        public IntIteratorSpliteratorIm(PrimitiveIteratorImitate.OfIntIm iterator, long size, int characteristics) {
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & SpliteratorImitate.CONCURRENT_IM) == 0 ?
                    characteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM :
                    characteristics;
        }

        public IntIteratorSpliteratorIm(PrimitiveIteratorImitate.OfIntIm iterator, int characteristics) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM);
        }


        @Override
        public long estimateSizeIm() {
            return est;
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        @Override
        public OfIntIm trySplitIm() {
            PrimitiveIteratorImitate.OfIntIm i = it;
            long s = est;
            if (s > 1 && i.hasNextIm()) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                int[] a = new int[n];
                int j = 0;
                do {
                    a[j] = i.nextIntIm();
                } while (++j < n && i.hasNextIm());
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new IntArraySpliteratorIm(a, 0, j, characteristics);
            }
            return null;
        }

        public void forEachRemainingIm(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            it.forEachRemainingIm(action);
        }

        @Override
        public boolean tryAdvanceIm(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (it.hasNextIm()) {
                action.accept(it.nextIntIm());
                return true;
            }
            return false;
        }
    }

    static final class LongIteratorSpliteratorIm implements SpliteratorImitate.OfLongIm {
        static final int BATCH_UNIT = IteratorSpliteratorIm.BATCH_UNIT;
        static final int MAX_BATCH = IteratorSpliteratorIm.MAX_BATCH;
        private PrimitiveIteratorImitate.OfLongIm it;
        private final int characteristics;
        private long est;
        private int batch;

        public LongIteratorSpliteratorIm(PrimitiveIteratorImitate.OfLongIm iterator, long size, int characteristics) {
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & SpliteratorImitate.CONCURRENT_IM) == 0 ?
                    characteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM :
                    characteristics;
        }

        public LongIteratorSpliteratorIm(PrimitiveIteratorImitate.OfLongIm iterator, int characteristics) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(SpliteratorImitate.SIZED_IM) | SpliteratorImitate.SUBSIZED_IM;
        }

        @Override
        public long estimateSizeIm() {
            return est;
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        @Override
        public OfLongIm trySplitIm() {
            PrimitiveIteratorImitate.OfLongIm i = it;
            long s = est;
            if (s > 1 && i.hasNextIm()) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                long[] a = new long[n];
                int j = 0;
                do {
                    a[j] = i.nextLongIm();
                } while (++j < n && i.hasNextIm());
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new LongArraySpliteratorIm(a, 0, j, characteristics);
            }
            return null;
        }

        public void forEachRemainingIm(LongConsumer action) {
            if (action == null)
                throw new NullPointerException();
            it.forEachRemainingIm(action);
        }

        @Override
        public boolean tryAdvanceIm(LongConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (it.hasNextIm()) {
                action.accept(it.nextLongIm());
                return true;
            }
            return false;
        }
    }

    static final class DoubleIteratorSpliteratorIm implements SpliteratorImitate.OfDoubleIm {
        static final int BATCH_UNIT = IteratorSpliteratorIm.BATCH_UNIT;
        static final int MAX_BATCH = IteratorSpliteratorIm.MAX_BATCH;
        private PrimitiveIteratorImitate.OfDoubleIm it;
        private final int characteristics;
        private long est;
        private int batch;

        public DoubleIteratorSpliteratorIm(PrimitiveIteratorImitate.OfDoubleIm iterator, long size, int characteristics) {
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & SpliteratorImitate.CONCURRENT_IM) == 0 ?
                    characteristics | SpliteratorImitate.SIZED_IM | SpliteratorImitate.SUBSIZED_IM :
                    characteristics;
        }

        public DoubleIteratorSpliteratorIm(PrimitiveIteratorImitate.OfDoubleIm iterator, int characteristics) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(SpliteratorImitate.SIZED_IM) | SpliteratorImitate.SUBSIZED_IM;
        }

        @Override
        public long estimateSizeIm() {
            return est;
        }

        @Override
        public int characteristicsIm() {
            return characteristics;
        }

        @Override
        public OfDoubleIm trySplitIm() {
            PrimitiveIteratorImitate.OfDoubleIm i = it;
            long s = est;
            if (s > 1 && i.hasNextIm()) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                double[] a = new double[n];
                int j = 0;
                do {
                    a[j] = i.nextDoubleIm();
                } while (++j < n && i.hasNextIm());
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new DoubleArraySpliteratorIm(a, 0, j, characteristics);
            }
            return null;
        }

        public void forEachRemainingIm(DoubleConsumer action) {
            if (action == null)
                throw new NullPointerException();
            it.forEachRemainingIm(action);
        }

        @Override
        public boolean tryAdvanceIm(DoubleConsumer action) {
            if (action == null) throw new NullPointerException();
            if (it.hasNextIm()) {
                action.accept(it.nextDoubleIm());
                return true;
            }
            return false;
        }

        @Override
        public Comparator<? super Double> getComparatorIm() {
            if (hasCharacteristicsIm(SpliteratorImitate.SORTED_IM))
                return null;
            throw new IllegalStateException();
        }
    }
}
