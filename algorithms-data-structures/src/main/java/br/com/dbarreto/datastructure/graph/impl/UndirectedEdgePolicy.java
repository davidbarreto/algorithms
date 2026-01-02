package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;

import java.util.Collection;
import java.util.List;

/**
 * Policy for undirected graphs.
 * Edges are bidirectional: an edge from A to B implies an edge from B to A.
 * Internally, this is often represented by two directed edges.
 */
public final class UndirectedEdgePolicy implements EdgePolicy {

    @Override
    public <V> Collection<EdgeAction<V>> edgePairs(V from, V to) {
        return List.of(
                new EdgeAction<>(from, to),
                new EdgeAction<>(to, from)
        );
    }

    @Override
    public int normalizeEdgeCount(int internalEdgeCount) {
        return internalEdgeCount / 2;
    }

    @Override
    public GraphType name() {
        return GraphType.UNDIRECTED;
    }
}
