package imitate.java.lang;

import imitate.java.util.IteratorImitate;
import imitate.java.util.SpliteratorImitate;
import imitate.java.util.SpliteratorsImitate;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * user: xiangyu.wang
 * date: 2018/5/4 16:21
 */
public interface IterableImitate<T> {

    IteratorImitate<T> iteratorIm();

    default void forEachIm(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        //for (T t : this) {
        //    action.accept(t);
        //}
    }

    //todo
    default SpliteratorImitate<T> spliteratorIm() {
        return null;
        //return SpliteratorsImitate.spliteratorUnknownSizeIm(iteratorIm(), 0);
    }
}
