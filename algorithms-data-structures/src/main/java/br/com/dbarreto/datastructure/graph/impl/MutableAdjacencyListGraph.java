package br.com.dbarreto.datastructure.graph.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.dbarreto.datastructure.graph.MutableGraph;

public class MutableAdjacencyListGraph<V> implements MutableGraph<V> {

    private final Map<V, Set<V>> adjacencyList;
    private Integer edgeCount;

    public MutableAdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
        this.edgeCount = 0;
    }

    @Override
    public void addVertex(V v) {
        this.adjacencyList.computeIfAbsent(v, k -> new HashSet<>());
    }

    @Override
    public void addEdge(V from, V to) {
        addVertex(from);
        addVertex(to);

        if (this.adjacencyList.get(from).add(to)) {
            this.edgeCount++;
        }
    }

    @Override
    public boolean hasEdge(V from, V to) {
        return this.adjacencyList.containsKey(from) && this.adjacencyList.get(from).contains(to);
    }

    @Override
    public Collection<V> neighborsOf(V vertex) {
        return Collections.unmodifiableSet(
            this.adjacencyList.getOrDefault(vertex, Set.of())
        );
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
    public void removeEdge(V from, V to) {
        if (this.adjacencyList.containsKey(from) && this.adjacencyList.get(from).remove(to)) {
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
            for (Set<V> neighbors : this.adjacencyList.values()) {
                if (neighbors.remove(v)) {
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
    public int edgeCount() {
        return this.edgeCount;
    }
}
