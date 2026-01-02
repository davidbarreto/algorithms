package br.com.dbarreto.datastructure.graph;

import java.util.Collection;

import br.com.dbarreto.algorithm.graph.GraphTraversals;
import br.com.dbarreto.datastructure.graph.impl.GraphType;

/**
 * Represents a Graph data structure consisting of vertices (nodes) and edges connecting them.
 * This interface provides read-only access to graph properties and structure.
 *
 * @param <V> the type of the vertices
 */
public interface Graph<V> {
    /**
     * Checks if the graph contains the specified vertex.
     *
     * @param vertex the vertex to check
     * @return true if the vertex exists in the graph, false otherwise
     */
    boolean containsVertex(V vertex);

    /**
     * Checks if there is an edge from the source vertex to the target vertex.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @return true if the edge exists, false otherwise
     */
    boolean hasEdge(V from, V to);

    /**
     * Returns a collection of all vertices in the graph.
     *
     * @return a collection of vertices
     */
    Collection<V> vertices();

    /**
     * Returns the collection of vertices that are directly connected to the specified vertex
     * by an outgoing edge.
     *
     * @param vertex the source vertex
     * @return a collection of neighbor vertices
     */
    Collection<V> neighborsOf(V vertex);

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the vertex count
     */
    int vertexCount();

    /**
     * Returns the number of edges in the graph.
     * For undirected graphs, this count is normalized (bidirectional edges count as one).
     *
     * @return the edge count
     */
    int edgeCount();

    /**
     * Returns the type of the graph (Directed or Undirected).
     *
     * @return the {@link GraphType}
     */
    GraphType getType();

    /**
     * Checks if a path exists between the start vertex and the target vertex.
     * Uses Breadth-First Search (BFS) for traversal.
     *
     * @param start  the starting vertex
     * @param target the target vertex
     * @return true if a path exists, false otherwise
     */
    default boolean hasPath(V start, V target) {
        if (!containsVertex(target)) {
            return false;
        }

        return GraphTraversals.breadthFirstSearch(this, start, target);
    }
}
