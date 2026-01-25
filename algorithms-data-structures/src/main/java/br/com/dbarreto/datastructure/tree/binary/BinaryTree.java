package br.com.dbarreto.datastructure.tree.binary;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.Tree;

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
public interface BinaryTree<T> extends Tree<T> {

    /**
     * Returns the root node of the binary tree.
     *
     * @return the root node, or {@code null} if the tree is empty
     */
    BinaryTreeNode<T> root();

    /**
     * Checks if the binary tree is balanced.
     * <p>
     * A binary tree is balanced if the height of the two subtrees of every node never differs by more than 1.
     * </p>
     *
     * @return {@code true} if the tree is balanced, {@code false} otherwise
     */
    boolean isBalanced();

    /**
     * Traverses the tree in in-order sequence (Left, Root, Right) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    void traverseInOrder(Consumer<T> visitor);

    /**
     * Traverses the tree in pre-order sequence (Root, Left, Right) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    void traversePreOrder(Consumer<T> visitor);

    /**
     * Traverses the tree in post-order sequence (Left, Right, Root) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    void traversePostOrder(Consumer<T> visitor);

    /**
     * Traverses the tree in level-order sequence (Breadth-First) and applies the visitor to each element.
     *
     * @param visitor the action to be performed for each element
     */
    void traverseLevelOrder(Consumer<T> visitor);

    /**
     * Returns an iterator for in-order traversal.
     * <p>
     * For binary search trees, this provides elements in sorted order.
     * </p>
     *
     * @return an iterator over the elements in in-order sequence
     */
   Iterator<T> inOrderIterator();

    /**
     * Returns an iterator for pre-order traversal.
     * <p>
     * Visits root before children.
     * </p>
     *
     * @return an iterator over the elements in pre-order sequence
     */
    Iterator<T> preOrderIterator();

    /**
     * Returns an iterator for post-order traversal.
     * <p>
     * Visits children before root.
     * </p>
     *
     * @return an iterator over the elements in post-order sequence
     */
    Iterator<T> postOrderIterator();

    /**
     * Returns an iterator for level-order traversal.
     * <p>
     * Breadth-first traversal using a queue.
     * </p>
     *
     * @return an iterator over the elements in level-order sequence
     */
    Iterator<T> levelOrderIterator();
}
