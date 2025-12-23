package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;
import br.com.dbarreto.algorithm.tree.BinaryTreeTraversals;
import br.com.dbarreto.datastructure.node.BinaryTreeNode;

import java.util.Iterator;
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

    /**
     * Returns an iterator for in-order traversal.
     * For binary search trees, this provides elements in sorted order.
     */
    default Iterator<T> inOrderIterator() {
        return TreeIterators.inOrder(this);
    }

    /**
     * Returns an iterator for pre-order traversal.
     * Visits root before children.
     */
    default Iterator<T> preOrderIterator() {
        return TreeIterators.preOrder(this);
    }

    /**
     * Returns an iterator for post-order traversal.
     * Visits children before root.
     */
    default Iterator<T> postOrderIterator() {
        return TreeIterators.postOrder(this);
    }

    /**
     * Returns an iterator for level-order traversal.
     * Breadth-first traversal using a queue.
     */
    default Iterator<T> levelOrderIterator() {
        return TreeIterators.levelOrder(this);
    }
}

