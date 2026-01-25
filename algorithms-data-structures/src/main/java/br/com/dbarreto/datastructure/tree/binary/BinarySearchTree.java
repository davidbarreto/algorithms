package br.com.dbarreto.datastructure.tree.binary;

public interface BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    void insert(T value);
    void delete(T value);
    T min();
    T max();
}
