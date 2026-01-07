package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.GraphType;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.tuple.Pair;

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

    private final GraphType graphType;

    protected AbstractGraph(GraphType graphType) {
        this.graphType = graphType;
    }

    @Override
    public boolean addEdge(V from, V to, double weight) {
        if (weight == 0.0) {
            throw new IllegalArgumentException("Edge weight cannot be 0");
        }
        boolean modified = false;
        for (Pair<V, V> edge : this.graphType.policy().edgePairs(from, to)) {
            if (addEdgeInternal(edge.first(), edge.second(), weight)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void removeEdge(V from, V to) {
        for (Pair<V, V> edge : this.graphType.policy().edgePairs(from, to)) {
            removeEdgeInternal(edge.first(), edge.second());
        }
    }

    @Override
    public int edgeCount() {
        return this.graphType.policy().normalizeEdgeCount(edgeCountInternal());
    }

    @Override
    public Collection<Edge<V>> logicalEdges() {
        return edges(true);
    }

    @Override
    public Collection<Edge<V>> physicalEdges() {
        return edges(false);
    }

    private Collection<Edge<V>> edges(boolean logical) {
        List<Edge<V>> edges = new ArrayList<>();
        for (V from : vertices()) {
            for (V to : neighborsOf(from)) {
                if (logical && this.graphType.policy().isLogicalEdge(from, to)) {
                    continue;
                }
                edges.add(new Edge<>(from, to, weight(from, to)));
            }
        }
        return edges;
    }

    @Override
    public GraphType type() {
        return this.graphType;
    }

    protected abstract boolean addEdgeInternal(V from, V to, double weight);
    protected abstract void removeEdgeInternal(V from, V to);
    protected abstract int edgeCountInternal();
}
