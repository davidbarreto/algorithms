package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjacencyMatrixGraph<V> extends AbstractGraph<V> {

    private final boolean[][] adjacencyMatrix;
    private final Map<V, Integer> vertexIndexMapping;
    private Integer vertexCount;
    private Integer edgeCount;

    public AdjacencyMatrixGraph(int numVertexes) {
        this(numVertexes, DIRECTED_GRAPH);
    }

    public AdjacencyMatrixGraph(int numVertexes, EdgePolicy edgePolicy) {
        super(edgePolicy);
        this.adjacencyMatrix = new boolean[numVertexes][numVertexes];
        this.vertexIndexMapping = new HashMap<>();
        this.vertexCount = 0;
        this.edgeCount = 0;
    }

    @Override
    public void addVertex(V vertex) {
        if (!this.vertexIndexMapping.containsKey(vertex)) {
            if (this.vertexCount >= this.adjacencyMatrix.length) {
                throw new IllegalArgumentException("Graph capacity exceeded: " + this.adjacencyMatrix.length);
            }
            this.vertexIndexMapping.put(vertex, this.vertexCount++);
        }
    }

    @Override
    public void addEdgeInternal(V from, V to) {
        addVertex(from);
        addVertex(to);

        var indexFrom = this.vertexIndexMapping.get(from);
        var indexTo = this.vertexIndexMapping.get(to);

        if (!this.adjacencyMatrix[indexFrom][indexTo]) {
            this.adjacencyMatrix[indexFrom][indexTo] = true;
            edgeCount++;
        }

    }

    @Override
    public boolean hasEdge(V from, V to) {
        var indexFrom = this.vertexIndexMapping.get(from);
        var indexTo = this.vertexIndexMapping.get(to);

        return indexFrom != null && indexTo != null && this.adjacencyMatrix[indexFrom][indexTo];
    }

    @Override
    public Collection<V> vertices() {
        return Collections.unmodifiableSet(this.vertexIndexMapping.keySet());
    }

    public Collection<V> neighborsOf(V vertex) {
        var i = this.vertexIndexMapping.get(vertex);
        if (i == null) {
            return Set.of();
        }

        Set<V> result = new HashSet<>();
        for (var e : this.vertexIndexMapping.entrySet()) {
            if (this.adjacencyMatrix[i][e.getValue()]) {
                result.add(e.getKey());
            }
        }

        return result;
    }

    @Override
    public boolean containsVertex(V vertex) {
        return this.vertexIndexMapping.containsKey(vertex);
    }

    @Override
    public int vertexCount() {
        return this.vertexCount;
    }

    @Override
    public int edgeCountInternal() {
        return this.edgeCount;
    }

    @Override
    public void removeEdgeInternal(V from, V to) {
        var indexFrom = this.vertexIndexMapping.get(from);
        var indexTo = this.vertexIndexMapping.get(to);

        if (indexFrom != null && indexTo != null && this.adjacencyMatrix[indexFrom][indexTo]) {
            this.adjacencyMatrix[indexFrom][indexTo] = false;
            this.edgeCount--;
        }
    }

    @Override
    public void removeVertex(V v) {
        var index = this.vertexIndexMapping.get(v);
        if (index != null) {
            // Count and remove outgoing edges
            for (int i = 0; i < this.adjacencyMatrix.length; i++) {
                if (this.adjacencyMatrix[index][i]) {
                    this.adjacencyMatrix[index][i] = false;
                    this.edgeCount--;
                }
            }
            
            // Count and remove incoming edges
            for (int i = 0; i < this.adjacencyMatrix.length; i++) {
                if (this.adjacencyMatrix[i][index]) {
                    this.adjacencyMatrix[i][index] = false;
                    this.edgeCount--;
                }
            }
            
            this.vertexIndexMapping.remove(v);
            this.vertexCount--;
        }
    }
}
