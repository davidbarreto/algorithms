package br.com.dbarreto.datastructure.tree.binary;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;
import br.com.dbarreto.algorithm.tree.BinaryTreeTraversals;
import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.RootedTree;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Represents a Binary Tree data structure.
 * <p>
 * A binary tree is a tree data structure in which each node has at most two children,
 * referred to as the left child and the right child.
 * </p>
 *
 * @param <T> the type of elements stored in the tree
 */
public interface BinaryTree<T> extends RootedTree<T> {

    /**
     * Returns the root node of the binary tree.
     *
     * @return the root node, or {@code null} if the tree is empty
     */
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

    /**
     * Checks if the binary tree is balanced.
     * <p>
     * A binary tree is balanced if the height of the two subtrees of every node never differs by more than 1.
     * </p>
     *
     * @return {@code true} if the tree is balanced, {@code false} otherwise
     */
    default boolean isBalanced() {
        return BinaryTreeOperations.isBalanced(this);
    }

    /**
     * Traverses the tree in in-order sequence (Left, Root, Right) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    default void traverseInOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traverseInOrder(this, visitor);
    }

    /**
     * Traverses the tree in pre-order sequence (Root, Left, Right) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    default void traversePreOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traversePreOrder(this, visitor);
    }

    /**
     * Traverses the tree in post-order sequence (Left, Right, Root) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    default void traversePostOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traversePostOrder(this, visitor);
    }

    /**
     * Traverses the tree in level-order sequence (Breadth-First) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    default void traverseLevelOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traverseLevelOrder(this, visitor);
    }

    /**
     * Returns an iterator for in-order traversal.
     * <p>
     * For binary search trees, this provides elements in sorted order.
     * </p>
     *
     * @return an iterator over the elements in in-order sequence
     */
    default Iterator<T> inOrderIterator() {
        return BinaryTreeIterators.inOrder(this);
    }

    /**
     * Returns an iterator for pre-order traversal.
     * <p>
     * Visits root before children.
     * </p>
     *
     * @return an iterator over the elements in pre-order sequence
     */
    default Iterator<T> preOrderIterator() {
        return BinaryTreeIterators.preOrder(this);
    }

    /**
     * Returns an iterator for post-order traversal.
     * <p>
     * Visits children before root.
     * </p>
     *
     * @return an iterator over the elements in post-order sequence
     */
    default Iterator<T> postOrderIterator() {
        return BinaryTreeIterators.postOrder(this);
    }

    /**
     * Returns an iterator for level-order traversal.
     * <p>
     * Breadth-first traversal using a queue.
     * </p>
     *
     * @return an iterator over the elements in level-order sequence
     */
    default Iterator<T> levelOrderIterator() {
        return BinaryTreeIterators.levelOrder(this);
    }
}
