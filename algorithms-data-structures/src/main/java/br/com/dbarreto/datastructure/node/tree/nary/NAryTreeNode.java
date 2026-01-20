package br.com.dbarreto.datastructure.node.tree.nary;

import java.util.Collection;

/**
 * A purely structural interface for a node that can have multiple children.
 *
 * @param <N> The type of the node itself.
 */
public interface NAryTreeNode<N extends NAryTreeNode<N>> {

    /**
     * Returns a collection of the children of this node.
     *
     * @return a collection of child nodes.
     */
    Collection<N> children();
}
