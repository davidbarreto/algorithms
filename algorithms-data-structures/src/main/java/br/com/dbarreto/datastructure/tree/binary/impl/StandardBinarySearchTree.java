package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.algorithm.tree.BinarySearchTreeOperations;
import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinarySearchTree;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;

/**
 * Simple Binary Search Tree implementation.
 * <p>
 * A Binary Search Tree (BST) is a binary tree data structure where each node has at most two children,
 * referred to as the left child and the right child. For each node:
 * - All values in the left subtree are less than the node's value
 * - All values in the right subtree are greater than or equal to the node's value
 * <p>
 * This property allows for efficient search, insert, and delete operations with average O(log n) time complexity.
 * However, this implementation does not perform balancing, so in the worst case (e.g., inserting sorted data),
 * the tree can degenerate into a linked list with O(n) time complexity.
 * <p>
 * Operations:
 * - Insert: Adds a new value while maintaining BST property
 * - Delete: Removes a value with three cases based on node children
 * - Search: Can be implemented using the BST property for O(log n) lookup
 *
 * @param <T> the type of elements maintained by this tree, must be Comparable
 */
public class StandardBinarySearchTree<T extends Comparable<T>> extends AbstractBinaryTree<T> implements BinarySearchTree<T> {

    private Node<T> root;

    /**
     * Constructs an empty binary search tree.
     */
    public StandardBinarySearchTree() {
        this.root = null;
    }

    public StandardBinarySearchTree(BinaryTree<T> tree) {
        if (!BinarySearchTreeOperations.isBinarySearchTree(tree)) {
            throw new IllegalArgumentException("Tree is not a BST");
        }
        this.root = deepCopy(tree.root());
    }

    static <T> Node<T> deepCopy(BinaryTreeNode<T> oldRoot) {
        if (oldRoot == null) {
            return null;
        }

        Node<T> newRoot = new Node<>(oldRoot.value());

        newRoot.setLeft(deepCopy(oldRoot.left()));
        newRoot.setRight(deepCopy(oldRoot.right()));

        return newRoot;
    }

    /**
     * Inserts a new value into this binary search tree.
     * <p>
     * The insertion maintains the BST property by placing smaller values to the left
     * and larger or equal values to the right. Duplicates are allowed and placed on the right.
     *
     * @param value the value to be inserted
     */
    @Override
    public void insert(T value) {
        this.root = insert(this.root, value);
    }

    /**
     * Recursive helper method for inserting a value into the BST.
     * <p>
     * Traverses the tree recursively to find the correct position for the new value,
     * then creates a new node at that position.
     *
     * @param root the root of the current subtree
     * @param value the value to insert
     * @return the root of the subtree after insertion
     */
    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }

        // Recursively insert into left or right subtree based on comparison
        if (value.compareTo(root.value()) < 0) {
            root.setLeft(insert(root.left(), value));
        } else {
            root.setRight(insert(root.right(), value));
        }

        return root;
    }

    /**
     * Deletes a value from this binary search tree.
     * <p>
     * The deletion handles three cases:
     * <p>
     * 1. Node has no children: Simply remove the node
     * <br>
     * 2. Node has one child: Replace node with its child
     * <br>
     * 3. Node has two children: Replace with in-order successor, then delete successor
     *
     * @param value the value to be deleted
     */
    @Override
    public void delete(T value) {
        this.root = delete(root, value);
    }

    /**
     * Recursive helper method for deleting a value from the BST.
     * <p>
     * Implements the three deletion cases while maintaining BST property.
     *
     * @param root the root of the current subtree
     * @param value the value to delete
     * @return the root of the subtree after deletion
     */
    private Node<T> delete(Node<T> root, T value) {
        if (root == null) {
            return null; // Value not found, nothing to delete
        }

        // Traverse to find the node to delete
        if (root.value().compareTo(value) > 0) {
            root.setLeft(delete(root.left(), value));
        } else if (root.value().compareTo(value) < 0) {
            root.setRight(delete(root.right(), value));
        } else {
            // Found the node to delete - handle three cases

            // Case 1: No left child - return right child (could be null)
            if (root.left() == null) {
                return root.right();
            }

            // Case 2: No right child - return left child
            if (root.right() == null) {
                return root.left();
            }

            // Case 3: Two children - replace with successor and delete successor
            var successor = findSuccessor(root); // Find in-order successor
            var successorValue = successor != null ? successor.value() : null;
            root.setValue(successorValue); // Replace current node's value with successor's value
            root.setRight(delete(root.right(), successorValue)); // Delete successor from right subtree
        }
        return root;
    }

    @Override
    public T min() {
        return BinarySearchTreeOperations.min(this);
    }

    @Override
    public T max() {
        return BinarySearchTreeOperations.max(this);
    }

    @Override
    public boolean contains(T value) {
        return BinarySearchTreeOperations.contains(this, value);
    }

    /**
     * Finds the in-order successor of a given node.
     * <p>
     * The in-order successor is the smallest node greater than the current node.
     * For a node with a right subtree, it's the leftmost node in the right subtree.
     *
     * @param current the node whose successor we want to find
     * @return the in-order successor node, or null if none exists
     */
    private BinaryTreeNode<T> findSuccessor(BinaryTreeNode<T> current) {
        current = current.right(); // Go to right subtree
        while (current != null && current.left() != null) {
            current = current.left(); // Find leftmost node in right subtree
        }
        return current;
    }

    /**
     * Returns the root node of this binary search tree.
     *
     * @return the root node, or null if the tree is empty
     */
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }
}
