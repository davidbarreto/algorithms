package br.com.dbarreto.datastructure.node;

import java.util.Collection;

public interface GraphNode<T> extends Vertex<T> {

    Collection<GraphNode<T>> neighbors();
    void addNeighbor(GraphNode<T> neighbor);
    void removeNeighbor(GraphNode<T> neighbor);
}
