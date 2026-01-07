package br.com.dbarreto.datastructure.graph;

/**
 * Represents a Graph that can be modified by adding or removing vertices and edges.
 *
 * @param <V> the type of the vertices
 */
public interface MutableGraph<V> extends Graph<V> {

    /**
     * The default weight assigned to an edge if no weight is provided.
     */
    double DEFAULT_WEIGHT = 1.0;

    /**
     * Adds a vertex to the graph.
     * <p>
     * If the vertex already exists, this operation does nothing.
     * </p>
     *
     * @param v the vertex to add
     */
    void addVertex(V v);

    /**
     * Adds an edge between two vertices with the default weight.
     * <p>
     * If the vertices do not exist, they are added to the graph.
     * </p>
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @return {@code true} if the edge was added, {@code false} if it already existed
     * @see #DEFAULT_WEIGHT
     */
    default boolean addEdge(V from, V to) {
        return addEdge(from, to, DEFAULT_WEIGHT);
    }

    /**
     * Adds an edge between two vertices with a specific weight.
     * <p>
     * If the vertices do not exist, they are added to the graph.
     * </p>
     * <p>
     * The weight must be non-zero. A weight of 0 is reserved to indicate the absence of an edge
     * in some implementations.
     * </p>
     * <p>
     * If the edge already exists, this operation does nothing and returns {@code false}.
     * To update the weight of an existing edge, the edge must first be removed and then added again with the new weight,
     * or use {@link #updateEdge(Object, Object, double)}.
     * </p>
     *
     * @param from   the source vertex
     * @param to     the target vertex
     * @param weight the weight of the edge
     * @return {@code true} if the edge was added, {@code false} if it already existed
     * @throws IllegalArgumentException if the weight is 0
     */
    boolean addEdge(V from, V to, double weight);

    /**
     * Removes the edge between two vertices.
     * <p>
     * If the edge does not exist, this operation does nothing.
     * </p>
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    void removeEdge(V from, V to);

    /**
     * Updates the weight of an edge between two vertices.
     * <p>
     * This method removes the existing edge (if any) and adds a new edge with the specified weight.
     * If the edge did not exist, it is created.
     * </p>
     *
     * @param from   the source vertex
     * @param to     the target vertex
     * @param weight the new weight of the edge
     * @return {@code true} if the edge was successfully updated (or added)
     * @throws IllegalArgumentException if the weight is 0
     */
    default boolean updateEdge(V from, V to, double weight) {
        removeEdge(from, to);
        return addEdge(from, to, weight);
    }

    /**
     * Removes a vertex and all edges connected to it (both incoming and outgoing).
     * <p>
     * If the vertex does not exist, this operation does nothing.
     * </p>
     *
     * @param v the vertex to remove
     */
    void removeVertex(V v);
}
