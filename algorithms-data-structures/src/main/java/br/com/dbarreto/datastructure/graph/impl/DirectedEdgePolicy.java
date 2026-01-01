package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;

import java.util.Collection;
import java.util.List;

public final class DirectedEdgePolicy implements EdgePolicy {

    @Override
    public <V> Collection<EdgeAction<V>> edgePairs(V from, V to) {
        return List.of(new EdgeAction<>(from, to));
    }

    @Override
    public int normalizeEdgeCount(int internalEdgeCount) {
        return internalEdgeCount;
    }
}
