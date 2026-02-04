package br.com.dbarreto.datastructure.cache;

import br.com.dbarreto.datastructure.cache.impl.LfuCache;
import br.com.dbarreto.datastructure.cache.impl.LruCache;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldPutAndGetElements(Cache<Integer, String> cache) {
        cache.put(1, "One");
        cache.put(2, "Two");
        assertEquals("One", cache.get(1));
        assertEquals("Two", cache.get(2));
        assertEquals(2, cache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldReturnNullForNonExistentKey(Cache<Integer, String> cache) {
        assertNull(cache.get(1));
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldUpdateValueForExistingKey(Cache<Integer, String> cache) {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(1, "New One"); // Update value for key 1

        assertEquals("New One", cache.get(1));
        assertEquals("Two", cache.get(2));
        assertEquals(2, cache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldRemoveElement(Cache<Integer, String> cache) {
        cache.put(1, "One");
        cache.put(2, "Two");
        assertEquals("One", cache.remove(1));
        assertNull(cache.get(1));
        assertFalse(cache.containsKey(1));
        assertEquals(1, cache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldReturnNullWhenRemovingNonExistentKey(Cache<Integer, String> cache) {
        assertNull(cache.remove(1));
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldCheckContainsKey(Cache<Integer, String> cache) {
        cache.put(1, "One");
        assertTrue(cache.containsKey(1));
        assertFalse(cache.containsKey(2));
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldReturnCorrectSize(Cache<Integer, String> cache) {
        assertEquals(0, cache.size());
        cache.put(1, "One");
        assertEquals(1, cache.size());
        cache.put(2, "Two");
        assertEquals(2, cache.size());
        cache.remove(1);
        assertEquals(1, cache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldReturnCorrectCapacity(Cache<Integer, String> cache) {
        assertEquals(3, cache.capacity());
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldClearCache(Cache<Integer, String> cache) {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.clear();
        assertEquals(0, cache.size());
        assertFalse(cache.containsKey(1));
        assertFalse(cache.containsKey(2));
    }

    @ParameterizedTest
    @MethodSource("cacheImplementations")
    void shouldReturnAsUnmodifiableMap(Cache<Integer, String> cache) {
        cache.put(1, "One");
        cache.put(2, "Two");
        var map = cache.asMap();
        assertEquals(2, map.size());
        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("Two"));

        assertThrows(UnsupportedOperationException.class, () -> map.put(3, "Three"));
    }

    static Stream<Arguments> cacheImplementations() {
        return Stream.of(
                Arguments.of(new LruCache<>(3)),
                Arguments.of(new LfuCache<>(3))
        );
    }
}
