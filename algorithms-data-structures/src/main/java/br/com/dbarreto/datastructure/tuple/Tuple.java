package br.com.dbarreto.datastructure.tuple;

import br.com.dbarreto.datastructure.tuple.impl.RelativeTuple;

public interface Tuple<T> extends Iterable<T> {

    T get(int index);
    RelativeTuple<T> slice(String expression);
    RelativeTuple<T> sliceFrom(int from);
    RelativeTuple<T> sliceTo(int to);
    RelativeTuple<T> slice(int from, int to);
    RelativeTuple<T> reverse();
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
}
