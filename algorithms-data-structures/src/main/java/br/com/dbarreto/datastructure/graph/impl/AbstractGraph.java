package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.graph.MutableGraph;

/**
 * Skeletal implementation of the {@link MutableGraph} interface to minimize the effort
 * required to implement this interface.
 * Handles edge policies (directed vs undirected) and delegates storage operations to subclasses.
 *
 * @param <V> the type of the vertices
 */
public abstract class AbstractGraph<V> implements MutableGraph<V> {

    private final EdgePolicy edgePolicy;

    protected AbstractGraph(EdgePolicy edgePolicy) {
        this.edgePolicy = edgePolicy;
    }

    @Override
    public void addEdge(V from, V to, double weight) {
        if (weight == 0.0) {
            throw new IllegalArgumentException("Edge weight cannot be 0");
        }
        for (EdgePolicy.EdgeAction<V> edge : this.edgePolicy.edgePairs(from, to)) {
            addEdgeInternal(edge.from(), edge.to(), weight);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        for (EdgePolicy.EdgeAction<V> edge : this.edgePolicy.edgePairs(from, to)) {
            removeEdgeInternal(edge.from(), edge.to());
        }
    }

    @Override
    public int edgeCount() {
        return this.edgePolicy.normalizeEdgeCount(edgeCountInternal());
    }

    @Override
    public GraphType getType() {
        return this.edgePolicy.name();
    }

    protected abstract void addEdgeInternal(V from, V to, double weight);
    protected abstract void removeEdgeInternal(V from, V to);
    protected abstract int edgeCountInternal();
}
