package br.com.dbarreto.datastructure.cache.impl;

import br.com.dbarreto.datastructure.cache.Cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class LfuCache<K, V> implements Cache<K, V> {

    private final Map<K, CacheValue<K, V>> cache;
    private final Map<Integer, LinkedHashSet<K>> freq;
    private final int capacity;
    private int minFreq;

    public LfuCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freq = new HashMap<>();
        this.minFreq = 1;
    }

    @Override
    public V get(K key) {
        var cacheVal = cache.get(key);
        if (cacheVal == null) {
            return null;
        }
        increaseFrequency(cacheVal);
        return cacheVal.value;
    }

    @Override
    public V put(K key, V value) {

        if (cache.containsKey(key)) {
            var cacheVal = cache.get(key);
            cacheVal.value = value;
            increaseFrequency(cacheVal);
            return value;
        }

        if (cache.size() == capacity) {
            evict();
        }

        var newVal = new CacheValue<>(key, value);
        cache.put(key, newVal);
        freq.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;

        return value;
    }

    private void increaseFrequency(CacheValue<K, V> cacheVal) {
        int currentFreq = cacheVal.freq;

        freq.get(currentFreq).remove(cacheVal.key);
        if (freq.get(currentFreq).isEmpty()) {
            freq.remove(currentFreq);
            if (currentFreq == minFreq) {
                minFreq++;
            }
        }

        cacheVal.freq++;
        freq.computeIfAbsent(cacheVal.freq, k -> new LinkedHashSet<>()).add(cacheVal.key);
    }

    private void evict() {
        freq.get(minFreq).stream().findFirst().ifPresent(this::remove);
    }

    @Override
    public V remove(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        var cacheVal = cache.get(key);
        freq.get(cacheVal.freq).remove(key);

        if (freq.get(cacheVal.freq).isEmpty()) {
            freq.remove(cacheVal.freq);
        }

        return cache.remove(key).value;
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public Map<K, V> asMap() {
        return Collections.unmodifiableMap(cache.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().value))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private static class CacheValue<K, V> {
        K key;
        V value;
        int freq;

        CacheValue(K key, V value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }
}
