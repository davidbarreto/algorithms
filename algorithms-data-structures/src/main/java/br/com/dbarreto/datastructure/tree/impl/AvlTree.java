package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.node.impl.SimpleMutableHeightBinarySearchTreeNode;
import br.com.dbarreto.datastructure.tree.SelfBalancingBinarySearchTree;

/**
 * AVL Tree implementation.
 *
 * An AVL Tree is a self-balancing binary search tree where the difference between the heights
 * of the left and right subtrees (balance factor) of any node is at most 1. This ensures that
 * the tree remains approximately balanced, guaranteeing O(log n) time complexity for search,
 * insert, and delete operations.
 *
 * Balance Factor: For any node, balance factor = height(left subtree) - height(right subtree)
 * - If balance factor > 1: Left subtree is taller (left-heavy)
 * - If balance factor < -1: Right subtree is taller (right-heavy)
 * - If balance factor is -1, 0, or 1: Tree is balanced at that node
 *
 * When imbalance occurs after insertion, rotations are performed to restore balance:
 * - Left-Left case: Single right rotation
 * - Right-Right case: Single left rotation
 * - Left-Right case: Left rotation on left child, then right rotation on root
 * - Right-Left case: Right rotation on right child, then left rotation on root
 *
 * @param <T> the type of elements maintained by this tree, must be Comparable
 */
public class AvlTree<T extends Comparable<T>> extends SimpleBinarySearchTree<T> implements SelfBalancingBinarySearchTree<T, SimpleMutableHeightBinarySearchTreeNode<T>> {

    private SimpleMutableHeightBinarySearchTreeNode<T> root;

    public AvlTree() {
        this(null);
    }

    public AvlTree(SimpleMutableHeightBinarySearchTreeNode<T> root) {
        this.root = root;
    }

    /**
     * Returns the root node of this AVL tree.
     *
     * @return the root node
     */
    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> root() {
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
     *
     * Height is defined as the number of edges on the longest path from the node to a leaf.
     * Returns 0 for null nodes (representing empty subtrees).
     *
     * @param node the root of the subtree
     * @return the height of the subtree
     */
    private int height(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        return node == null ? 0 : node.height();
    }

    /**
     * Performs a left rotation on the given node.
     *
     * Left rotation: When a node has a right child, and we want to rotate the node to the left,
     * making its right child the new parent. This helps balance the tree when the right subtree
     * is heavier. After rotation, heights are recalculated for both nodes.
     *
     * @param node the node to rotate left
     * @return the new root of the subtree after rotation
     */
    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> rotateLeft(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        var right = node.rightMutable(); // The right child becomes the new parent
        var temp = right.leftMutable(); // The left subtree of the right child needs to be reattached

        right.setLeft(node); // Move the original node to the left of its right child
        node.setRight(temp); // Attach the temp subtree to the right of the original node

        // Recalculate heights after rotation
        node.setHeight(Math.max(height(node.leftMutable()), height(node.rightMutable())) + 1);
        right.setHeight(Math.max(height(right.leftMutable()), height(right.rightMutable())) + 1);

        return right; // Return the new root of this subtree
    }

    /**
     * Performs a right rotation on the given node.
     *
     * Right rotation: When a node has a left child, and we want to rotate the node to the right,
     * making its left child the new parent. This helps balance the tree when the left subtree
     * is heavier. After rotation, heights are recalculated for both nodes.
     *
     * @param node the node to rotate right
     * @return the new root of the subtree after rotation
     */
    @Override
    public SimpleMutableHeightBinarySearchTreeNode<T> rotateRight(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        var left = node.leftMutable(); // The left child becomes the new parent
        var temp = left.rightMutable(); // The right subtree of the left child needs to be reattached

        left.setRight(node); // Move the original node to the right of its left child
        node.setLeft(temp); // Attach the temp subtree to the left of the original node

        // Recalculate heights after rotation
        node.setHeight(Math.max(height(node.leftMutable()), height(node.rightMutable())) + 1);
        left.setHeight(Math.max(height(left.leftMutable()), height(left.rightMutable())) + 1);

        return left; // Return the new root of this subtree
    }

    /**
     * Inserts a new value into this AVL tree.
     *
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
     *
     * This method performs the actual insertion and balancing:
     * 1. Recursively insert into the appropriate subtree
     * 2. Update the height of the current node
     * 3. Check balance factor and perform rotations if unbalanced
     *
     * @param root the root of the current subtree
     * @param value the value to insert
     * @return the new root of the subtree after insertion and balancing
     */
    private SimpleMutableHeightBinarySearchTreeNode<T> insert(SimpleMutableHeightBinarySearchTreeNode<T> root, T value) {
        if (root == null) {
            return new SimpleMutableHeightBinarySearchTreeNode<>(value);
        }

        // Recursively insert into left or right subtree
        int comparison = value.compareTo(root.value());
        if (comparison < 0) {
            root.setLeft(insert(root.leftMutable(), value));
        } else if (comparison > 0) {
            root.setRight(insert(root.rightMutable(), value));
        } else {
            // It's a duplication. Discard it by returning the current root unchanged
            return root;
        }

        // Update height of current node
        root.setHeight(1 + Math.max(height(root.leftMutable()), height(root.rightMutable())));

        // Get balance factor
        int balance = getBalance(root);

        // Left-Left case: Left subtree is heavy, and new node is in left-left
        // Balance > 1 means left-heavy, and value < root.left.value() means it went left of left child
        if (balance > 1 && value.compareTo(root.left().value()) < 0) {
            return rotateRight(root);
        }

        // Right-Right case: Right subtree is heavy, and new node is in right-right
        // Balance < -1 means right-heavy, and value >= root.right.value() means it went right of right child
        if (balance < -1 && value.compareTo(root.right().value()) >= 0) {
            return rotateLeft(root);
        }

        // Left-Right case: Left subtree is heavy, but new node is in left-right
        // Balance > 1 means left-heavy, but value > root.left.value() means it went right of left child
        // First rotate left on left child, then right on root
        if (balance > 1 && value.compareTo(root.left().value()) > 0) {
            root.setLeft(rotateLeft(root.leftMutable())); // Left rotation on left child
            return rotateRight(root); // Right rotation on root
        }

        // Right-Left case: Right subtree is heavy, but new node is in right-left
        // Balance < -1 means right-heavy, but value < root.right.value() means it went left of right child
        // First rotate right on right child, then left on root
        if (balance < -1 && value.compareTo(root.right().value()) < 0) {
            root.setRight(rotateRight(root.rightMutable())); // Right rotation on right child
            return rotateLeft(root); // Left rotation on root
        }

        return root; // No rotation needed, tree is balanced
    }

    /**
     * Returns the balance factor of this AVL tree.
     *
     * @return the balance of the tree (0 if empty)
     */
    public int getBalance() {
        return getBalance(this.root);
    }

    /**
     * Calculates the balance factor of a node.
     *
     * Balance factor = height(left subtree) - height(right subtree)
     * - Positive: left-heavy
     * - Negative: right-heavy
     * - Zero: balanced
     *
     * @param node the node to calculate balance factor for
     * @return the balance factor
     */
    private int getBalance(SimpleMutableHeightBinarySearchTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left()) - height(node.right());
    }
}
