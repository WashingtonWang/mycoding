package imitate.java.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * user: xiangyu.wang
 * date: 2018/5/18 15:43
 */
public interface PrimitiveIteratorImitate<T, T_CONS> extends IteratorImitate<T> {

    void forEachRemainingIm(T_CONS action);

    public static interface OfIntIm extends PrimitiveIteratorImitate<Integer, IntConsumer> {

        int nextIntIm();

        default void forEachRemainingIm(IntConsumer action) {
            Objects.requireNonNull(action);
            while (hasNextIm())
                action.accept(nextIntIm());
        }

        default Integer nextIm() {
            if (TripwireImitate.ENABLED)
                TripwireImitate.trip(getClass(), "{0} calling PrimitiveIterator.OfInt.nextInt()");
            return nextIntIm();
        }

        default void forEachRemainingIm(Consumer<? super Integer> action) {
            if (action instanceof IntConsumer) {
                forEachRemainingIm((IntConsumer) action);
            } else {
                Objects.requireNonNull(action);
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(), "{0} calling PrimitiveIterator.OfInt.forEachRemainingInt(action::accept)");
                forEachRemainingIm((IntConsumer) action::accept);
            }
        }
    }

    public static interface OfLongIm extends PrimitiveIteratorImitate<Long, LongConsumer>{

        long nextLongIm();

        default void forEachRemainingIm(LongConsumer action){
            Objects.requireNonNull(action);
            while (hasNextIm())
                action.accept(nextLongIm());
        }

        default Long nextIm(){
            if (TripwireImitate.ENABLED)
                TripwireImitate.trip(getClass(), "{0} calling PrimitiveIterator.OfLong.nextLong()");
            return nextLongIm();
        }

        default void forEachRemainingIm(Consumer<? super Long> action){
            if (action instanceof LongConsumer){
                forEachRemainingIm((LongConsumer) action);
            }else{
                Objects.requireNonNull(action);
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(), "{0} calling PrimitiveIterator.OfLong.forEachRemainingLong(action::accept)");
                forEachRemainingIm((LongConsumer) action::accept);
            }
        }
    }

    public static interface OfDoubleIm extends PrimitiveIteratorImitate<Double, DoubleConsumer>{

        double nextDoubleIm();

        default void forEachRemainingIm(DoubleConsumer action){
            Objects.requireNonNull(action);
            if (hasNextIm())
                action.accept(nextDoubleIm());
        }

        default Double nextIm(){
            if (TripwireImitate.ENABLED)
                TripwireImitate.trip(getClass(), "{0} calling PrimitiveIterator.OfDouble.nextLong()");
            return nextDoubleIm();
        }

        default void forEachRemainingIm(Consumer<? super Double> action){
            if (action instanceof DoubleConsumer){
                forEachRemainingIm((DoubleConsumer)action);
            } else {
                Objects.requireNonNull(action);
                if (TripwireImitate.ENABLED)
                    TripwireImitate.trip(getClass(),
                            "{0} calling PrimitiveIterator.OfDouble.forEachRemainingDouble(action::accept)");
                forEachRemainingIm((DoubleConsumer) action::accept);
            }
        }
    }
}
