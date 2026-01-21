package br.com.dbarreto.datastructure.cache;

import java.util.Map;

public interface Cache<K, V> {

    V get(K key);
    V put(K key, V value);
    V remove(K key);
    boolean containsKey(K key);
    int size();
    int capacity();
    void clear();
    default boolean isEmpty() {
        return size() == 0;
    }
    Map<K, V> asMap();
}
