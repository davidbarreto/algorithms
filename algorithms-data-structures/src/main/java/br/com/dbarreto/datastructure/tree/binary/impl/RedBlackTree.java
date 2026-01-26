package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeChildDirection;
import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;

import static br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeChildDirection.LEFT;
import static br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeChildDirection.RIGHT;

/**
 * Red-Black Tree implementation.
 * <p>
 * A Red-Black Tree is a self-balancing binary search tree where each node has a color (red or black)
 * that ensures the tree remains approximately balanced during insertions and deletions.
 * <p>
 * Red-Black Tree Properties:
 * 1. Every node is either red or black.
 * 2. The root is always black.
 * 3. Every leaf (null node) is black.
 * 4. If a node is red, then both its children must be black (no two red nodes in a row).
 * 5. Every path from a node to a descendant leaf contains the same number of black nodes (black-height property).
 * <p>
 * These properties guarantee that the tree height is O(log n), ensuring efficient operations.
 *
 * @param <T> the type of elements maintained by this tree, must be Comparable
 */
public class RedBlackTree<T extends Comparable<T>> extends StandardBinarySearchTree<T> {

    private RedBlackNode<T> root;

    /**
     * General rotation method that performs either left or right rotation based on direction.
     * <p>
     * Rotation maintains the binary search tree property while changing the structure to balance the tree.
     * The direction parameter determines whether it's a left or right rotation.
     *
     * @param node the node to rotate
     * @param direction the direction of rotation (LEFT for left rotation, RIGHT for right rotation)
     */
    private void rotate(RedBlackNode<T> node, BinaryTreeChildDirection direction) {

        var invertedDirection = direction.invert();

        var child = node.child(invertedDirection); // The child that will become the new parent
        var temp = child.child(direction); // The subtree that needs to be reattached
        var parent = node.parent; // Original parent of the node

        // Move temp to the correct position under node
        node.setChild(temp, invertedDirection);

        if (temp != null) {
            temp.parent = node;
        }

        // Update child's parent
        child.parent = parent;

        // Update the parent's child reference
        if (parent == null) {
            this.root = child; // child becomes the new root
        } else if (node == parent.child(direction)) {
            parent.setChild(child, direction);
        } else {
            parent.setChild(child, invertedDirection);
        }

        // Complete the rotation
        child.setChild(node, direction);
        node.parent = child;
    }

