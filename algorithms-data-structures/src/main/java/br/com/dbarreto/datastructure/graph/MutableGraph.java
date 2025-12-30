package br.com.dbarreto.datastructure.graph;

public interface MutableGraph<V> extends Graph<V> {
    void addVertex(V v);
    void addEdge(V from, V to);
    void removeEdge(V from, V to);
    void removeVertex(V v);
}
