package br.com.dbarreto.datastructure.node;

public interface MutableBinarySearchTreeNode<T extends Comparable<T>>
        extends MutableBinaryTreeNode<T>, BinarySearchTreeNode<T> {
    
    @Override
    MutableBinarySearchTreeNode<T> leftMutable();
    
    @Override
    MutableBinarySearchTreeNode<T> rightMutable();

    void setHeight(int height);
}