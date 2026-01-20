package br.com.dbarreto.datastructure.tuple;

public interface Tuple<T> extends Iterable<T> {

    T get(int index);
    Tuple<T> slice(String expression);
    Tuple<T> sliceFrom(int from);
    Tuple<T> sliceTo(int to);
    Tuple<T> slice(int from, int to);
    Tuple<T> reverse();
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
}
