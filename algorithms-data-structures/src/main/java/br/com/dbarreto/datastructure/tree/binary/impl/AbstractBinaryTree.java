package br.com.dbarreto.datastructure.tree.binary.impl;

import br.com.dbarreto.algorithm.tree.BinaryTreeOperations;
import br.com.dbarreto.algorithm.tree.BinaryTreeTraversals;
import br.com.dbarreto.datastructure.node.tree.binary.BinaryTreeNode;
import br.com.dbarreto.datastructure.tree.binary.BinaryTree;
import br.com.dbarreto.datastructure.tree.binary.BinaryTreeIterators;

import java.util.Iterator;
import java.util.function.Consumer;

public abstract class AbstractBinaryTree<T> implements BinaryTree<T> {

    @Override
    public boolean isBalanced() {
        return BinaryTreeOperations.isBalanced(this);
    }

    @Override
    public void traverseInOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traverseInOrder(this, visitor);
    }

    @Override
    public void traversePreOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traversePreOrder(this, visitor);
    }

    @Override
    public void traversePostOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traversePostOrder(this, visitor);
    }

    @Override
    public void traverseLevelOrder(Consumer<T> visitor) {
        BinaryTreeTraversals.traverseLevelOrder(this, visitor);
    }

    @Override
    public Iterator<T> inOrderIterator() {
        return BinaryTreeIterators.inOrder(this);
    }

    @Override
    public Iterator<T> preOrderIterator() {
        return BinaryTreeIterators.preOrder(this);
    }

    @Override
    public Iterator<T> postOrderIterator() {
        return BinaryTreeIterators.postOrder(this);
    }

    @Override
    public Iterator<T> levelOrderIterator() {
        return BinaryTreeIterators.levelOrder(this);
    }

    @Override
    public int height() {
        return BinaryTreeOperations.height(this);
    }

    @Override
    public int size() {
        return BinaryTreeOperations.size(this);
    }

    @Override
    public boolean contains(T value) {
        return BinaryTreeOperations.contains(this, value);
    }

    static class Node<T> implements BinaryTreeNode<T> {

        private T value;
        private Node<T> left;
        private Node<T> right;

        Node(T value) {
            this.value = value;
        }

        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public Node<T> left() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        @Override
        public Node<T> right() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        @Override
        public T value() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
