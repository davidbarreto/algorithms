package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.algorithm.tree.BinarySearchTreeOperations;
import br.com.dbarreto.datastructure.node.BinarySearchTreeNode;

public interface BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    void insert(T value);
    void delete(T value);

    BinarySearchTreeNode<T> root();

    default T min() {
        return BinarySearchTreeOperations.min(root());
    }

    default T max() {
        return BinarySearchTreeOperations.max(root());
    }
}
