package br.com.dbarreto.datastructure.graph.policy;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.Collection;
import java.util.List;

/**
 * Policy for directed graphs.
 * Edges are one-way: from source to target.
 */
public final class DirectedEdgePolicy implements EdgePolicy {

    @Override
    public <V> Collection<Pair<V, V>> edgePairs(V from, V to) {
        return List.of(new SimplePair<>(from, to));
    }

    @Override
    public int normalizeEdgeCount(int internalEdgeCount) {
        return internalEdgeCount;
    }

    @Override
    public <V> boolean isLogicalEdge(V from, V to) {
        return true;
    }
}
