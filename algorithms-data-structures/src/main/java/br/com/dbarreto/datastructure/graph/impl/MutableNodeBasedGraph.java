package br.com.dbarreto.datastructure.graph.impl;

import java.util.*;
import java.util.stream.Collectors;

import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.node.GraphNode;
import br.com.dbarreto.datastructure.node.impl.SimpleGraphNode;

public class MutableNodeBasedGraph<T> implements MutableGraph<T> {
    private final Map<T, GraphNode<T>> vertices;
    private Integer edgeCount;

    public MutableNodeBasedGraph() {
        this.vertices = new HashMap<>();
        this.edgeCount = 0;
    }

    @Override
    public boolean containsVertex(T vertex) {
        return this.vertices.containsKey(vertex);
    }

    @Override
    public boolean hasEdge(T from, T to) {
        return this.vertices.containsKey(from) && this.vertices.containsKey(to)
                && this.vertices.get(from).neighbors().contains(this.vertices.get(to));
    }

    @Override
    public Collection<T> vertices() {
        return this.vertices.keySet();
    }

    @Override
    public Collection<T> neighborsOf(T vertex) {
        if (!this.vertices.containsKey(vertex)) {
            return Set.of();
        }
        return this.vertices.get(vertex).neighbors().stream()
                .map(GraphNode::value).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public int vertexCount() {
        return this.vertices.size();
    }

    @Override
    public int edgeCount() {
        return this.edgeCount;
    }

    @Override
    public void addVertex(T v) {
        this.vertices.putIfAbsent(v, new SimpleGraphNode<>(v));
    }

    @Override
    public void addEdge(T from, T to) {
        addVertex(from);
        addVertex(to);

        if (!hasEdge(from, to)) {
            this.vertices.get(from).addNeighbor(this.vertices.get(to));
            this.edgeCount++;
        }
    }

    @Override
    public void removeEdge(T from, T to) {
        if (this.vertices.containsKey(from) && hasEdge(from, to)) {
            this.vertices.get(from).removeNeighbor(this.vertices.get(to));
            this.edgeCount--;
        }
    }

    @Override
    public void removeVertex(T v) {
        if (this.vertices.containsKey(v)) {
            // Count edges to remove
            var vertexToRemove = this.vertices.get(v);
            int edgesToRemove = vertexToRemove.neighbors().size();
            
            // Remove this vertex from all other vertices' neighbor lists
            for (GraphNode<T> vertex : this.vertices.values()) {
                if (vertex.neighbors().contains(vertexToRemove)) {
                    vertex.removeNeighbor(vertexToRemove);
                    edgesToRemove++;
                }
            }
            
            this.vertices.remove(v);
            this.edgeCount -= edgesToRemove;
        }
    }
}
