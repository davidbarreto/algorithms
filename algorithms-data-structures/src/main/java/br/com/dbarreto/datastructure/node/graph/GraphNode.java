package br.com.dbarreto.datastructure.node.graph;

import java.util.Map;

/**
 * Represents a node in a graph data structure.
 * <p>
 * A graph node holds a value and maintains a collection of its neighbors (adjacent nodes)
 * along with the weights of the edges connecting to them.
 * </p>
 *
 * @param <T> the type of the value held by the node
 */
public interface GraphNode<T> extends Vertex<T> {

    /**
     * Returns a map of neighboring nodes and the weights of the edges connecting to them.
     *
     * @return a map where keys are neighbor nodes and values are edge weights
     */
    Map<GraphNode<T>, Double> neighbors();

    /**
     * Adds a neighbor to this node with the specified edge weight.
     *
     * @param neighbor the neighbor node to add
     * @param weight   the weight of the edge connecting to the neighbor
     */
    void addNeighbor(GraphNode<T> neighbor, double weight);

    /**
     * Removes a neighbor from this node.
     *
     * @param neighbor the neighbor node to remove
     */
    void removeNeighbor(GraphNode<T> neighbor);
}
