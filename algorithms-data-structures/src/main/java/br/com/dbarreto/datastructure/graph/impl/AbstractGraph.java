package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.graph.MutableGraph;

public abstract class AbstractGraph<V> implements MutableGraph<V> {

    public static final EdgePolicy DIRECTED_GRAPH = new DirectedEdgePolicy();
    public static final EdgePolicy UNDIRECTED_GRAPH = new UndirectedEdgePolicy();
    private final EdgePolicy edgePolicy;

    protected AbstractGraph(EdgePolicy edgePolicy) {
        this.edgePolicy = edgePolicy;
    }

    @Override
    public void addEdge(V from, V to) {
        for (EdgePolicy.EdgeAction<V> edge : edgePolicy.edgePairs(from, to)) {
            addEdgeInternal(edge.from(), edge.to());
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        for (EdgePolicy.EdgeAction<V> edge : edgePolicy.edgePairs(from, to)) {
            removeEdgeInternal(edge.from(), edge.to());
        }
    }

    @Override
    public int edgeCount() {
        return edgePolicy.normalizeEdgeCount(edgeCountInternal());
    }

    protected abstract void addEdgeInternal(V from, V to);
    protected abstract void removeEdgeInternal(V from, V to);
    protected abstract int edgeCountInternal();
}
