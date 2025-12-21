package br.com.dbarreto.datastructure.node;

public interface HeightBinarySearchTreeNode<T extends Comparable<T>> extends BinarySearchTreeNode<T> {
    int height();
}
