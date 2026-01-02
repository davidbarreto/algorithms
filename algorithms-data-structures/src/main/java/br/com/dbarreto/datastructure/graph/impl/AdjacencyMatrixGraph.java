package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.EdgePolicy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of a graph using an adjacency matrix.
 * <p>
 * This implementation uses a 2D boolean array to represent edges.
 * It is efficient for dense graphs and checking edge existence (O(1)),
 * but requires O(V^2) space.
 * </p>
 *
 * @param <V> the type of the vertices
 */
public class AdjacencyMatrixGraph<V> extends AbstractGraph<V> {

    private final boolean[][] adjacencyMatrix;
    private final Map<V, Integer> vertexIndexMapping;
    private final Map<Integer, V> indexVertexMapping;
    private Integer vertexCount;
    private Integer edgeCount;

    /**
     * Creates a directed adjacency matrix graph with the specified vertex capacity.
     *
     * @param numVertexes the maximum number of vertices this graph can hold
     */
    public AdjacencyMatrixGraph(int numVertexes) {
        this(numVertexes, DIRECTED_GRAPH);
    }

    /**
     * Creates an adjacency matrix graph with the specified capacity and edge policy.
     *
     * @param numVertexes the maximum number of vertices
     * @param edgePolicy  the policy determining if the graph is directed or undirected
     */
    public AdjacencyMatrixGraph(int numVertexes, EdgePolicy edgePolicy) {
        super(edgePolicy);
        this.adjacencyMatrix = new boolean[numVertexes][numVertexes];
        this.vertexIndexMapping = new HashMap<>();
        this.indexVertexMapping = new HashMap<>();
        this.vertexCount = 0;
        this.edgeCount = 0;
    }

    @Override
    public void addVertex(V vertex) {
        if (!this.vertexIndexMapping.containsKey(vertex)) {
            if (this.vertexCount >= this.adjacencyMatrix.length) {
                throw new IllegalArgumentException("Graph capacity exceeded: " + this.adjacencyMatrix.length);
            }
            int index = this.vertexCount++;
            this.vertexIndexMapping.put(vertex, index);
            this.indexVertexMapping.put(index, vertex);
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

    /**
     * Removes a vertex and all associated edges.
     * <p>
     * To maintain contiguous indices in the matrix, the vertex to be removed is swapped
     * with the last vertex in the graph. This avoids "holes" in the adjacency matrix indices.
     */
    @Override
    public void removeVertex(V v) {
        var indexToRemove = this.vertexIndexMapping.get(v);
        if (indexToRemove != null) {
            removeEdgesOf(indexToRemove);

            this.vertexIndexMapping.remove(v);
            this.indexVertexMapping.remove(indexToRemove);

            swapWithLastVertex(indexToRemove);

            this.vertexCount--;
        }
    }

    private void removeEdgesOf(int indexToRemove) {
        // Count and remove outgoing edges
        for (int i = 0; i < this.vertexCount; i++) {
            if (this.adjacencyMatrix[indexToRemove][i]) {
                this.adjacencyMatrix[indexToRemove][i] = false;
                this.edgeCount--;
            }
        }

        // Count and remove incoming edges
        for (int i = 0; i < this.vertexCount; i++) {
            if (this.adjacencyMatrix[i][indexToRemove]) {
                this.adjacencyMatrix[i][indexToRemove] = false;
                this.edgeCount--;
            }
        }
    }

    private void swapWithLastVertex(int indexToRemove) {
        // Swap with the last vertex to keep indices contiguous
        int lastIndex = this.vertexCount - 1;
        if (indexToRemove < lastIndex) {
            V lastVertex = this.indexVertexMapping.get(lastIndex);
            boolean diagonal = this.adjacencyMatrix[lastIndex][lastIndex];

            // Move row
            System.arraycopy(this.adjacencyMatrix[lastIndex], 0, this.adjacencyMatrix[indexToRemove], 0, this.vertexCount);
            Arrays.fill(this.adjacencyMatrix[lastIndex], 0, this.vertexCount, false);

            // Move col
            for (int i = 0; i < this.vertexCount; i++) {
                this.adjacencyMatrix[i][indexToRemove] = this.adjacencyMatrix[i][lastIndex];
                this.adjacencyMatrix[i][lastIndex] = false;
            }
            this.adjacencyMatrix[indexToRemove][indexToRemove] = diagonal;

            this.vertexIndexMapping.put(lastVertex, indexToRemove);
            this.indexVertexMapping.put(indexToRemove, lastVertex);
            this.indexVertexMapping.remove(lastIndex);
        }
    }
}
