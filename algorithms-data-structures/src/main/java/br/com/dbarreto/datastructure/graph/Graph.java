package br.com.dbarreto.datastructure.graph;

import java.util.Collection;
import java.util.OptionalDouble;

import br.com.dbarreto.algorithm.graph.GraphTraversals;

/**
 * Represents a Graph data structure consisting of vertices (nodes) and edges connecting them.
 * This interface provides read-only access to graph properties and structure.
 * <p>
 * Edges in the graph can have weights. If no weight is specified during creation, a default weight is used.
 * Weights must be non-zero.
 * </p>
 *
 * @param <V> the type of the vertices
 */
public interface Graph<V> {

    /**
     * Checks if the graph contains the specified vertex.
     *
     * @param vertex the vertex to check
     * @return {@code true} if the vertex exists in the graph, {@code false} otherwise
     */
    boolean containsVertex(V vertex);

    /**
     * Checks if there is an edge from the source vertex to the target vertex.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @return {@code true} if the edge exists, {@code false} otherwise
     */
    boolean hasEdge(V from, V to);

    /**
     * Returns the weight of the edge from the source vertex to the target vertex.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @return an {@link OptionalDouble} containing the weight of the edge, or empty if the edge does not exist
     */
    OptionalDouble weight(V from, V to);

    /**
     * Returns a collection of all vertices in the graph.
     *
     * @return an unmodifiable view of the vertices of this graph
     */
    Collection<V> vertices();

    /**
     * Returns a collection of all logical edges in the graph.
     * <p>
     * If graph is {@link GraphType#UNDIRECTED}, it will add only one of the directions (A -> B) OR (B -> A).
     * </p>
     * <p>
     * If the graph is {@link GraphType#DIRECTED}, the result is the same as {@link Graph#physicalEdges()}
     * </p>
     *
     * @return a collection of edges
     */
    Collection<Edge<V>> logicalEdges();

    /**
     * Returns a collection of all physical edges in the graph.
     * <p>
     * If graph is {@link GraphType#UNDIRECTED}, it will add both of the directions (A -> B) AND (B -> A).
     * </p>
     * <p>
     * If the graph is {@link GraphType#DIRECTED}, the result is the same as {@link Graph#logicalEdges()}
     * </p>
     *
     * @return a collection of edges
     */
    Collection<Edge<V>> physicalEdges();

    /**
     * Returns the collection of vertices that are directly connected to the specified vertex
     * by an outgoing edge. It's an immutable view of the vertices.
     *
     * @param vertex the source vertex
     * @return an unmodifiable view of this vertex's neighbors
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
     * <p>
     * For undirected graphs, this count is normalized (bidirectional edges count as one).
     * It should match {@link Graph#logicalEdges()} size
     * </p>
     *
     * @return the edge count
     */
    int edgeCount();

    /**
     * Returns the type of the graph (Directed or Undirected).
     *
     * @return the {@link GraphType}
     */
    GraphType type();

    /**
     * Checks if a path exists between the start vertex and the target vertex.
     * Uses Breadth-First Search (BFS) for traversal.
     *
     * @param start  the starting vertex
     * @param target the target vertex
     * @return {@code true} if a path exists, {@code false} otherwise
     */
    default boolean hasPath(V start, V target) {
        if (!containsVertex(target)) {
            return false;
        }

        return GraphTraversals.breadthFirstSearch(this, start, target);
    }

    /**
     * Represents an edge in the graph.
     *
     * @param from   the source vertex
     * @param to     the target vertex
     * @param weight the weight of the edge
     * @param <V>    the type of the vertices
     */
    record Edge<V>(V from, V to, double weight) {}
}
