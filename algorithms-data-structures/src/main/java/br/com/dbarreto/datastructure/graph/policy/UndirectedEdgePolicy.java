package br.com.dbarreto.datastructure.graph.policy;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.Collection;
import java.util.List;

/**
 * Policy for undirected graphs.
 * Edges are bidirectional: an edge from A to B implies an edge from B to A.
 * Internally, this is often represented by two directed edges.
 */
public final class UndirectedEdgePolicy implements EdgePolicy {

    @Override
    public <V> Collection<Pair<V, V>> edgePairs(V from, V to) {
        return List.of(
                new SimplePair<>(from, to),
                new SimplePair<>(to, from)
        );
    }

    @Override
    public int normalizeEdgeCount(int internalEdgeCount) {
        return internalEdgeCount / 2;
    }

    /**
     * This implementation chooses as logical edge, the one
     * where 'from' vertex has a smaller identify hash code.
     * @param from The origin vertex
     * @param to The destination vertex
     * @return true, if the 'from' vertex has smaller identity hash code then 'to' vertex
     * @param <V> The type of the vertices
     */
    @Override
    public <V> boolean isLogicalEdge(V from, V to) {
        return System.identityHashCode(from) < System.identityHashCode(to);
    }
}
