package br.com.dbarreto.datastructure.node;

public interface MutableBinarySearchTreeNode<T extends Comparable<T>, N extends MutableBinarySearchTreeNode<T,N>> extends BinarySearchTreeNode<T>, MutableBinaryTreeNode<T, N> {

    @Override
    default N left() {
        return leftMutable();
    }

    @Override
    default N right() {
        return rightMutable();
    }
}

