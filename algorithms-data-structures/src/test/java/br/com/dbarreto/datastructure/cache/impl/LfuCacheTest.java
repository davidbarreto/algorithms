package br.com.dbarreto.datastructure.cache.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LfuCacheTest {

    private LfuCache<Integer, String> cache;

    @BeforeEach
    void setUp() {
        cache = new LfuCache<>(3); // Capacity of 3 for testing
    }

    @Test
    void shouldEvictLeastFrequentlyUsedElement() {
        cache.put(1, "One");
        cache.get(1);
        cache.get(1);
        cache.get(1);

        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.get(3);

        cache.put(4, "Four"); // This should evict "Two"

        assertNull(cache.get(2));
        assertEquals("One", cache.get(1));
        assertEquals("Three", cache.get(3));
        assertEquals("Four", cache.get(4));
        assertEquals(3, cache.size());
    }
}