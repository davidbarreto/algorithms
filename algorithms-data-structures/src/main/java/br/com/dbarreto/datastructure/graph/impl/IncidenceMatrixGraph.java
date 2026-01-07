package br.com.dbarreto.datastructure.graph.impl;

import java.util.*;

import br.com.dbarreto.datastructure.graph.GraphType;

/**
 * An implementation of a graph using an incidence matrix, which is a 2D array where rows
 * represent vertices and columns represent edges.
 * <p>
 * In this matrix:
 * <ul>
 *     <li>A value of {@code 1} at {@code matrix[v][e]} indicates that vertex {@code v} is the source of edge {@code e}.</li>
 *     <li>A value of {@code -1} indicates that vertex {@code v} is the target of edge {@code e}.</li>
 *     <li>A value of {@code 0} indicates no connection between the vertex and the edge.</li>
 * </ul>
 * This implementation includes several key features:
 * <ul>
 *     <li><b>Dynamic Edge Reuse:</b> When an edge is removed, its column index is added to a pool of
 *     free indices. This allows the index to be reused for new edges, preventing the matrix from
 *     running out of columns and improving memory efficiency.</li>
 *     <li><b>Efficient Vertex Removal:</b> When a vertex is removed, it is swapped with the last
 *     vertex in the matrix. This ensures that the row indices remain contiguous, which simplifies
 *     indexing and avoids gaps in the matrix.</li>
 * </ul>
 * <b>Space Complexity:</b> O(V * E), where V is the maximum number of vertices and E is the
 * maximum number of edges.
 *
 * @param <V> the type of the vertices in the graph
 */
public class IncidenceMatrixGraph<V> extends AbstractGraph<V> {

    private final short[][] incidenceMatrix;
    private final double[] edgeWeights;
    private final Map<V, Integer> vertexIndexMapping;
    private final Map<Integer, V> indexVertexMapping;
    private Integer vertexCount;
    private Integer edgeCount;
    private Integer nextEdgeIndex;
    private final Queue<Integer> freeEdgeIndices;

    /**
     * Creates a new directed {@code IncidenceMatrixGraph} with a specified capacity for vertices and edges.
     *
     * @param numVertexes the maximum number of vertices the graph can hold
     * @param numEdges    the maximum number of edges the graph can hold
     */
    public IncidenceMatrixGraph(int numVertexes, int numEdges) {
        this(numVertexes, numEdges, GraphType.DIRECTED);
    }

    /**
     * Creates a new {@code IncidenceMatrixGraph} with a specified capacity and graph type (directed or undirected).
     *
     * @param numVertexes     the maximum number of vertices the graph can hold
     * @param numLogicalEdges the maximum number of logical edges the graph can hold
     * @param graphType       the type of the graph, which determines the edge policy (e.g., {@link GraphType#DIRECTED})
     */
    public IncidenceMatrixGraph(int numVertexes, int numLogicalEdges, GraphType graphType) {
        super(graphType);
        var maxEdges = graphType.policy().physicalEdgeCount(numLogicalEdges);
        this.incidenceMatrix = new short[numVertexes][maxEdges];
        this.edgeWeights = new double[maxEdges];
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
     * Adds a single directed edge to the graph's underlying storage.
     * <p>
     * This method reuses a freed edge index from the {@code freeEdgeIndices} queue if available.
     * Otherwise, it uses the next available column index for the new edge.
     *
     * @param from   the source vertex of the edge
     * @param to     the target vertex of the edge
     * @param weight the weight of the edge
     * @return {@code true} if the edge was successfully added, {@code false} otherwise
     * @throws IllegalStateException if the maximum number of edges has been reached
     */
    @Override
    public boolean addEdgeInternal(V from, V to, double weight) {

        addVertex(from);
        addVertex(to);

        var i = this.vertexIndexMapping.get(from);
        var j = this.vertexIndexMapping.get(to);

        if (hasEdge(from, to)) {
            return false;
        }

        if (this.freeEdgeIndices.isEmpty() && this.nextEdgeIndex >= this.incidenceMatrix[0].length) {
            throw new IllegalStateException("Maximum number of edges exceeded: " + this.incidenceMatrix[0].length);
        }

        int edge;
        if (this.freeEdgeIndices.isEmpty()) {
            edge = this.nextEdgeIndex++;
        } else {
            edge = this.freeEdgeIndices.poll();
        }

        this.incidenceMatrix[i][edge] = 1;
        this.incidenceMatrix[j][edge] = -1;
        this.edgeWeights[edge] = weight;
        this.edgeCount++;
        return true;
    }

    @Override
    public OptionalDouble weight(V from, V to) {
        if (hasEdge(from, to)) {
            var i = this.vertexIndexMapping.get(from);
            var j = this.vertexIndexMapping.get(to);
            for (int e = 0; e < this.nextEdgeIndex; e++) {
                if (this.incidenceMatrix[i][e] == 1 && this.incidenceMatrix[j][e] == -1) {
                    return OptionalDouble.of(this.edgeWeights[e]);
                }
            }
        }
        return OptionalDouble.empty();
    }

    /**
     * Removes a single directed edge from the graph's underlying storage.
     * <p>
     * The column index corresponding to the removed edge is added to the {@code freeEdgeIndices}
     * queue, making it available for reuse by future edge additions.
     *
     * @param from the source vertex of the edge to be removed
     * @param to   the target vertex of the edge to be removed
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
                    this.edgeWeights[e] = 0.0;
                    this.edgeCount--;
                    this.freeEdgeIndices.add(e);
                    break;
                }
            }
        }
    }

    /**
     * Removes a vertex and all of its incident edges from the graph.
     * <p>
     * To maintain a contiguous set of row indices in the incidence matrix, the row corresponding
     * to the vertex being removed is swapped with the last vertex's row. All edges connected to
     * the removed vertex are also cleared, and their column indices are freed for reuse.
     *
     * @param v the vertex to be removed
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
                this.edgeWeights[e] = 0.0;
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

            // Swap weights
            double lastWeight = this.edgeWeights[lastIndex];
            this.edgeWeights[lastIndex] = this.edgeWeights[indexToRemove];
            this.edgeWeights[indexToRemove] = lastWeight;
            
            this.vertexIndexMapping.put(lastVertex, indexToRemove);
            this.indexVertexMapping.put(indexToRemove, lastVertex);
            this.indexVertexMapping.remove(lastIndex);
        }
    }
}
