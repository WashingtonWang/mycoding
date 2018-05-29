package imitate.java.util;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * user: xiangyu.wang
 * date: 2018/5/15 17:39
 */
public interface IteratorImitate<E> {

    boolean hasNextIm();

    E nextIm();

    default void removeIm() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemainingIm(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNextIm())
            action.accept(nextIm());
    }
}
