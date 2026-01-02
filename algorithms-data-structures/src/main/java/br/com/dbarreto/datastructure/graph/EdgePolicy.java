package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.graph.impl.GraphType;

import java.util.Collection;

public interface EdgePolicy {
    <V> Collection<EdgeAction<V>> edgePairs(V from, V to);
    int normalizeEdgeCount(int internalEdgeCount);

    GraphType name();
    record EdgeAction<V>(V from, V to) {}
}


