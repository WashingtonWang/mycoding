package map.imitate;

import java.awt.image.Kernel;
import java.io.Serializable;
import java.util.*;

/**
 * user: xiangyu.wang
 * date: 2018/3/16 17:26
 */
public abstract class AbstractMapIm<K, V> implements MapImitate<K, V> {

    protected AbstractMapIm() {
    }

    public int seizeIm() {
        return entrySetIm().size();
    }

    public boolean isEmptyIm() {
        return sizeIm() == 0;
    }

    public boolean containsValueIm(Object value) {
        Iterator<EntryIm<K, V>> i = entrySetIm().iterator();
        if (value == null) {
            while (i.hasNext()) {
                EntryIm<K, V> e = i.next();
                if (e.getValueIm() == null) {
                    return true;
                }
            }
        } else {
            while (i.hasNext()) {
                EntryIm<K, V> e = i.next();
                if (value.equals(e.getValueIm())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsKeyIm(Objects key) {
        Iterator<MapImitate.EntryIm<K, V>> i = entrySetIm().iterator();
        if (key == null) {
            while (i.hasNext()) {
                EntryIm<K, V> e = i.next();
                if (e.getKeyIm() == null) {
                    return false;
                }
            }
        } else {
            while (i.hasNext()) {
                EntryIm<K, V> e = i.next();
                if (key.equals(e.getKeyIm())) {
                    return true;
                }
            }
        }
        return false;
    }

    public V getIm(Object key){
        Iterator<EntryIm<K, V>> i = entrySetIm().iterator();
        if (key == null){
            while (i.hasNext()){
                EntryIm<K, V> e = i.next();
                if (e.getKeyIm() == null){
                    return e.getValueIm();
                }
            }
        }else{
            while (i.hasNext()){
                EntryIm<K, V> e = i.next();
                if (key.equals(e.getKeyIm())){
                    return e.getValueIm();
                }
            }
        }
        return null;
    }

    public V putIm(K key, V value){
        throw new UnsupportedOperationException();
    }

    public V removeIm(Object key){
        Iterator<EntryIm<K, V>> i = entrySetIm().iterator();
        EntryIm<K, V> correctEntry = null;
        if (key == null){
            while (correctEntry == null && i.hasNext()){
                EntryIm<K, V> e = i.next();
                if (e.getKeyIm() == null){
                    correctEntry = e;
                }
            }
        }else{
            while (correctEntry == null && i.hasNext()){
                EntryIm<K, V> e = i.next();
                if (key.equals(e.getKeyIm())){
                    correctEntry = e;
                }
            }
        }

        V oldValue = null;
        if (correctEntry != null){
            oldValue = correctEntry.getValueIm();
            i.remove();
        }
        return oldValue;
    }

    public void putAllIm(MapImitate<? extends K, ? extends V> m){
        for (MapImitate.EntryIm<? extends K, ? extends V> e : m.entrySetIm()){
            putIm(e.getKeyIm(), e.getValueIm());
        }
    }

    public void clearIm(){
        entrySetIm().clear();
    }

    transient Set<K> keySet;
    transient Collection<V> values;

    public Set<K> keySetIm(){
        Set<K> ks = keySet;
        if (ks == null){
            ks = new AbstractSet<K>() {
                @Override
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        private Iterator<EntryIm<K, V>> i = entrySetIm().iterator();
                        @Override
                        public boolean hasNext() {
                            return i.hasNext();
                        }
                        @Override
                        public K next() {
                            return i.next().getKeyIm();
                        }

                        public void remove(){
                            i.remove();
                        }
                    };
                }
                @Override
                public int size() {
                    return AbstractMapIm.this.seizeIm();
                }
                public boolean isEmpty(){
                    return AbstractMapIm.this.isEmptyIm();
                }
                public void clear(){
                    AbstractMapIm.this.clearIm();
                }
                public boolean contains(Object k){
                    return AbstractMapIm.this.containsKeyIm(k);
                }
            };
            keySet = ks;
        }
        return ks;
    }

    public Collection<V> valuesIm(){
        Collection<V> vals = values;
        if (vals == null){
            vals = new AbstractCollection<V>() {
                @Override
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        private Iterator<EntryIm<K, V>> i = entrySetIm().iterator();
                        @Override
                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        @Override
                        public V next() {
                            return i.next().getValueIm();
                        }

                        public void remove(){
                            i.remove();
                        }
                    };
                }

                @Override
                public int size() {
                    return AbstractMapIm.this.sizeIm();
                }
                public boolean isEmpty(){
                    return AbstractMapIm.this.isEmptyIm();
                }
                public void clear(){
                    AbstractMapIm.this.clearIm();
                }
                public boolean contains(Object k){
                    return AbstractMapIm.this.containsKeyIm(k);
                }
            };
            values = vals;
        }
        return vals;
    }

    public abstract Set<EntryIm<K, V>> entrySetIm();

    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof MapImitate)){
            return false;
        }
        MapImitate<?, ?> m = (MapImitate<?, ?>) o;
        if (m.sizeIm() != seizeIm()){
            return false;
        }
        try {
            Iterator<EntryIm<K, V>> i = entrySetIm().iterator();
            while (i.hasNext()){
                EntryIm<K, V> e = i.next();
                K key = e.getKeyIm();
                V value = e.getValueIm();
                if (value == null){
                    if (!(m.getIm(key) == null) && m.containsKeyIm(key)){
                        return false;
                    }
                }else {
                    if (!value.equals(m.getIm(key))){
                        return false;
                    }
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }

        return true;
    }

    public int hashCode(){
        int h = 0;
        for (EntryIm<K, V> kvEntryIm : entrySetIm()) {
            h += kvEntryIm.hashCode();
        }
        return h;
    }

    public String toString(){
        Iterator<EntryIm<K, V>> i = entrySetIm().iterator();
        if (!i.hasNext()){
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;){
            EntryIm<K, V> e = i.next();
            K key = e.getKeyIm();
            V value = e.getValueIm();
            sb.append(key == this ? "(this Map)" : key);
            sb.append("=");
            sb.append(value == this ? "(this Map)" : value);
            if (!i.hasNext()){
                return sb.append('}').toString();
            }
            sb.append(',').append(' ');
        }
    }

    protected Object clone() throws CloneNotSupportedException{
        AbstractMapIm<?, ?> result = (AbstractMapIm<?, ?>) super.clone();
        result.keySet = null;
        result.values = null;
        return result;
    }

    private static boolean eqIm(Object o1, Object o2){
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static class SimpleEntryIm<K, V> implements EntryIm<K, V>, Serializable{
        private static final long serialVersionUID = 1;

        private final K key;
        private V value;

        public SimpleEntryIm(K key, V value){
            this.key = key;
            this.value = value;
        }

        public SimpleEntryIm(EntryIm<? extends K, ? extends V> entryIm){
            this.key = entryIm.getKeyIm();
            this.value = entryIm.getValueIm();
        }

        @Override
        public K getKeyIm() {
            return null;
        }

        @Override
        public V getValueIm() {
            return null;
        }

        @Override
        public V setValueIm(V value) {
            return null;
        }
    }

}
