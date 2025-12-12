package br.com.dbarreto.datastructure.tree;

public interface Tree<T> {
    int height();
    int size();
    boolean contains(T value);
    default boolean isEmpty() {
        return size() == 0;
    }
}