    /**
     * Inserts a new value into this Red-Black tree.
     * <p>
     * The insertion follows the standard binary search tree insertion, but then fixes any
     * Red-Black property violations that may have occurred. New nodes are always inserted
     * as red to minimize violations, and then the tree is rebalanced if necessary.
     * <p>
     * Duplicates are placed on the right side.
     *
     * @param value The value to be inserted into this Red-Black tree
     */
    @Override
    public void insert(T value) {
        var node = new RedBlackNode<>(value);
        node.color = Color.RED; // New nodes start as RED to minimize property violations

        var parent = findParent(null, this.root, value);
        node.parent = parent;

        if (parent == null) {
            this.root = node;
            node.color = Color.BLACK; // Root must always be black
            return;
        }

        // Insert as left or right child based on comparison
        if (value.compareTo(parent.value()) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        // If node has no grandparent, no need to fix (grandparent is root or near root)
        if (parent.parent == null) {
            return;
        }

        // Fix any Red-Black property violations caused by the insertion
        fixInsert(node);
        // Ensure root is always black
        this.root.color = Color.BLACK;
    }

    /**
     * Finds the parent node where a new value should be inserted.
     * <p>
     * This is a recursive helper method that traverses the tree to find the appropriate
     * parent for a new node with the given value, maintaining the binary search tree property.
     *
     * @param parent the current parent candidate
     * @param current the current node being examined
     * @param value the value to insert
     * @return the parent node for the new value
     */
    private RedBlackNode<T> findParent(RedBlackNode<T> parent, RedBlackNode<T> current, T value) {
        if (current == null) {
            return parent;
        }

        parent = current;
        current = value.compareTo(current.value()) < 0 ? current.left : current.right;

        return findParent(parent, current, value);
    }

    /**
     * Returns the root node of this Red-Black tree.
     *
     * @return the root node
     */
    @Override
    public BinaryTreeNode<T> root() {
        return getRoot();
    }

    RedBlackNode<T> getRoot() {
        return this.root;
    }

    /**
     * Fixes Red-Black tree properties after insertion.
     * <p>
     * This method is called after inserting a new red node. It traverses up the tree
     * fixing any violations of the Red-Black properties, specifically when a red node
     * has a red parent (violating property 4: no two consecutive red nodes).
     *
     * @param node the newly inserted node that may have caused violations
     */
    private void fixInsert(RedBlackNode<T> node) {
        // Continue fixing while node is not root and its parent is red (violation)
        while (node != this.root && isRed(node.parent)) {

            // Determine which side the parent is on relative to grandparent
            if (isLeftParent(node)) {
                node = fixInsert(node, LEFT);
            } else {
                node = fixInsert(node, RIGHT);
            }
            // Ensure root remains black
            this.root.color = Color.BLACK;
        }
    }

    /**
     * Checks if the node's parent is the left child of its grandparent.
     *
     * @param node the node to check
     * @return true if parent is left child of grandparent
     */
    private boolean isLeftParent(RedBlackNode<T> node) {
        return node.parent == node.parent.parent.left;
    }

    /**
     * Fixes insertion violations for a specific direction (left or right case).
     * <p>
     * This method handles the four cases that can occur during Red-Black tree insertion:
     * <p>
     * 1. Uncle is red: Recolor parent, uncle black, grandparent red, and continue with grandparent.
     * <br>
     * 2. Uncle is black and node is right child: Rotate parent towards the direction.
     * <br>
     * 3. Uncle is black and node is left child: Rotate grandparent and recolor.
     *
     * @param node the current node being fixed
     * @param direction the direction indicating which side the violation occurs
     * @return the node to continue fixing from (usually the grandparent or rotated node)
     */
    private RedBlackNode<T> fixInsert(RedBlackNode<T> node, BinaryTreeChildDirection direction) {

        var invertedDirection = direction.invert();

        var parent = node.parent;
        var grandParent = parent.parent;
        var uncle = grandParent.child(invertedDirection); // Uncle is the sibling of parent

        // Case 1: Uncle is red - recolor and move up to grandparent
        if (isRed(uncle)) {
            recolor(parent, uncle, grandParent);
            return grandParent; // Continue fixing from grandparent
        }

        // Case 2: Uncle is black and node is the "inner" child (right child when parent is left, or vice versa)
        // Need to rotate parent to make it an "outer" child case
        if (node == parent.child(invertedDirection)) {
            rotate(parent, direction); // Rotate parent towards the direction
            node = parent; // Now node becomes the old parent
            parent = node.parent;
            grandParent = parent.parent;
        }

        // Case 3: Uncle is black and node is the "outer" child - rotate grandparent and recolor
        rotateAndRecolor(parent, grandParent, invertedDirection);
        return node;
    }

    /**
     * Returns the black height of the given node.
     * Black height is the number of black nodes on any path from the node to a leaf.
     * Returns -1 if the black heights are inconsistent (violation of property 5).
     * 
     * @param node the node to get black height for
     * @return the black height, or -1 if inconsistent
     */
    static <T> int blackHeight(RedBlackNode<T> node) {

        if (node == null) {
            return 1; // null nodes are black
        }

        int leftHeight = blackHeight(node.left);
        int rightHeight = blackHeight(node.right);

        boolean eitherSidesInvalid = leftHeight == -1 || rightHeight == -1;
        boolean isUnbalanced = leftHeight != rightHeight;
        if (eitherSidesInvalid || isUnbalanced) {
            return -1; // violation
        }

        // Add 1 if current node is black
        // It does not matter whether you use leftHeight or rightHeight,
        // because at that point they are guaranteed to be equal.
        return leftHeight + (node.color == Color.BLACK ? 1 : 0);
    }

    /**
     * Checks if a node is red.
     *
     * @param node the node to check
     * @return true if the node is red, false if black or null
     */
    static <T> boolean isRed(RedBlackNode<T> node) {
        return node != null && node.color == Color.RED;
    }

    /**
     * Recolors nodes during insertion fix-up.
     * <p>
     * This is used in Case 1 of the insertion fix-up: when both parent and uncle are red,
     * we recolor parent and uncle to black, and grandparent to red. This pushes the red
     * violation up to the grandparent level.
     *
     * @param parent the parent node
     * @param uncle the uncle node
     * @param grandParent the grandparent node
     */
    private void recolor(RedBlackNode<T> parent, RedBlackNode<T> uncle, RedBlackNode<T> grandParent) {
        parent.color = Color.BLACK;
        uncle.color = Color.BLACK;
        grandParent.color = Color.RED;
    }

    /**
     * Performs rotation and recoloring during insertion fix-up.
     * <p>
     * This is used in Case 3 of the insertion fix-up: when uncle is black and the node
     * is an "outer" child. We rotate the grandparent and recolor to fix the violation.
     * The parent becomes black, grandparent becomes red, maintaining the black-height property.
     *
     * @param parent the parent node
     * @param grandParent the grandparent node
     * @param direction the direction to rotate the grandparent
     */
    private void rotateAndRecolor(RedBlackNode<T> parent, RedBlackNode<T> grandParent,
        BinaryTreeChildDirection direction)
    {
        parent.color = Color.BLACK;
        grandParent.color = Color.RED;
        rotate(grandParent, direction);
    }

    static class RedBlackNode<T> implements BinaryTreeNode<T> {

        private final T value;
        private RedBlackNode<T> parent;
        RedBlackNode<T> left;
        RedBlackNode<T> right;
        private Color color;

        RedBlackNode(T value) {
            this.value = value;
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

        @Override
        public RedBlackNode<T> child(BinaryTreeChildDirection direction) {
            return (direction == LEFT) ? left : right;
        }

        private void setChild(RedBlackNode<T> child, BinaryTreeChildDirection direction) {
            if (direction == LEFT) {
                left = child;
            } else {
                right = child;
            }
        }
    }

    enum Color {
        RED, BLACK
    }
}
