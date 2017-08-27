package threadeg.eg4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 此类不是线程安全的类  因为在错误的锁上进行了同步    P. 61 《java并发编程实战》
 * @param <E>
 */
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x){
        boolean absent = !list.contains(x);
        if (absent)
            list.add(x);
        return absent;
    }
}

/**
 * 此类是线程安全的类 在正确的锁上进行了同步   P. 61 《java并发编程实战》
 * @param <E>
 */
class ListHelperSafe<E>{
    public List<E> list = Collections.synchronizedList(new ArrayList<>());
    public boolean putIfAbsent(E x){
        synchronized (list){
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
}


