### putIfAbsent()
    该方法跟Lambda表达式没关系，但是很有用。方法签名为V putIfAbsent(K key, V value)，
    作用是只有在不存在key值的映射或映射值为null时，才将value指定的值放入到Map中，否则不对Map做更改．
    该方法将条件判断和赋值合二为一，使用起来更加方便．
    
### remove()
    我们都知道Map中有一个remove(Object key)方法，来根据指定key值删除Map中的映射关系；
    Java8新增了remove(Object key, Object value)方法，只有在当前Map中key正好映射到value时才删除该映射，否则什么也不做．
    
### replace()
    在Java7及以前，要想替换Map中的映射关系可通过put(K key, V value)方法实现，该方法总是会用新值替换原来的值．
    为了更精确的控制替换行为，Java8在Map中加入了两个replace()方法，分别如下：
    
    * replace(K key, V value)，只有在当前Map中key的映射存在时才用value去替换原来的值，否则什么也不做．
    * replace(K key, V oldValue, V newValue)，只有在当前Map中key的映射存在且等于oldValue时才用newValue去替换原来的值，否则什么也不做．

### merge()
    该方法签名为merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)，作用是：
    1、如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上；
    2、否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射．
    参数中BiFunction函数接口前面已经介绍过，里面有一个待实现方法R apply(T t, U u)．
    merge()方法虽然语义有些复杂，但该方法的用方式很明确，一个比较常见的场景是将新的错误信息拼接到原来的信息上，比如：
    map.merge(key, newMsg, (v1, v2) -> v1+v2);    
    
### compute()
    该方法签名为compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)，
    作用是把remappingFunction的计算结果关联到key上，如果计算结果为null，则在Map中删除key的映射．
    要实现上述merge()方法中错误信息拼接的例子，使用compute()代码如下：
    map.compute(key, (k,v) -> v==null ? newMsg : v.concat(newMsg));
    
### computeIfAbsent()
    该方法签名为V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)，作用是：
    只有在当前Map中不存在key值的映射或映射值为null时，才调用mappingFunction，并在mappingFunction执行结果非null时，将结果跟key关联．
    Function是一个函数接口，里面有一个待实现方法R apply(T t)．
    computeIfAbsent()常用来对Map的某个key值建立初始化映射．比如我们要实现一个多值映射，Map的定义可能是Map<K,Set<V>>，要向Map中放入新值
    
### computeIfPresent()
    该方法签名为V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)，作用跟computeIfAbsent()相反，即，
    只有在当前Map中存在key值的映射且非null时，才调用remappingFunction，
    如果remappingFunction执行结果为null，则删除key的映射，否则使用该结果替换key原来的映射．