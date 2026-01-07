package br.com.dbarreto.datastructure.graph.policy;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.Collection;
import java.util.List;

/**
 * Policy for directed graphs.
 * <p>
 * Edges are one-way: from source to target.
 * </p>
 */
public final class DirectedEdgePolicy implements EdgePolicy {

    /**
     * Returns a single pair representing the directed edge from source to target.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @param <V>  the type of the vertices
     * @return a list containing a single {@link SimplePair} (from, to)
     */
    @Override
    public <V> Collection<Pair<V, V>> edgePairs(V from, V to) {
        return List.of(new SimplePair<>(from, to));
    }

    /**
     * Returns the internal edge count as is, since each internal edge corresponds to one logical edge.
     *
     * @param internalEdgeCount the raw count of edges in the storage
     * @return the internal edge count
     */
    @Override
    public int normalizeEdgeCount(int internalEdgeCount) {
        return internalEdgeCount;
    }

    /**
     * Always returns true, as every internal edge in a directed graph is a logical edge.
     *
     * @param from The origin vertex
     * @param to   The destination vertex
     * @param <V>  The vertex type
     * @return {@code true}
     */
    @Override
    public <V> boolean isLogicalEdge(V from, V to) {
        return true;
    }
}
