package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableAdjacencyListGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableAdjacencyMatrixGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableIncidenceMatrixGraph;
import br.com.dbarreto.datastructure.graph.impl.MutableNodeBasedGraph;
import br.com.dbarreto.datastructure.node.GraphNode;
import br.com.dbarreto.datastructure.node.impl.SimpleGraphNode;

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

    public static MutableGraph<GraphNode<String>> createNodeBasedGraph() {
        return populateNodeGraph(new MutableNodeBasedGraph<>());
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

    private static MutableGraph<GraphNode<String>> populateNodeGraph(MutableGraph<GraphNode<String>> mutableGraph) {

        var a = new SimpleGraphNode<String>("A");
        var b = new SimpleGraphNode<String>("B");
        var c = new SimpleGraphNode<String>("C");
        var d = new SimpleGraphNode<String>("D");
        var e = new SimpleGraphNode<String>("E");
        var f = new SimpleGraphNode<String>("F");
        var g = new SimpleGraphNode<String>("G");

        mutableGraph.addEdge(a, b);
        mutableGraph.addEdge(a, d);
        mutableGraph.addEdge(a, e);
        mutableGraph.addEdge(d, e);
        mutableGraph.addEdge(b, e);
        mutableGraph.addEdge(b, c);
        mutableGraph.addEdge(b, g);
        mutableGraph.addEdge(c, g);
        mutableGraph.addEdge(e, f);

        return mutableGraph;
    }
}
