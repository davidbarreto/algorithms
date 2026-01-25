package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;

/**
 * AVL Tree implementation.
 * <p>
 * An AVL Tree is a self-balancing binary search tree where the difference between the heights
 * of the left and right subtrees (balance factor) of any node is at most 1. This ensures that
 * the tree remains approximately balanced, guaranteeing O(log n) time complexity for search,
 * insert, and delete operations.
 * <p>
 * Balance Factor: For any node, balance factor = height(left subtree) - height(right subtree)
 * - If balance factor > 1: Left subtree is taller (left-heavy)
 * - If balance factor < -1: Right subtree is taller (right-heavy)
 * - If balance factor is -1, 0, or 1: Tree is balanced at that node
 * <p>
 * When imbalance occurs after insertion, rotations are performed to restore balance:
 * - Left-Left case: Single right rotation
 * - Right-Right case: Single left rotation
 * - Left-Right case: Left rotation on left child, then right rotation on root
 * - Right-Left case: Right rotation on right child, then left rotation on root
 *
 * @param <T> the type of elements maintained by this tree, must be Comparable
 */
public class AvlTree<T extends Comparable<T>> extends StandardBinarySearchTree<T> {

    private AvlNode<T> root;

    /**
     * Returns the root node of this AVL tree.
     *
     * @return the root node
     */
    @Override
    public BinaryTreeNode<T> root() {
        return getRoot();
    }

    AvlNode<T> getRoot() {
        return this.root;
    }

    /**
     * Returns the height of this AVL tree.
     *
     * @return the height of the tree (0 if empty)
     */
    @Override
    public int height() {
        return height(this.root);
    }

    /**
     * Calculates the height of a subtree rooted at the given node.
     * <p>
     * Height is defined as the number of edges on the longest path from the node to a leaf.
     * Returns 0 for null nodes (representing empty subtrees).
     *
     * @param node the root of the subtree
     * @return the height of the subtree
     */
    private static <T> int height(AvlNode<T> node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Performs a left rotation on the given node.
     * <p>
     * Left rotation: When a node has a right child, and we want to rotate the node to the left,
     * making its right child the new parent. This helps balance the tree when the right subtree
     * is heavier. After rotation, heights are recalculated for both nodes.
     *
     * @param node the node to rotate left
     * @return the new root of the subtree after rotation
     */
    private AvlNode<T> rotateLeft(AvlNode<T> node) {
        var right = node.right; // The right child becomes the new parent
        var temp = right.left; // The left subtree of the right child needs to be reattached

        right.left = node; // Move the original node to the left of its right child
        node.right = temp; // Attach the temp subtree to the right of the original node

        // Recalculate heights after rotation
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        right.height = Math.max(height(right.left), height(right.right)) + 1;

        return right; // Return the new root of this subtree
    }

    /**
     * Performs a right rotation on the given node.
     * <p>
     * Right rotation: When a node has a left child, and we want to rotate the node to the right,
     * making its left child the new parent. This helps balance the tree when the left subtree
     * is heavier. After rotation, heights are recalculated for both nodes.
     *
     * @param node the node to rotate right
     * @return the new root of the subtree after rotation
     */
    private AvlNode<T> rotateRight(AvlNode<T> node) {
        var left = node.left; // The left child becomes the new parent
        var temp = left.right; // The right subtree of the left child needs to be reattached

        left.right = node; // Move the original node to the right of its left child
        node.left = temp; // Attach the temp subtree to the left of the original node

        // Recalculate heights after rotation
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left), height(left.right)) + 1;

        return left; // Return the new root of this subtree
    }

    /**
     * Inserts a new value into this AVL tree.
     * <p>
     * The insertion follows the standard binary search tree insertion, but then checks
     * the balance factor and performs rotations if necessary to maintain the AVL property.
     * The method recursively inserts the value and balances the tree on the way back up.
     *
     * @param value the value to be inserted
     */
    @Override
    public void insert(T value) {
        this.root = insert(this.root, value);
    }

    /**
     * Recursive helper method for inserting a value into the AVL tree.
     * <p>
     * This method performs the actual insertion and balancing:
     * 1. Recursively insert into the appropriate subtree
     * 2. Update the height of the current node
     * 3. Check balance factor and perform rotations if unbalanced
     *
     * @param root the root of the current subtree
     * @param value the value to insert
     * @return the new root of the subtree after insertion and balancing
     */
    private AvlNode<T> insert(AvlNode<T> root, T value) {
        if (root == null) {
            return new AvlNode<>(value);
        }

        // Recursively insert into left or right subtree
        int comparison = value.compareTo(root.value());
        if (comparison < 0) {
            root.left = insert(root.left, value);
        } else if (comparison > 0) {
            root.right = insert(root.right, value);
        } else {
            // It's a duplication. Discard it by returning the current root unchanged
            return root;
        }

        // Update height of current node
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Get balance factor
        int balance = balanceFactor(root);

        if (balance > 1) {
            if (balanceFactor(root.left) >= 0) {
                return rotateRight(root);
            } else {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        }

        if (balance < -1) {
            if (balanceFactor(root.right) <= 0) {
                return rotateLeft(root);
            } else {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }

        return root; // No rotation needed, tree is balanced
    }

    /**
     * Returns the balance factor of this AVL tree.
     *
     * @return the balance of the tree (0 if empty)
     */
    public int balanceFactor() {
        return balanceFactor(this.root);
    }

    /**
     * Calculates the balance factor of a node.
     * <p>
     * Balance factor = height(left subtree) - height(right subtree)
     * - Positive: left-heavy
     * - Negative: right-heavy
     * - Zero: balanced
     *
     * @param node the node to calculate balance factor for
     * @return the balance factor
     */
    static <T> int balanceFactor(AvlNode<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    static class AvlNode<T> implements BinaryTreeNode<T> {
        private final T value;
        AvlNode<T> left;
        AvlNode<T> right;
        private int height;

        AvlNode(T value) {
            this.value = value;
            this.height = 1;
        }

        @Override
        public BinaryTreeNode<T> left() {
            return left;
        }

        @Override
        public BinaryTreeNode<T> right() {
            return right;
        }

        @Override
        public T value() {
            return value;
        }
    }
}
