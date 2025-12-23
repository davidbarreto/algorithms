package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;
import br.com.dbarreto.algorithm.tree.BinaryTreeTraversals;
import br.com.dbarreto.datastructure.node.BinaryTreeNode;

import java.util.function.Consumer;

public interface BinaryTree<T> extends RootedTree<T> {

    @Override
    BinaryTreeNode<T> root();

    @Override
    default int height() {
        return BinaryTreeOperations.height(this);
    }

    @Override
    default int size() {
        return BinaryTreeOperations.size(this);
    }

    @Override
    default boolean contains(T value) {
        return BinaryTreeOperations.contains(this, value);
    }

    default boolean isBalanced() {
        return BinaryTreeOperations.isBalanced(this);
    }

    default void traverseInOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traverseInOrder(this, visitor);
    }

    default void traversePreOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traversePreOrder(this, visitor);
    }

    default void traversePostOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traversePostOrder(this, visitor);
    }

    default void traverseLevelOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traverseLevelOrder(this, visitor);
    }
}

