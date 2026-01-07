package br.com.dbarreto.datastructure.node;

/**
 * Represents a generic vertex (or node) in a data structure.
 *
 * @param <T> the type of the value held by the vertex
 */
public interface Vertex<T> {
    /**
     * Returns the value stored in this vertex.
     *
     * @return the value
     */
    T value();
}
