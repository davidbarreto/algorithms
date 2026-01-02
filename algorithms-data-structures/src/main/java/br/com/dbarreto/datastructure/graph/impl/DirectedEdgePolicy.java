package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;

import java.util.Collection;
import java.util.List;

/**
 * Policy for directed graphs.
 * Edges are one-way: from source to target.
 */
public final class DirectedEdgePolicy implements EdgePolicy {

    @Override
    public <V> Collection<EdgeAction<V>> edgePairs(V from, V to) {
        return List.of(new EdgeAction<>(from, to));
    }

    @Override
    public int normalizeEdgeCount(int internalEdgeCount) {
        return internalEdgeCount;
    }

    @Override
    public GraphType name() {
        return GraphType.DIRECTED;
    }
}
