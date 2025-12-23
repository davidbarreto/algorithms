package br.com.dbarreto.datastructure.node;

public interface BinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    BinarySearchTreeNode<T> left();
    BinarySearchTreeNode<T> right();
}
