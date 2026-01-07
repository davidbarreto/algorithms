package br.com.dbarreto.datastructure.graph.policy;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.Collection;
import java.util.List;

/**
 * Policy for undirected graphs.
 * <p>
 * Edges are bidirectional: an edge from A to B implies an edge from B to A.
 * Internally, this is often represented by two directed edges.
 * </p>
 */
public final class UndirectedEdgePolicy implements EdgePolicy {

    /**
     * Returns two pairs representing the bidirectional edge: (from, to) and (to, from).
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @param <V>  the type of the vertices
     * @return a list containing two {@link SimplePair}s: (from, to) and (to, from)
     */
    @Override
    public <V> Collection<Pair<V, V>> edgePairs(V from, V to) {
        return List.of(
                new SimplePair<>(from, to),
                new SimplePair<>(to, from)
        );
    }

    /**
     * Returns half the internal edge count, as each logical edge is represented by two internal edges.
     *
     * @param internalEdgeCount the raw count of edges in the storage
     * @return the internal edge count divided by 2
     */
    @Override
    public int normalizeEdgeCount(int internalEdgeCount) {
        return internalEdgeCount / 2;
    }

    /**
     * Determines if the edge (from -> to) is the canonical representation of the undirected edge.
     * <p>
     * This implementation chooses as logical edge, the one
     * where 'from' vertex has a smaller identity hash code.
     * </p>
     *
     * @param from The origin vertex
     * @param to   The destination vertex
     * @param <V>  The type of the vertices
     * @return {@code true}, if the 'from' vertex has smaller identity hash code then 'to' vertex
     */
    @Override
    public <V> boolean isLogicalEdge(V from, V to) {
        return System.identityHashCode(from) < System.identityHashCode(to);
    }
}
