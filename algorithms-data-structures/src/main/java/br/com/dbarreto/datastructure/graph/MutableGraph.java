package br.com.dbarreto.datastructure.graph;

/**
 * Represents a Graph that can be modified by adding or removing vertices and edges.
 *
 * @param <V> the type of the vertices
 */
public interface MutableGraph<V> extends Graph<V> {
    /**
     * Adds a vertex to the graph. If the vertex already exists, this operation does nothing.
     *
     * @param v the vertex to add
     */
    void addVertex(V v);

    /**
     * Adds an edge between two vertices. If the vertices do not exist, they are added.
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    void addEdge(V from, V to);

    /**
     * Removes the edge between two vertices.
     *
     * @param from the source vertex
     * @param to   the target vertex
     */
    void removeEdge(V from, V to);

    /**
     * Removes a vertex and all edges connected to it (both incoming and outgoing).
     *
     * @param v the vertex to remove
     */
    void removeVertex(V v);
}
