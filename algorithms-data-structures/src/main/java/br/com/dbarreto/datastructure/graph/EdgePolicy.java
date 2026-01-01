package br.com.dbarreto.datastructure.graph;

import java.util.Collection;

public interface EdgePolicy {
    <V> Collection<EdgeAction<V>> edgePairs(V from, V to);
    int normalizeEdgeCount(int internalEdgeCount);
    record EdgeAction<V>(V from, V to) {}
}


