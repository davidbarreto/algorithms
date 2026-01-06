package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.graph.MutableGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Skeletal implementation of the {@link MutableGraph} interface to minimize the effort
 * required to implement this interface.
 * Handles edge policies (directed vs undirected) and delegates storage operations to subclasses.
 *
 * @param <V> the type of the vertices
 */
public abstract class AbstractGraph<V> implements MutableGraph<V> {

    public static final EdgePolicy DIRECTED_GRAPH = new DirectedEdgePolicy();
    public static final EdgePolicy UNDIRECTED_GRAPH = new UndirectedEdgePolicy();
    private final EdgePolicy edgePolicy;

    protected AbstractGraph(EdgePolicy edgePolicy) {
        this.edgePolicy = edgePolicy;
    }

    @Override
    public boolean addEdge(V from, V to, double weight) {
        if (weight == 0.0) {
            throw new IllegalArgumentException("Edge weight cannot be 0");
        }
        boolean modified = false;
        for (EdgePolicy.EdgeAction<V> edge : this.edgePolicy.edgePairs(from, to)) {
            if (addEdgeInternal(edge.from(), edge.to(), weight)) {
                modified = true;
            }
        }
        return modified;
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
    public Collection<Edge<V>> edges() {
        List<Edge<V>> edges = new ArrayList<>();
        for (V from : vertices()) {
            for (V to : neighborsOf(from)) {
                edges.add(new Edge<>(from, to, weight(from, to)));
            }
        }
        return edges;
    }

    @Override
    public GraphType getType() {
        return this.edgePolicy.name();
    }

    protected abstract boolean addEdgeInternal(V from, V to, double weight);
    protected abstract void removeEdgeInternal(V from, V to);
    protected abstract int edgeCountInternal();
}
