package br.com.dbarreto.datastructure.node;

public interface MutableTreeNode<T> extends TreeNode<T> {
    void setValue(T value);
}
