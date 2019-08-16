package com.jdk.collection.map;

/**
 * @author purensong
 * @date 2019/4/10 14:53
 */
public interface MyMap<K, V> {
    public V put(K k, V v);

    public V get(K k);

    interface Entry<K, V> {
        public K getKey();

        public V getVal();
    }
}
