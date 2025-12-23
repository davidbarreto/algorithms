package br.com.dbarreto.datastructure.tree.impl;

import br.com.dbarreto.datastructure.node.BinaryTreeChildDirection;
import br.com.dbarreto.datastructure.node.ColoredBinarySearchTreeNode;
import br.com.dbarreto.datastructure.node.impl.RedBlackTreeNode;
import br.com.dbarreto.datastructure.tree.SelfBalancingBinarySearchTree;

public class RedBlackTree<T extends Comparable<T>> extends SimpleBinarySearchTree<T> implements SelfBalancingBinarySearchTree<T, RedBlackTreeNode<T>> {

    private RedBlackTreeNode<T> root;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(RedBlackTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public RedBlackTreeNode<T> rotateLeft(RedBlackTreeNode<T> node) {
        return rotate(node, BinaryTreeChildDirection.LEFT);
    }

    @Override
    public RedBlackTreeNode<T> rotateRight(RedBlackTreeNode<T> node) {
        return rotate(node, BinaryTreeChildDirection.RIGHT);
    }

    private RedBlackTreeNode<T> rotate(RedBlackTreeNode<T> node, BinaryTreeChildDirection direction) {

        var invertedDirection = direction.invert();

        var child = node.childMutable(invertedDirection);
        var temp = child.childMutable(direction);
        var parent = node.parentMutable();

        node.setChild(temp, invertedDirection);

        if (temp != null) {
            temp.setParent(node);
        }
        child.setParent(parent);

        if (parent == null) {
            this.root = child;
        } else if (node == parent.child(direction)) {
            parent.setChild(child, direction);
        } else {
            parent.setChild(child, invertedDirection);
        }

        child.setChild(node, direction);
        node.setParent(child);

        return this.root;
    }

    /**
     * Inserts new value in this Red-black tree
     * Duplicates go on the right side
     * @param value The value to be inserted in this Red-black tree
     */
    @Override
    public void insert(T value) {
        var node = new RedBlackTreeNode<>(value);
        node.turnRed(); // new nodes start with RED

        var parent = findParent(null, this.root, value);
        node.setParent(parent);

        if (parent == null) {
            this.root = node;
            node.turnBlack();
            return;
        }

        if (value.compareTo(parent.value()) < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }

        if (parent.parent() == null) {
            return;
        }

        fixInsert(node);
        this.root.turnBlack();
    }

    private RedBlackTreeNode<T> findParent(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> current, T value) {
        if (current == null) {
            return parent;
        }

        parent = current;
        current = value.compareTo(current.value()) < 0 ? current.leftMutable() : current.rightMutable();

        return findParent(parent, current, value);
    }

    @Override
    public RedBlackTreeNode<T> root() {
        return this.root;
    }

    private void fixInsert(RedBlackTreeNode<T> node) {
        while (node != this.root && isRed(node.parent())) {

            if (isLeftParent(node)) {
                node = fixInsert(node, BinaryTreeChildDirection.LEFT);
            } else {
                node = fixInsert(node, BinaryTreeChildDirection.RIGHT);
            }
            this.root.turnBlack();
        }
    }

    private boolean isLeftParent(RedBlackTreeNode<T> node) {
        return node.parent() == node.parent().parent().left();
    }

    private RedBlackTreeNode<T> fixInsert(RedBlackTreeNode<T> node, BinaryTreeChildDirection direction) {

        var invertedDirection = direction.invert();

        var parent = node.parentMutable();
        var grandParent = parent.parentMutable();
        var uncle = grandParent.childMutable(invertedDirection);

        if (isRed(uncle)) {
            recolor(parent, uncle, grandParent);
            return grandParent;
        }

        if (node == parent.childMutable(invertedDirection)) {
            rotate(parent, direction);
            node = parent;
            parent = node.parentMutable();
            grandParent = parent.parentMutable();
        }

        rotateAndRecolor(parent, grandParent, invertedDirection);
        return node;
    }

    private boolean isRed(ColoredBinarySearchTreeNode<T> node) {
        return node != null && node.color().isRed();
    }

    private static <T extends Comparable<T>> void recolor(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> uncle, RedBlackTreeNode<T> grandParent) {
        parent.turnBlack();
        uncle.turnBlack();
        grandParent.turnRed();
    }

    private void rotateAndRecolor(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> grandParent,
                                  BinaryTreeChildDirection direction)
    {
        parent.turnBlack();
        grandParent.turnRed();
        rotate(grandParent, direction);
    }
}
