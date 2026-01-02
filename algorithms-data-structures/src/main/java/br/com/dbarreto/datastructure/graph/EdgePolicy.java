package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.graph.impl.GraphType;

import java.util.Collection;

/**
 * Defines the behavior of edges in a graph, specifically distinguishing between
 * directed and undirected graphs.
 */
public interface EdgePolicy {
    /**
     * Returns the collection of edge actions required to represent a connection between two vertices.
     * For directed graphs, this is a single edge. For undirected graphs, this typically involves
     * symmetric edges.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @param <V>  the type of the vertices
     * @return a collection of {@link EdgeAction}s
     */
    <V> Collection<EdgeAction<V>> edgePairs(V from, V to);

    /**
     * Normalizes the internal edge count based on the policy.
     * For example, undirected graphs might store two internal edges for one logical edge.
     *
     * @param internalEdgeCount the raw count of edges in the storage
     * @return the logical edge count
     */
    int normalizeEdgeCount(int internalEdgeCount);

    /**
     * Returns the graph type associated with this policy.
     *
     * @return the {@link GraphType}
     */
    GraphType name();

    /**
     * Represents a single directed edge operation.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @param <V>  the type of the vertices
     */
    record EdgeAction<V>(V from, V to) {}
}
