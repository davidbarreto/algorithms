package br.com.dbarreto.datastructure.node;

import java.util.Map;

public interface GraphNode<T> extends Vertex<T> {

    Map<GraphNode<T>, Double> neighbors();
    void addNeighbor(GraphNode<T> neighbor, double weight);
    void removeNeighbor(GraphNode<T> neighbor);
}
