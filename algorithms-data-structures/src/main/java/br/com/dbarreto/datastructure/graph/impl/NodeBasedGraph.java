package br.com.dbarreto.datastructure.graph.impl;

import java.util.*;
import java.util.stream.Collectors;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.node.GraphNode;
import br.com.dbarreto.datastructure.node.impl.SimpleGraphNode;

/**
 * Implementation of a graph using an object-oriented node structure.
 * <p>
 * Each vertex is wrapped in a {@link GraphNode} which maintains a list of its neighbors.
 * This is similar to an adjacency list but uses explicit node objects.
 * </p>
 *
 * @param <V> the type of the vertices
 */
public class NodeBasedGraph<V> extends AbstractGraph<V> {
    private final Map<V, GraphNode<V>> vertices;
    private Integer edgeCount;

    /**
     * Creates a directed node-based graph.
     */
    public NodeBasedGraph() {
        this(DIRECTED_GRAPH);
    }

    /**
     * Creates a node-based graph with the specified edge policy.
     *
     * @param edgePolicy the policy determining if the graph is directed or undirected
     */
    public NodeBasedGraph(EdgePolicy edgePolicy) {
        super(edgePolicy);
        this.vertices = new HashMap<>();
        this.edgeCount = 0;
    }

    @Override
    public boolean containsVertex(V vertex) {
        return this.vertices.containsKey(vertex);
    }

    @Override
    public boolean hasEdge(V from, V to) {
        return this.vertices.containsKey(from) && this.vertices.containsKey(to)
                && this.vertices.get(from).neighbors().containsKey(this.vertices.get(to));
    }

    @Override
    public Collection<V> vertices() {
        return this.vertices.keySet();
    }

    @Override
    public Collection<V> neighborsOf(V vertex) {
        if (!this.vertices.containsKey(vertex)) {
            return Set.of();
        }
        return this.vertices.get(vertex).neighbors().keySet().stream()
                .map(GraphNode::value).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public int vertexCount() {
        return this.vertices.size();
    }

    @Override
    public int edgeCountInternal() {
        return this.edgeCount;
    }

    @Override
    public void addVertex(V v) {
        this.vertices.putIfAbsent(v, new SimpleGraphNode<>(v));
    }

    @Override
    public void addEdgeInternal(V from, V to, double weight) {
        addVertex(from);
        addVertex(to);

        if (!hasEdge(from, to)) {
            this.vertices.get(from).addNeighbor(this.vertices.get(to), weight);
            this.edgeCount++;
        }
    }

    @Override
    public double weight(V from, V to) {
        if (this.vertices.containsKey(from) && this.vertices.containsKey(to)) {
            return this.vertices.get(from).neighbors().get(this.vertices.get(to));
        }
        return Double.NaN;
    }

    @Override
    public void removeEdgeInternal(V from, V to) {
        if (this.vertices.containsKey(from) && hasEdge(from, to)) {
            this.vertices.get(from).removeNeighbor(this.vertices.get(to));
            this.edgeCount--;
        }
    }

    @Override
    public void removeVertex(V v) {
        if (this.vertices.containsKey(v)) {
            // Count edges to remove
            var vertexToRemove = this.vertices.get(v);
            int edgesToRemove = vertexToRemove.neighbors().size();
            
            // Remove this vertex from all other vertices' neighbor lists
            for (GraphNode<V> vertex : this.vertices.values()) {
                if (vertex.neighbors().containsKey(vertexToRemove)) {
                    vertex.removeNeighbor(vertexToRemove);
                    edgesToRemove++;
                }
            }
            
            this.vertices.remove(v);
            this.edgeCount -= edgesToRemove;
        }
    }
}
