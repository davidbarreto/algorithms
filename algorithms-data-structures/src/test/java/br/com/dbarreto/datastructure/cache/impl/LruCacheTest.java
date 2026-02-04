package br.com.dbarreto.datastructure.cache.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

    private LruCache<Integer, String> cache;

    @BeforeEach
    void setUp() {
        cache = new LruCache<>(3); // Capacity of 3 for testing
    }

    @Test
    void shouldEvictLeastRecentlyUsedElement() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.put(4, "Four"); // This should evict "One"

        assertNull(cache.get(1));
        assertEquals("Two", cache.get(2));
        assertEquals("Three", cache.get(3));
        assertEquals("Four", cache.get(4));
        assertEquals(3, cache.size());
    }

    @Test
    void shouldUpdateUsageOnGet() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        cache.get(1); // "One" is now most recently used
        cache.put(4, "Four"); // This should evict "Two"

        assertNull(cache.get(2));
        assertEquals("One", cache.get(1));
        assertEquals("Three", cache.get(3));
        assertEquals("Four", cache.get(4));
    }
}
