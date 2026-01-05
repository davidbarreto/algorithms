package br.com.dbarreto.datastructure.tuple.impl;

import br.com.dbarreto.datastructure.tuple.Tuple;

import java.util.Arrays;
import java.util.Iterator;

public class RelativeTuple<T> implements Tuple<T> {
    private final T[] values;

    @SafeVarargs
    public RelativeTuple(T... values) {
        this.values = values.clone();
    }

    @Override
    public T get(int index) {
        return this.values[normalizeIndex(index, false)];
    }

    @Override
    public RelativeTuple<T> slice(String expression) {
        String[] parts = expression.split(":", -1);
        int from = parseIndex(parts[0], 0);
        int to = parseIndex(parts[1], this.values.length);

        return slice(from, to);
    }

    @Override
    public RelativeTuple<T> sliceFrom(int from) {
        return slice(from, this.values.length);
    }

    @Override
    public RelativeTuple<T> sliceTo(int to) {
        return slice(0, to);
    }

    @Override
    public RelativeTuple<T> slice(int from, int to) {
        from = normalizeIndex(from, false);
        to = normalizeIndex(to, true);

        if (from >= to) {
            return new RelativeTuple<>();
        }

        return new RelativeTuple<>(Arrays.copyOfRange(this.values, from, to));
    }

    @Override
    public RelativeTuple<T> reverse() {
        T[] newValues = this.values.clone(); // Create an array with same type and size as original
        int len = this.values.length;
        for (int i = 0; i < len; i++) {
            newValues[i] = this.values[len - 1 - i];
        }
        return new RelativeTuple<>(newValues);
    }

    @Override
    public int size() {
        return this.values.length;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(this.values).iterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(this.values);
    }

    private int normalizeIndex(int index, boolean allowEnd) {
        if (index < 0) {
            index = this.values.length + index;
        }

        int maxAllowed = allowEnd ? this.values.length : this.values.length - 1;

        if (index < 0 || index > maxAllowed) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: [0, " + values.length + ") or [-" + values.length + ", " + "-1]");
        }

        return index;
    }

    private int parseIndex(String indexString, int defaultIndex) {
        if (indexString.isEmpty()) {
            return defaultIndex;
        }
        return Integer.parseInt(indexString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelativeTuple<?> that)) return false;
        return Arrays.equals(this.values, that.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.values);
    }
}


