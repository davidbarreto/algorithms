package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.node.MutableBinarySearchTreeNode;

public interface SelfBalancingBinarySearchTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    MutableBinarySearchTreeNode<T> rotateLeft(MutableBinarySearchTreeNode<T> node);
    MutableBinarySearchTreeNode<T> rotateRight(MutableBinarySearchTreeNode<T> node);
}
