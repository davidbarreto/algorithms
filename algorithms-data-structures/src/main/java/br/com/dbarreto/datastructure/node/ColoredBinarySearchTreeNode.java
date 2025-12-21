package br.com.dbarreto.datastructure.node;

public interface ColoredBinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    enum Color {
        RED,
        BLACK
    }
    Color color();
    ColoredBinarySearchTreeNode<T> parent();
}

