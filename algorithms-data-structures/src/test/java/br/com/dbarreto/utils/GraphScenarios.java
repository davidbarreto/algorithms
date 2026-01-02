package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.graph.impl.*;

public class GraphScenarios {

    public static MutableGraph<String> createAdjacencyListGraph() {
        return populateGraph(new AdjacencyListGraph<>());
    }

    public static MutableGraph<String> createUndirectedAdjacencyListGraph() {
        return populateGraph(new AdjacencyListGraph<>(Graph.UNDIRECTED_GRAPH));
    }

    public static MutableGraph<String> createAdjacencyMatrixGraph() {
        return populateGraph(new AdjacencyMatrixGraph<>(10));
    }

    public static MutableGraph<String> createUndirectedAdjacencyMatrixGraph() {
        return populateGraph(new AdjacencyMatrixGraph<>(10, Graph.UNDIRECTED_GRAPH));
    }

    public static MutableGraph<String> createIncidenceMatrixGraph() {
        return populateGraph(new IncidenceMatrixGraph<>(10, 20));
    }

    public static MutableGraph<String> createUndirectedIncidenceMatrixGraph() {
        return populateGraph(new IncidenceMatrixGraph<>(10, 20, Graph.UNDIRECTED_GRAPH));
    }

    public static MutableGraph<String> createNodeBasedGraph() {
        return populateGraph(new NodeBasedGraph<>());
    }

    public static MutableGraph<String> createUndirectedNodeBasedGraph() {
        return populateGraph(new NodeBasedGraph<>(Graph.UNDIRECTED_GRAPH));
    }

    private static MutableGraph<String> populateGraph(MutableGraph<String> mutableGraph) {

        mutableGraph.addEdge("A", "B");
        mutableGraph.addEdge("A", "D");
        mutableGraph.addEdge("A", "E");
        mutableGraph.addEdge("D", "E");
        mutableGraph.addEdge("B", "E");
        mutableGraph.addEdge("B", "C");
        mutableGraph.addEdge("B", "G");
        mutableGraph.addEdge("C", "G");
        mutableGraph.addEdge("E", "F");

        return mutableGraph;
    }
}
