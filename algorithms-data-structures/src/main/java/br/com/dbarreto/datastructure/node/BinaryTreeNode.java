package br.com.dbarreto.datastructure.node;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;

public interface BinaryTreeNode<T> extends TreeNode<T> {
    BinaryTreeNode<T> left();
    BinaryTreeNode<T> right();

    default BinaryTreeNode<T> child(BinaryTreeChildDirection direction) {
        return (direction == BinaryTreeChildDirection.LEFT) ? left() : right();
    }

    default int height() {
        return BinaryTreeOperations.height(this);
    }

    default int size() {
        return BinaryTreeOperations.size(this);
    }

    default boolean contains(T value) {
        return BinaryTreeOperations.contains(this, value);
    }

    default boolean isBalanced() {
        return BinaryTreeOperations.isBalanced(this);
    }
}
