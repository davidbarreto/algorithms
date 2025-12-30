package br.com.dbarreto.datastructure.graph;

import java.util.Collection;

public interface Graph<V> {
    boolean containsVertex(V vertex);
    boolean hasEdge(V from, V to);
    Collection<V> vertices();
    Collection<V> neighborsOf(V vertex);
    int vertexCount();
    int edgeCount();
}
