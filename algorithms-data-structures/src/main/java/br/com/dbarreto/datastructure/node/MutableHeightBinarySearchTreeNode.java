package br.com.dbarreto.datastructure.node;

public interface MutableHeightBinarySearchTreeNode<T extends Comparable<T>, N extends MutableHeightBinarySearchTreeNode<T,N>>
        extends MutableBinarySearchTreeNode<T, N>, HeightBinarySearchTreeNode<T> {
    
    @Override
    N leftMutable();
    
    @Override
    N rightMutable();

    void setHeight(int height);
}