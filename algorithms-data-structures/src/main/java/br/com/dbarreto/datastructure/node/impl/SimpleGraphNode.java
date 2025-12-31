package br.com.dbarreto.datastructure.node.impl;

import br.com.dbarreto.datastructure.node.GraphNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SimpleGraphNode<T> implements GraphNode<T> {

    private T value;
    private Set<GraphNode<T>> neighbors;

    public SimpleGraphNode() {
        this(null);
    }

    public SimpleGraphNode(T value) {
        this.neighbors = new HashSet<>();
        this.value = value;
    }

    @Override
    public Collection<GraphNode<T>> neighbors() {
        return this.neighbors;
    }

    @Override
    public void addNeighbor(GraphNode<T> neighbor) {
        this.neighbors.add(neighbor);
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
