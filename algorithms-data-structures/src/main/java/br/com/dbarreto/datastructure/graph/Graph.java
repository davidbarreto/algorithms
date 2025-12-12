package br.com.dbarreto.datastructure.graph;

public interface Graph<V> {
    void addVertex(V v);
    void addEdge(V from, V to);
    boolean hasEdge(V from, V to);
    int size();
}
