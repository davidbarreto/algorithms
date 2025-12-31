package br.com.dbarreto.datastructure.graph;

import java.util.Collection;

import br.com.dbarreto.algorithm.graph.GraphTraversals;

public interface Graph<V> {
    boolean containsVertex(V vertex);
    boolean hasEdge(V from, V to);
    Collection<V> vertices();
    Collection<V> neighborsOf(V vertex);
    int vertexCount();
    int edgeCount();

    default boolean hasPath(V start, V target) {
        if (!containsVertex(target)) {
            return false;
        }

        return GraphTraversals.breadthFirstSearch(this, start, target);
    }
}
