package com.mycoding.javabase.imitate.java.util;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * user: xiangyu.wang
 * date: 2018/5/4 16:36
 */
public interface SpliteratorImitate<T> {

    boolean tryAdvanceIm(Consumer<? super T> action);

    default void forEachRemainingIm(Consumer<? super T> action) {
        do {
        } while (tryAdvanceIm(action));
    }

    SpliteratorImitate<T> trySplitIm();

    long estimateSizeIm();

    default long getExactSizeIfKnownIm() {
        return (characteristicsIm() & SIZED_IM) == 0 ? -1L : estimateSizeIm();
    }

    int characteristicsIm();

    default boolean hasCharacteristicsIm(int characteristicsIm) {
        return (characteristicsIm() & characteristicsIm) == characteristicsIm;
    }

    default Comparator<? super T> getComparatorIm() {
        throw new IllegalStateException();
    }

    public static final int ORDERED_IM = 0x00000010;
    public static final int DISTINCT_IM = 0x00000001;
    public static final int SORTED_IM = 0x00000004;
    public static final int SIZED_IM = 0x00000040;
    public static final int NONNULL_IM = 0x00000100;
    public static final int IMMUTABLE_IM = 0x00000400;
    public static final int CONCURRENT_IM = 0x00001000;
    public static final int SUBSIZED_IM = 0x00001000;

    public interface OfPrimitiveIm<T, T_CONS, T_SPLITR extends SpliteratorImitate.OfPrimitiveIm<T, T_CONS, T_SPLITR>>
            extends SpliteratorImitate<T> {

        T_SPLITR trySplitIm();

        boolean tryAdvanceIm(T_CONS action);

        default void forEachRemainingIm(T_CONS action) {
            do {
            } while (tryAdvanceIm(action));
        }
    }

    public interface OfIntIm extends OfPrimitiveIm<Integer, IntConsumer, OfIntIm> {

        OfIntIm trySplitIm();

        @Override
        boolean tryAdvanceIm(IntConsumer action);

        default void forEachRemainingIm(IntConsumer action) {
            do {
            } while (tryAdvanceIm(action));
        }

        default boolean tryAdvanceIm(Consumer<? super Integer> action) {
            if (action instanceof IntConsumer) {
                return tryAdvanceIm((IntConsumer) action);
            } else {
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(),
                            "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
                return tryAdvanceIm((IntConsumer) action::accept);
            }
        }

        @Override
        default void forEachRemainingIm(Consumer<? super Integer> action){
            if (action instanceof IntConsumer){
                forEachRemainingIm((IntConsumer) action);
            } else {
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(),
                            "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
                forEachRemainingIm((IntConsumer) action::accept);
            }
        }
    }

    public interface OfLongIm extends OfPrimitiveIm<Long, LongConsumer, OfLongIm> {

        OfLongIm trySplitIm();

        boolean tryAdvanceIm(LongConsumer action);

        default void forEachRemainingIm(LongConsumer action) {
            do {
            } while (tryAdvanceIm(action));
        }

        default boolean tryAdvanceIm(Consumer<? super Long> action) {
            if (action instanceof LongConsumer) {
                return tryAdvanceIm((LongConsumer) action);
            } else {
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(),
                            "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
                return tryAdvanceIm((LongConsumer) action::accept);
            }
        }

        default void forEachRemainingIm(Consumer<? super Long> action) {
            if (action instanceof LongConsumer) {
                forEachRemainingIm((LongConsumer) action);
            } else {
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(),
                            "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
                forEachRemainingIm((LongConsumer) action::accept);
            }
        }
    }

    public interface OfDoubleIm extends OfPrimitiveIm<Double, DoubleConsumer, OfDoubleIm> {

        OfDoubleIm trySplitIm();

        boolean tryAdvanceIm(DoubleConsumer action);

        default void forEachRemainingIm(DoubleConsumer action) {
            do {
            } while (tryAdvanceIm(action));
        }

        default boolean tryAdvanceIm(Consumer<? super Double> action) {
            if (action instanceof DoubleConsumer) {
                return tryAdvanceIm((DoubleConsumer) action);
            } else {
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(),
                            "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
                return tryAdvanceIm((DoubleConsumer) action::accept);
            }
        }

        default void forEachRemainingIm(Consumer<? super Double> action) {
            if (action instanceof DoubleConsumer) {
                forEachRemainingIm((DoubleConsumer) action);
            } else {
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(),
                            "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
                forEachRemainingIm((DoubleConsumer) action::accept);
            }
        }
    }
}
