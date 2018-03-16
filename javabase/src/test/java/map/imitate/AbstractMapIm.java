package map.imitate;

import java.util.Objects;
import java.util.Set;

/**
 * user: xiangyu.wang
 * date: 2018/3/16 17:26
 */
public abstract class AbstractMapIm<K, V> implements MapImitate<K, V> {

    protected AbstractMapIm(){ }

    public int seizeIm(){
        return entryImSet().size();
    }

    public boolean isEmptyIm(){
        return sizeIm() == 0;
    }

    public boolean containsValueIm(Object value){
        //Iterable<EntryIm<K,V>> i = entryImSet().iterator();
        //i
    }

    public abstract Set<EntryIm<K, V>> entryImSet();

}
