package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.GraphType;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An abstract base class for {@link MutableGraph} implementations, providing common functionality
 * for handling different edge policies (directed vs. undirected) and delegating storage-specific
 * operations to subclasses.
 * <p>
 * This class simplifies the creation of new graph implementations by managing the logic
 * for adding, removing, and counting edges based on the specified {@link GraphType}. Subclasses
 * are responsible for implementing the actual storage and retrieval of vertices and edges.
 *
 * @param <V> the type of the vertices in the graph
 */
public abstract class AbstractGraph<V> implements MutableGraph<V> {

    private final GraphType graphType;

    /**
     * Constructs an {@code AbstractGraph} with the specified graph type.
     *
     * @param graphType the type of the graph (e.g., {@link GraphType#DIRECTED} or {@link GraphType#UNDIRECTED})
     */
    protected AbstractGraph(GraphType graphType) {
        this.graphType = graphType;
    }

    /**
     * Adds an edge between two vertices with a specified weight.
     * <p>
     * The behavior of this method is determined by the {@link br.com.dbarreto.datastructure.graph.EdgePolicy}
     * associated with the graph's type. For an undirected graph, this may result in adding
     * two internal edges (one in each direction), whereas for a directed graph, only a single
     * edge is added.
     *
     * @param from   the source vertex of the edge
     * @param to     the target vertex of the edge
     * @param weight the weight of the edge (must be non-zero)
     * @return {@code true} if the graph was modified as a result of this operation, {@code false} otherwise
     * @throws IllegalArgumentException if the specified weight is 0
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
     * Removes the edge between two specified vertices.
     * <p>
     * The behavior of this method is determined by the {@link br.com.dbarreto.datastructure.graph.EdgePolicy}
     * of the graph. For an undirected graph, this may involve removing edges in both directions.
     *
     * @param from the source vertex of the edge to be removed
     * @param to   the target vertex of the edge to be removed
     */
    @Override
    public void removeEdge(V from, V to) {
        for (Pair<V, V> edge : this.graphType.policy().edgePairs(from, to)) {
            removeEdgeInternal(edge.first(), edge.second());
        }
    }

    /**
     * Returns the number of logical edges in the graph.
     * <p>
     * The edge count is adjusted based on the graph's {@link br.com.dbarreto.datastructure.graph.EdgePolicy}.
     * For example, in an undirected graph, a pair of reciprocal edges is counted as a single logical edge.
     *
     * @return the total number of logical edges
     */
    @Override
    public int edgeCount() {
        return this.graphType.policy().logicalEdgeCount(edgeCountInternal());
    }

    /**
     * Returns a collection of all logical edges in the graph. A logical edge represents the
     * conceptual connection between vertices as defined by the graph's type. For example, in an
     * undirected graph, the edge (u, v) is the same as (v, u), and only one of them will be
     * returned.
     *
     * @return a {@link Collection} of the logical edges
     */
    @Override
    public Collection<Edge<V>> logicalEdges() {
        return edges(true);
    }

    /**
     * Returns a collection of all physical edges stored in the graph. A physical edge represents
     * a direct, one-way connection from a source vertex to a target vertex. In an undirected
     * graph, a single logical edge is typically represented by two physical edges (one in each
     * direction).
     *
     * @return a {@link Collection} of the physical edges
     */
    @Override
    public Collection<Edge<V>> physicalEdges() {
        return edges(false);
    }

    protected Collection<Edge<V>> edges(boolean logical) {
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
     * Returns the type of the graph, indicating whether it is directed or undirected.
     *
     * @return the {@link GraphType} of the graph
     */
    @Override
    public GraphType type() {
        return this.graphType;
    }

    /**
     * Adds a single directed edge to the graph's underlying storage. Subclasses must implement
     * this method to define how edges are stored.
     *
     * @param from   the source vertex of the edge
     * @param to     the target vertex of the edge
     * @param weight the weight of the edge
     * @return {@code true} if the edge was successfully added, {@code false} otherwise
     */
    protected abstract boolean addEdgeInternal(V from, V to, double weight);

    /**
     * Removes a single directed edge from the graph's underlying storage. Subclasses must
     * implement this method to define how edges are removed.
     *
     * @param from the source vertex of the edge to be removed
     * @param to   the target vertex of the edge to be removed
     */
    protected abstract void removeEdgeInternal(V from, V to);

    /**
     * Counts the total number of physical (directed) edges stored in the graph. Subclasses must
     * implement this method to count the edges in their specific storage implementation.
     *
     * @return the total number of internal edges
     */
    protected abstract int edgeCountInternal();
}
