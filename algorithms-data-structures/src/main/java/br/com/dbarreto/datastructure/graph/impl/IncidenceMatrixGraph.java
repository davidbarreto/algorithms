package br.com.dbarreto.datastructure.graph.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.dbarreto.datastructure.graph.EdgePolicy;

/**
 * Implementation of a graph using an incidence matrix.
 * <p>
 * This implementation uses a 2D short array where rows represent vertices and columns represent edges.
 * Values indicate the relationship: 1 (source), -1 (target), 0 (no connection).
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

    @Override
    public void addEdgeInternal(V from, V to) {

        addVertex(from);
        addVertex(to);

        var i = this.vertexIndexMapping.get(from);
        var j = this.vertexIndexMapping.get(to);

        if (hasEdge(from, to)) {
            return;
        }

        if (this.nextEdgeIndex >= this.incidenceMatrix[0].length) {
            throw new IllegalStateException("Maximum number of edges exceeded");
        }

        var edge = this.nextEdgeIndex++;

        this.incidenceMatrix[i][edge] = 1;
        this.incidenceMatrix[j][edge] = -1;
        this.edgeCount++;
    }

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
                    break;
                }
            }
        }
    }

    @Override
    public void removeVertex(V v) {
        var index = this.vertexIndexMapping.get(v);
        if (index != null) {
            // Count edges involving this vertex
            int edgesToRemove = 0;
            for (int e = 0; e < this.incidenceMatrix[index].length; e++) {
                if (this.incidenceMatrix[index][e] != 0) {
                    edgesToRemove++;
                    // Clear the entire edge column since we're removing a vertex
                    for (int i = 0; i < this.incidenceMatrix.length; i++) {
                        this.incidenceMatrix[i][e] = 0;
                    }
                }
            }
            
            this.edgeCount -= edgesToRemove;
            this.vertexIndexMapping.remove(v);
            this.indexVertexMapping.remove(index);
            this.vertexCount--;
        }
    }
}
