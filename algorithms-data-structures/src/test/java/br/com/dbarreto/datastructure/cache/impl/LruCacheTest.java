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
    void shouldPutAndGetElements() {
        cache.put(1, "One");
        cache.put(2, "Two");
        assertEquals("One", cache.get(1));
        assertEquals("Two", cache.get(2));
        assertEquals(2, cache.size());
    }

    @Test
    void shouldReturnNullForNonExistentKey() {
        assertNull(cache.get(1));
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

    @Test
    void shouldUpdateValueForExistingKey() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(1, "New One"); // Update value for key 1

        assertEquals("New One", cache.get(1));
        assertEquals("Two", cache.get(2));
        assertEquals(2, cache.size());
    }

    @Test
    void shouldRemoveElement() {
        cache.put(1, "One");
        cache.put(2, "Two");
        assertEquals("One", cache.remove(1));
        assertNull(cache.get(1));
        assertFalse(cache.containsKey(1));
        assertEquals(1, cache.size());
    }

    @Test
    void shouldReturnNullWhenRemovingNonExistentKey() {
        assertNull(cache.remove(1));
    }

    @Test
    void shouldCheckContainsKey() {
        cache.put(1, "One");
        assertTrue(cache.containsKey(1));
        assertFalse(cache.containsKey(2));
    }

    @Test
    void shouldReturnCorrectSize() {
        assertEquals(0, cache.size());
        cache.put(1, "One");
        assertEquals(1, cache.size());
        cache.put(2, "Two");
        assertEquals(2, cache.size());
        cache.remove(1);
        assertEquals(1, cache.size());
    }

    @Test
    void shouldReturnCorrectCapacity() {
        assertEquals(3, cache.capacity());
    }

    @Test
    void shouldClearCache() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.clear();
        assertEquals(0, cache.size());
        assertFalse(cache.containsKey(1));
        assertFalse(cache.containsKey(2));
    }

    @Test
    void shouldReturnAsUnmodifiableMap() {
        cache.put(1, "One");
        cache.put(2, "Two");
        var map = cache.asMap();
        assertEquals(2, map.size());
        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("Two"));

        assertThrows(UnsupportedOperationException.class, () -> map.put(3, "Three"));
    }
}
