package br.com.dbarreto.datastructure.graph.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.node.GraphNode;

public class MutableNodeBasedGraph<T> implements MutableGraph<GraphNode<T>> {

    private final Set<GraphNode<T>> vertices;
    private Integer edgeCount;

    public MutableNodeBasedGraph() {
        this.vertices = new HashSet<>();
        this.edgeCount = 0;
    }

    @Override
    public boolean containsVertex(GraphNode<T> vertex) {
        return this.vertices.contains(vertex);
    }

    @Override
    public boolean hasEdge(GraphNode<T> from, GraphNode<T> to) {
        return this.vertices.contains(from) && from.neighbors().contains(to);
    }

    @Override
    public Collection<GraphNode<T>> vertices() {
        return this.vertices;
    }

    @Override
    public Collection<GraphNode<T>> neighborsOf(GraphNode<T> vertex) {
        if (!this.vertices.contains(vertex)) {
            return Set.of();
        }
        return vertex.neighbors();
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
    public void addVertex(GraphNode<T> v) {
        this.vertices.add(v);
    }

    @Override
    public void addEdge(GraphNode<T> from, GraphNode<T> to) {
        if (this.vertices.contains(from)) {
            from.addNeighbor(to);
        }
    }

    @Override
    public void removeEdge(GraphNode<T> from, GraphNode<T> to) {
        if (this.vertices.contains(from)) {
            from.removeNeighbor(to);
            this.edgeCount--;
        }
    }

    @Override
    public void removeVertex(GraphNode<T> v) {
        if (this.vertices.contains(v)) {
            // Count edges to remove
            int edgesToRemove = v.neighbors().size();
            
            // Remove this vertex from all other vertices' neighbor lists
            for (GraphNode<T> vertex : this.vertices) {
                if (vertex.neighbors().contains(v)) {
                    vertex.removeNeighbor(v);
                    edgesToRemove++;
                }
            }
            
            this.vertices.remove(v);
            this.edgeCount -= edgesToRemove;
        }
    }
}
