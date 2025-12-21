package br.com.dbarreto.datastructure.node;

public interface MutableBinarySearchTreeNode<T extends Comparable<T>, N extends MutableBinarySearchTreeNode<T,N>> extends BinarySearchTreeNode<T>, MutableBinaryTreeNode<T, N> {
}
