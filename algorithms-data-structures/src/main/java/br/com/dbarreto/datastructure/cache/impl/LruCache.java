package br.com.dbarreto.datastructure.cache.impl;

import br.com.dbarreto.datastructure.cache.Cache;
import br.com.dbarreto.datastructure.list.impl.DoublyLinkedList;
import br.com.dbarreto.datastructure.node.list.DoublyLinkedNode;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LruCache<K, V> implements Cache<K, V> {

    private final DoublyLinkedList<Pair<K, V>> queue;
    private final Map<K, DoublyLinkedNode<Pair<K, V>>> cache;
    private int capacity;

    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.queue = new DoublyLinkedList<>();
        this.cache = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if (!this.cache.containsKey(key)) {
            return null;
        }
        updateUsage(key);
        return this.cache.get(key).value().second();
    }

    @Override
    public V put(K key, V value) {
        if (this.cache.containsKey(key)) {
            var node = this.cache.get(key);
            var pair = node.value();

            node.setValue(new SimplePair<>(pair.first(), value));
            updateUsage(key);

            return pair.second();
        }
        
        if (this.cache.size() >= this.capacity) {
           evict();
        }
        Pair<K, V> pair = new SimplePair<>(key, value);
        var node = this.queue.addToLast(pair);
        this.cache.put(key, node);

        return null;
    }

    @Override
    public V remove(K key) {
        if (!this.cache.containsKey(key)) {
            return null;
        }
        DoublyLinkedNode<Pair<K, V>> node = this.cache.remove(key);
        this.queue.remove(node);
        return node.value().second();
    }

    @Override
    public boolean containsKey(K key) {
        return this.cache.containsKey(key);
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public void clear() {
        this.cache.clear();
        this.queue.clear();
    }

    @Override
    public Map<K, V> asMap() {
        return Collections.unmodifiableMap(this.cache.entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().value().second())));
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        while (this.cache.size() > this.capacity) {
            evict();
        }
    }

    private void evict() {
        K key = this.queue.removeFirst().first();
        this.cache.remove(key);
    }

    private void updateUsage(K key) {
        DoublyLinkedNode<Pair<K, V>> node = this.cache.get(key);
        this.queue.moveToTail(node);
    }
}
