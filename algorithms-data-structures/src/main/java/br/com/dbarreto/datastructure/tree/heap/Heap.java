package br.com.dbarreto.datastructure.tree.heap;

public interface Heap<E> {

    void insert(E value);

    E peek();
    E extract();

    int size();
    void clear();

    default boolean isEmpty() {
        return size() == 0;
    }
}
