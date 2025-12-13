package br.com.dbarreto.datastructure.node;

import br.com.dbarreto.algorithm.tree.BinaryTreeProperties;

public interface BinaryTreeNode<T> extends TreeNode<T> {
    BinaryTreeNode<T> left();
    BinaryTreeNode<T> right();

    default int height() {
        return BinaryTreeProperties.height(this);
    }

    default int size() {
        return BinaryTreeProperties.size(this);
    }

    default boolean contains(T value) {
        return BinaryTreeProperties.contains(this, value);
    }

    default boolean isBalanced() {
        return BinaryTreeProperties.isBalanced(this);
    }
}
