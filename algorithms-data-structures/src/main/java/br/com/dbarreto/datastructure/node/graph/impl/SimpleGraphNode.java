package br.com.dbarreto.datastructure.node.graph.impl;

import br.com.dbarreto.datastructure.node.graph.GraphNode;

import java.util.*;

/**
 * A simple implementation of {@link GraphNode}.
 * <p>
 * Uses a {@link HashMap} to store neighbors and their edge weights.
 * Equality and hash code are based on the node's value.
 * </p>
 *
 * @param <T> the type of the value held by the node
 */
public class SimpleGraphNode<T> implements GraphNode<T> {

    private T value;
    private Map<GraphNode<T>, Double> neighbors;

    /**
     * Creates a new graph node with a null value.
     */
    public SimpleGraphNode() {
        this(null);
    }

    /**
     * Creates a new graph node with the specified value.
     *
     * @param value the value to be held by the node
     */
    public SimpleGraphNode(T value) {
        this.neighbors = new HashMap<>();
        this.value = value;
    }

    @Override
    public Map<GraphNode<T>, Double> neighbors() {
        return this.neighbors;
    }

    @Override
    public void addNeighbor(GraphNode<T> neighbor, double weight) {
        this.neighbors.put(neighbor, weight);
    }

    @Override
    public void removeNeighbor(GraphNode<T> neighbor) {
        this.neighbors.remove(neighbor);
    }

    @Override
    public T value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleGraphNode<?> that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
