package br.com.dbarreto.datastructure.node;

import br.com.dbarreto.datastructure.node.impl.RedBlackTreeNode;

public interface MutableColoredBinarySearchTreeNode<T extends Comparable<T>, N extends MutableColoredBinarySearchTreeNode<T, N>> extends MutableBinarySearchTreeNode<T, N>, ColoredBinarySearchTreeNode<T> {

    void setLeft(N left);

    void setColor(Color color);

    @Override
    default ColoredBinarySearchTreeNode<T> parent() {
        return parentMutable();
    }

    N parentMutable();
    void setParent(N parent);
}
