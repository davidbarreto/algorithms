package br.com.dbarreto.datastructure.list.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelativeArrayListTest {

    @Test
    void shouldGetWithPositiveIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.get(1));
    }

    @Test
    void shouldGetWithNegativeIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("C", list.get(-1));
        assertEquals("B", list.get(-2));
        assertEquals("A", list.get(-3));
    }

    @Test
    void shouldSetWithPositiveIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.set(1, "D");
        assertEquals("D", list.get(1));
    }

    @Test
    void shouldSetWithNegativeIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.set(-1, "D");
        assertEquals("D", list.get(2));
    }

    @Test
    void shouldAddWithPositiveIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("C");
        list.add(1, "B");
        assertEquals("B", list.get(1));
    }

    @Test
    void shouldAddWithNegativeIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("C");
        list.add(-1, "B");
        assertEquals("B", list.get(1));
    }

    @Test
    void shouldRemoveWithPositiveIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove(1);
        assertEquals("C", list.get(1));
    }

    @Test
    void shouldRemoveWithNegativeIndex() {
        RelativeArrayList<String> list = new RelativeArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove(-1);
        assertEquals("B", list.get(1));
    }
}
