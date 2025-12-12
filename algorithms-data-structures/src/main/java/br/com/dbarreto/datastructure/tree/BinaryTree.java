package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.algorithm.tree.traversal.BinaryTreeTraversals;
import br.com.dbarreto.datastructure.node.BinaryTreeNode;
import java.util.function.Consumer;

public interface BinaryTree<T> extends RootedTree<T> {

    @Override
    BinaryTreeNode<T> root();

    @Override
    default int height() {
        return height(root());
    }

    private int height(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left()), height(root.right()));
    }

    @Override
    default int size() {
        return size(root());
    }

    private int size(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return 1 + size(root.left()) + size(root.right());
    }

    default boolean contains(T value) {
        return contains(root(), value);
    }

    private boolean contains(BinaryTreeNode<T> root, T value) {
        if (root == null) return false;

        if (root.value().equals(value)) return true;

        return contains(root.left(), value) || contains(root.right(), value);
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
}

