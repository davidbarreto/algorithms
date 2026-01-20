package br.com.dbarreto.datastructure.node.tree.nary;

import br.com.dbarreto.datastructure.node.tree.TreeNode;

/**
 * Represents a node in an N-ary tree that also holds a value.
 * <p>
 * This interface composes the structural properties of an {@link NAryTreeNode}
 * with the value-holding property of a {@link TreeNode}. It serves as a
 * convenient base for concrete N-ary tree node implementations.
 *
 * @param <T> the type of value stored in the node.
 */
public interface ValuedNAryTreeNode<T> extends NAryTreeNode<ValuedNAryTreeNode<T>>, TreeNode<T> {
    // This interface is intentionally left blank.
    // Its purpose is to unite the parent interfaces into a single, expressive type.
}
