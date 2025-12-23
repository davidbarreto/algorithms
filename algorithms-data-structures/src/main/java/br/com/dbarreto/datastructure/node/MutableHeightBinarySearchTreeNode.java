package br.com.dbarreto.datastructure.node;

public interface MutableHeightBinarySearchTreeNode<T extends Comparable<T>, N extends MutableHeightBinarySearchTreeNode<T,N>>
        extends MutableBinarySearchTreeNode<T, N>, HeightBinarySearchTreeNode<T> {
    
    @Override
    N leftMutable();
    
    @Override
    N rightMutable();

    @Override
    default N right() {
        return rightMutable();
    }

    @Override
    default N left() {
        return leftMutable();
    }

    void setHeight(int height);
}