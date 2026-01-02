package br.com.dbarreto.datastructure.graph.impl;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import br.com.dbarreto.datastructure.graph.EdgePolicy;

/**
 * Implementation of a graph using an incidence matrix.
 * <p>
 * This implementation uses a 2D short array where rows represent vertices and columns represent edges.
 * Values indicate the relationship: 1 (source), -1 (target), 0 (no connection).
 * </p>
 * <p>
 * This implementation supports dynamic edge reuse. When an edge is removed, its column index is
 * added to a pool of free indices to be reused by subsequent edge additions, preventing
 * the matrix from exhausting its column capacity prematurely.
 * </p>
 * <p>
 * Vertex removal is handled by swapping the removed vertex with the last vertex in the matrix
 * to maintain contiguous row indices.
 * </p>
 * <p>
 * Space Complexity: O(V * E)
 * </p>
 *
 * @param <V> the type of the vertices
 */
public class IncidenceMatrixGraph<V> extends AbstractGraph<V> {

    private final short[][] incidenceMatrix;
    private final Map<V, Integer> vertexIndexMapping;
    private final Map<Integer, V> indexVertexMapping;
    private Integer vertexCount;
    private Integer edgeCount;
    private Integer nextEdgeIndex;
    private final Queue<Integer> freeEdgeIndices;

    /**
     * Creates a directed incidence matrix graph with specified capacities.
     *
     * @param numVertexes the maximum number of vertices
     * @param numEdges    the maximum number of edges
     */
    public IncidenceMatrixGraph(int numVertexes, int numEdges) {
        this(numVertexes, numEdges, DIRECTED_GRAPH);
    }

    /**
     * Creates an incidence matrix graph with specified capacities and edge policy.
     *
     * @param numVertexes the maximum number of vertices
     * @param numEdges    the maximum number of edges
     * @param edgePolicy  the policy determining if the graph is directed or undirected
     */
    public IncidenceMatrixGraph(int numVertexes, int numEdges, EdgePolicy edgePolicy) {
        super(edgePolicy);
        this.incidenceMatrix = new short[numVertexes][numEdges];
        this.vertexIndexMapping = new HashMap<>();
        this.indexVertexMapping = new HashMap<>();
        this.vertexCount = 0;
        this.edgeCount = 0;
        this.nextEdgeIndex = 0;
        this.freeEdgeIndices = new ArrayDeque<>();
    }

    @Override
    public boolean containsVertex(V vertex) {
        return this.vertexIndexMapping.containsKey(vertex);
    }

    @Override
    public boolean hasEdge(V from, V to) {
        if (containsVertex(from) && containsVertex(to)) {
            
            var i = this.vertexIndexMapping.get(from);
            var j = this.vertexIndexMapping.get(to);

            for (int e = 0; e < this.nextEdgeIndex; e++) {
                if (this.incidenceMatrix[i][e] == 1 && this.incidenceMatrix[j][e] == -1) {
                    return true; // each edge has only one target vertex
                }
            }
        }

        return false;
    }

    @Override
    public Collection<V> vertices() {
        return this.vertexIndexMapping.keySet();
    }

    @Override
    public Collection<V> neighborsOf(V vertex) {

        if (!containsVertex(vertex)) {
            return Set.of();
        }
        
        var i = this.vertexIndexMapping.get(vertex);
        Set<V> result = new HashSet<>();
        
        for (int e = 0; e < this.nextEdgeIndex; e++) {
            if (this.incidenceMatrix[i][e] == 1) {  // outgoing edge
                for (int v = 0; v < this.vertexCount; v++) {
                    if (this.incidenceMatrix[v][e] == -1) {  // incoming edge to this vertex
                        result.add(this.indexVertexMapping.get(v));
                        break;  // each edge has only one target vertex
                    }
                }
            }
        }

        return result;
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
    public void addVertex(V v) {
        this.vertexIndexMapping.computeIfAbsent(v, k -> {
            var index = vertexCount++;
            this.indexVertexMapping.put(index, v);
            return index;
        });
    }

    /**
     * Adds an edge internally.
     * <p>
     * Tries to reuse a freed edge index from {@code freeEdgeIndices} if available;
     * otherwise, uses the next available index.
     */
    @Override
    public void addEdgeInternal(V from, V to) {

        addVertex(from);
        addVertex(to);

        var i = this.vertexIndexMapping.get(from);
        var j = this.vertexIndexMapping.get(to);

        if (hasEdge(from, to)) {
            return;
        }

        if (this.freeEdgeIndices.isEmpty() && this.nextEdgeIndex >= this.incidenceMatrix[0].length) {
            throw new IllegalStateException("Maximum number of edges exceeded");
        }

        int edge;
        if (this.freeEdgeIndices.isEmpty()) {
            edge = this.nextEdgeIndex++;
        } else {
            edge = this.freeEdgeIndices.poll();
        }

        this.incidenceMatrix[i][edge] = 1;
        this.incidenceMatrix[j][edge] = -1;
        this.edgeCount++;
    }

    /**
     * Removes an edge internally.
     * <p>
     * The column index associated with the removed edge is added to {@code freeEdgeIndices} for reuse.
     */
    @Override
    public void removeEdgeInternal(V from, V to) {
        if (containsVertex(from) && containsVertex(to)) {
            
            var i = this.vertexIndexMapping.get(from);
            var j = this.vertexIndexMapping.get(to);

            for (int e = 0; e < this.nextEdgeIndex; e++) {
                if (this.incidenceMatrix[i][e] == 1 && this.incidenceMatrix[j][e] == -1) {
                    this.incidenceMatrix[i][e] = 0;
                    this.incidenceMatrix[j][e] = 0;
                    this.edgeCount--;
                    this.freeEdgeIndices.add(e);
                    break;
                }
            }
        }
    }

    /**
     * Removes a vertex and all associated edges.
     * <p>
     * To maintain contiguous indices in the matrix rows, the vertex to be removed is swapped
     * with the last vertex in the graph. All edges associated with the removed vertex are cleared and their indices freed.
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
        // Count edges involving this vertex
        int edgesToRemove = 0;
        for (int e = 0; e < this.incidenceMatrix[indexToRemove].length; e++) {
            if (this.incidenceMatrix[indexToRemove][e] != 0) {
                edgesToRemove++;
                // Clear the entire edge column since we're removing a vertex
                for (int i = 0; i < this.incidenceMatrix.length; i++) {
                    this.incidenceMatrix[i][e] = 0;
                }
                this.freeEdgeIndices.add(e);
            }
        }
        this.edgeCount -= edgesToRemove;
    }

    private void swapWithLastVertex(int indexToRemove) {
        // Swap with last vertex to keep indices contiguous
        int lastIndex = this.vertexCount - 1;
        if (indexToRemove < lastIndex) {
            V lastVertex = this.indexVertexMapping.get(lastIndex);
            
            // Move row lastIndex to indexToRemove
            System.arraycopy(this.incidenceMatrix[lastIndex], 0, this.incidenceMatrix[indexToRemove], 0, this.incidenceMatrix[0].length);
            Arrays.fill(this.incidenceMatrix[lastIndex], (short) 0);
            
            this.vertexIndexMapping.put(lastVertex, indexToRemove);
            this.indexVertexMapping.put(indexToRemove, lastVertex);
            this.indexVertexMapping.remove(lastIndex);
        }
    }
}
