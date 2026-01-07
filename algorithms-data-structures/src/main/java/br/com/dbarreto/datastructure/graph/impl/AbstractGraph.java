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
 * <p>
 * Handles edge policies (directed vs undirected) and delegates storage operations to subclasses.
 * </p>
 *
 * @param <V> the type of the vertices
 */
public abstract class AbstractGraph<V> implements MutableGraph<V> {

    private final GraphType graphType;

    /**
     * Constructs an {@code AbstractGraph} with the specified graph type.
     *
     * @param graphType the type of the graph (Directed or Undirected)
     */
    protected AbstractGraph(GraphType graphType) {
        this.graphType = graphType;
    }

    /**
     * Adds an edge between two vertices with a specific weight.
     * <p>
     * Delegates to the {@link br.com.dbarreto.datastructure.graph.EdgePolicy} to determine
     * the actual edges to add (e.g., adding a reverse edge for undirected graphs).
     * </p>
     *
     * @param from   the source vertex
     * @param to     the target vertex
     * @param weight the weight of the edge
     * @return {@code true} if the graph was modified as a result of this operation
     * @throws IllegalArgumentException if the weight is 0
     */
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

    /**
     * Removes the edge between two vertices.
     * <p>
     * Delegates to the {@link br.com.dbarreto.datastructure.graph.EdgePolicy} to determine
     * the actual edges to remove.
     * </p>
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    @Override
    public void removeEdge(V from, V to) {
        for (Pair<V, V> edge : this.graphType.policy().edgePairs(from, to)) {
            removeEdgeInternal(edge.first(), edge.second());
        }
    }

    /**
     * Returns the number of edges in the graph.
     * <p>
     * The count is normalized based on the graph type (e.g., undirected edges count as one).
     * </p>
     *
     * @return the edge count
     */
    @Override
    public int edgeCount() {
        return this.graphType.policy().normalizeEdgeCount(edgeCountInternal());
    }

    /**
     * Returns a collection of all logical edges in the graph.
     *
     * @return a collection of edges
     */
    @Override
    public Collection<Edge<V>> logicalEdges() {
        return edges(true);
    }

    /**
     * Returns a collection of all physical edges in the graph.
     *
     * @return a collection of edges
     */
    @Override
    public Collection<Edge<V>> physicalEdges() {
        return edges(false);
    }

    private Collection<Edge<V>> edges(boolean logical) {
        List<Edge<V>> edges = new ArrayList<>();
        for (V from : vertices()) {
            for (V to : neighborsOf(from)) {
                if (logical && !this.graphType.policy().isLogicalEdge(from, to)) {
                    continue;
                }

                weight(from, to).ifPresent(w -> edges.add(new Edge<>(from, to, w)));
            }
        }
        return edges;
    }

    /**
     * Returns the type of the graph.
     *
     * @return the {@link GraphType}
     */
    @Override
    public GraphType type() {
        return this.graphType;
    }

    /**
     * Internal method to add a single directed edge.
     *
     * @param from   the source vertex
     * @param to     the target vertex
     * @param weight the weight of the edge
     * @return {@code true} if the edge was added
     */
    protected abstract boolean addEdgeInternal(V from, V to, double weight);

    /**
     * Internal method to remove a single directed edge.
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    protected abstract void removeEdgeInternal(V from, V to);

    /**
     * Internal method to count the total number of directed edges stored.
     *
     * @return the internal edge count
     */
    protected abstract int edgeCountInternal();
}
