package br.com.dbarreto.datastructure.node;

public interface MutableBinaryTreeNode<T> extends BinaryTreeNode<T>, MutableTreeNode<T> {
    
    void setLeft(MutableBinaryTreeNode<T> left);
    void setRight(MutableBinaryTreeNode<T> right);

    @Override
    default BinaryTreeNode<T> left() {
        return leftMutable();
    }

    @Override
    default BinaryTreeNode<T> right() {
        return rightMutable();
    }

    MutableBinaryTreeNode<T> leftMutable();
    MutableBinaryTreeNode<T> rightMutable();
}
