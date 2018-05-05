package imitate.java.lang;

import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * user: xiangyu.wang
 * date: 2018/5/4 16:21
 */
public interface IterableImitate<T> {
    IterableImitate<T> iteratorIm();

    default void ForEachIm(Consumer<? super T> action){
        Objects.requireNonNull(action);
        for (T t : this){
            action.accept(t);
        }
    }

    default Spliterator<T> spliteratorIm(){
        Spliterators.spliteratorUnknownSize(iteratorIm(), 0);
    }
}
