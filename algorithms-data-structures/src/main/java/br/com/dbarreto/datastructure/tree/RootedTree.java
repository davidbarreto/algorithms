package br.com.dbarreto.datastructure.tree;

import br.com.dbarreto.datastructure.node.TreeNode;

public interface RootedTree<T> extends Tree<T> {
    TreeNode<T> root();
}
