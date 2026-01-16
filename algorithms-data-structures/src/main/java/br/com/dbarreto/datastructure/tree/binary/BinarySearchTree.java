package br.com.dbarreto.datastructure.tree.binary;

import br.com.dbarreto.algorithm.tree.BinarySearchTreeOperations;
import br.com.dbarreto.datastructure.node.tree.binary.BinarySearchTreeNode;

public interface BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    void insert(T value);
    void delete(T value);

    BinarySearchTreeNode<T> root();

    default T min() {
        return BinarySearchTreeOperations.min(this);
    }

    default T max() {
        return BinarySearchTreeOperations.max(this);
    }

    @Override
    default boolean contains(T value) {
        return BinarySearchTreeOperations.contains(this, value);
    }
}
