package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.tuple.Pair;

import java.util.Collection;

/**
 * Defines the behavior of edges in a graph, specifically distinguishing between
 * directed and undirected graphs.
 */
public interface EdgePolicy {
    /**
     * Returns the collection of edge actions required to represent a connection between two vertices.
     * <p>
     * For directed graphs, this is a single edge. For undirected graphs, this typically involves
     * symmetric edges.
     * </p>
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @param <V>  the type of the vertices
     * @return a collection of {@link Pair}s representing the edges to be added/removed
     */
    <V> Collection<Pair<V, V>> edgePairs(V from, V to);

    /**
     * Normalizes the internal edge count based on the policy.
     * <p>
     * For example, undirected graphs might store two internal edges for one logical edge.
     * </p>
     *
     * @param internalEdgeCount the raw count of edges in the storage
     * @return the logical edge count
     */
    int normalizeEdgeCount(int internalEdgeCount);

    /**
     * Determines whether an internal edge (from -> to)
     * represents a logical edge exposed by the graph.
     * <p>
     * For undirected graphs, not all internal edges are logical, so the implementors should choose
     * (A -> B) or (B -> A) as logical, and omit the other.
     * </p>
     *
     * @param from The origin vertex
     * @param to   The destination vertex
     * @param <V>  The vertex type
     * @return {@code true} if (from -> to) is considered as a logical edge, {@code false} otherwise
     */
    <V> boolean isLogicalEdge(V from, V to);
}
