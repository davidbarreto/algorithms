package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.node.BinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import br.com.dbarreto.datastructure.node.impl.SimpleMutableBinarySearchTreeNode;
import br.com.dbarreto.datastructure.tree.BinarySearchTree;

/**
 * Simple Binary Search Tree implementation.
 *
 * A Binary Search Tree (BST) is a binary tree data structure where each node has at most two children,
 * referred to as the left child and the right child. For each node:
 * - All values in the left subtree are less than the node's value
 * - All values in the right subtree are greater than or equal to the node's value
 *
 * This property allows for efficient search, insert, and delete operations with average O(log n) time complexity.
 * However, this implementation does not perform balancing, so in the worst case (e.g., inserting sorted data),
 * the tree can degenerate into a linked list with O(n) time complexity.
 *
 * Operations:
 * - Insert: Adds a new value while maintaining BST property
 * - Delete: Removes a value with three cases based on node children
 * - Search: Can be implemented using the BST property for O(log n) lookup
 *
 * @param <T> the type of elements maintained by this tree, must be Comparable
 */
public class SimpleBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private SimpleMutableBinarySearchTreeNode<T> root;

    /**
     * Constructs an empty binary search tree.
     */
    public SimpleBinarySearchTree() {
    }

    /**
     * Constructs a binary search tree with the specified root node.
     *
     * @param root the root node of the tree
     */
    public SimpleBinarySearchTree(SimpleMutableBinarySearchTreeNode<T> root) {
        this.root = root;
    }

    /**
     * Inserts a new value into this binary search tree.
     *
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
     *
     * Traverses the tree recursively to find the correct position for the new value,
     * then creates a new node at that position.
     *
     * @param root the root of the current subtree
     * @param value the value to insert
     * @return the root of the subtree after insertion
     */
    private SimpleMutableBinarySearchTreeNode<T> insert(SimpleMutableBinarySearchTreeNode<T> root, T value) {
        if (root == null) {
            return new SimpleMutableBinarySearchTreeNode<>(value);
        }

        // Recursively insert into left or right subtree based on comparison
        if (value.compareTo(root.value()) < 0) {
            root.setLeft(insert(root.leftMutable(), value));
        } else {
            root.setRight(insert(root.rightMutable(), value));
        }

        return root;
    }

    /**
     * Deletes a value from this binary search tree.
     *
     * The deletion handles three cases:
     * 1. Node has no children: Simply remove the node
     * 2. Node has one child: Replace node with its child
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
     *
     * Implements the three deletion cases while maintaining BST property.
     *
     * @param root the root of the current subtree
     * @param value the value to delete
     * @return the root of the subtree after deletion
     */
    private SimpleMutableBinarySearchTreeNode<T> delete(SimpleMutableBinarySearchTreeNode<T> root, T value) {
        if (root == null) {
            return null; // Value not found, nothing to delete
        }

        // Traverse to find the node to delete
        if (root.value().compareTo(value) > 0) {
            root.setLeft(delete(root.leftMutable(), value));
        } else if (root.value().compareTo(value) < 0) {
            root.setRight(delete(root.rightMutable(), value));
        } else {
            // Found the node to delete - handle three cases

            // Case 1: No left child - return right child (could be null)
            if (root.left() == null) {
                return root.rightMutable();
            }

            // Case 2: No right child - return left child
            if (root.right() == null) {
                return root.leftMutable();
            }

            // Case 3: Two children - replace with successor and delete successor
            var succ = successor(root); // Find in-order successor
            var valSucc = succ != null ? succ.value() : null;
            root.setValue(valSucc); // Replace current node's value with successor's value
            root.setRight(delete(root.rightMutable(), valSucc)); // Delete successor from right subtree
        }
        return root;
    }

    /**
     * Finds the in-order successor of a given node.
     *
     * The in-order successor is the smallest node greater than the current node.
     * For a node with a right subtree, it's the leftmost node in the right subtree.
     *
     * @param current the node whose successor we want to find
     * @return the in-order successor node, or null if none exists
     */
    private BinaryTreeNode<T> successor(BinaryTreeNode<T> current) {
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
    public BinarySearchTreeNode<T> root() {
        return root;
    }
}
