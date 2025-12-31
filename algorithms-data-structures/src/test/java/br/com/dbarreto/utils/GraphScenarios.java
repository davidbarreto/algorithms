package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableAdjacencyListGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableAdjacencyMatrixGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableIncidenceMatrixGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableNodeBasedGraph;

public class GraphScenarios {

    public static MutableGraph<String> createAdjacencyListGraph() {
        return populateGraph(new MutableAdjacencyListGraph<>());
    }

    public static MutableGraph<String> createAdjacencyMatrixGraph() {
        return populateGraph(new MutableAdjacencyMatrixGraph<>(10));
    }

    public static MutableGraph<String> createIncidenceMatrixGraph() {
        return populateGraph(new MutableIncidenceMatrixGraph<>(10, 20));
    }

    public static MutableGraph<String> createNodeBasedGraph() {
        return populateGraph(new MutableNodeBasedGraph<>());
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
