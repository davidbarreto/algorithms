package br.com.dbarreto.datastructure.node;

public interface ColoredBinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    enum Color {
        RED,
        BLACK;

        public boolean isRed() {
            return this == Color.RED;
        }

        public boolean isBlack() {
            return this == Color.BLACK;
        }
    }
    Color color();
    ColoredBinarySearchTreeNode<T> parent();
}

