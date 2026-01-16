package br.com.dbarreto.datastructure.graph.impl;

import java.util.*;

import br.com.dbarreto.datastructure.graph.GraphType;

/**
 * Implementation of a graph using an adjacency list.
 * <p>
 * This implementation uses a {@link Map} where keys are vertices and values are {@link Map}s
 * of adjacent vertices and their weights. It is efficient for sparse graphs.
 * </p>
 * <p>
 * Space Complexity: O(V + E)
 * </p>
 *
 * @param <V> the type of the vertices
 */
public class AdjacencyListGraph<V> extends AbstractGraph<V> {

    private final Map<V, Map<V, Double>> adjacencyList;
    private Integer edgeCount;

    /**
     * Creates a directed adjacency list graph.
     */
    public AdjacencyListGraph() {
        this(GraphType.DIRECTED);
    }

    /**
     * Creates an adjacency list graph with the specified edge policy.
     *
     * @param graphType the policy determining if the graph is directed or undirected
     */
    public AdjacencyListGraph(GraphType graphType) {
        super(graphType);
        this.adjacencyList = new HashMap<>();
        this.edgeCount = 0;
    }

    @Override
    public void addVertex(V v) {
        this.adjacencyList.computeIfAbsent(v, k -> new HashMap<>());
    }

    @Override
    public boolean addEdgeInternal(V from, V to, double weight) {
        addVertex(from);
        addVertex(to);

        if (this.adjacencyList.get(from).putIfAbsent(to, weight) == null) {
            this.edgeCount++;
            return true;
        }
        return false;
    }

    @Override
    public OptionalDouble weight(V from, V to) {
        if (this.adjacencyList.containsKey(from) && this.adjacencyList.get(from).containsKey(to)) {
            return OptionalDouble.of(this.adjacencyList.get(from).get(to));
        }
        return OptionalDouble.empty();
    }

    @Override
    public boolean hasEdge(V from, V to) {
        return this.adjacencyList.containsKey(from) && this.adjacencyList.get(from).containsKey(to);
    }

    @Override
    public Collection<V> neighborsOf(V vertex) {
        return this.adjacencyList.getOrDefault(vertex, Map.of()).keySet();
    }

    @Override
    public boolean containsVertex(V vertex) {
        return this.adjacencyList.containsKey(vertex);
    }

    @Override
    public Collection<V> vertices() {
        return Collections.unmodifiableSet(this.adjacencyList.keySet());
    }

    @Override
    public Collection<Edge<V>> edges(boolean isLogical) {
        List<Edge<V>> edges = new ArrayList<>();
        for (Map.Entry<V, Map<V, Double>> entry : this.adjacencyList.entrySet()) {
            V from = entry.getKey();
            for (Map.Entry<V, Double> neighborEntry : entry.getValue().entrySet()) {
                V to = neighborEntry.getKey();
                if (isLogical && !type().policy().isLogicalEdge(from, to)) {
                    continue;
                }
                Double weight = neighborEntry.getValue();
                edges.add(new Edge<>(from, to, weight));
            }
        }
        return edges;
    }

    @Override
    public void removeEdgeInternal(V from, V to) {
        if (this.adjacencyList.containsKey(from) && this.adjacencyList.get(from).remove(to) != null) {
            this.edgeCount--;
        }
    }

    @Override
    public void removeVertex(V v) {
        if (this.adjacencyList.containsKey(v)) {
            // Remove all edges from this vertex
            this.edgeCount -= this.adjacencyList.get(v).size();
            this.adjacencyList.remove(v);
            
            // Remove all edges to this vertex from other vertices
            for (Map<V, Double> neighbors : this.adjacencyList.values()) {
                if (neighbors.remove(v) != null) {
                    this.edgeCount--;
                }
            }
        }
    }

    @Override
    public int vertexCount() {
        return this.adjacencyList.size();
    }

    @Override
    public int edgeCountInternal() {
        return this.edgeCount;
    }
}
