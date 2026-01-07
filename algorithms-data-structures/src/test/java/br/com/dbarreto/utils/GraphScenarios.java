package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.graph.EdgePolicy;
import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.graph.impl.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static List<Function<GraphArguments, MutableGraph<String>>> graphImplementations() {
        return List.of(
                a -> new AdjacencyListGraph<>(Graph.toEdgePolicy(a.type())),
                a -> new AdjacencyMatrixGraph<>(a.vertices(), Graph.toEdgePolicy(a.type())),
                a -> new IncidenceMatrixGraph<>(a.vertices(), a.edges(), Graph.toEdgePolicy(a.type())),
                a -> new NodeBasedGraph<>(Graph.toEdgePolicy(a.type()))
        );
    }

    public static Graph<String> of(Function<GraphArguments, MutableGraph<String>> constructor,
                                   GraphScenario<String> scenario)
    {
        var vertices = scenario.edges().stream().map(Graph.Edge::from).collect(Collectors.toSet());
        vertices.addAll(scenario.edges().stream().map(Graph.Edge::to).toList());

        var numVertices = vertices.size();
        var numEdges = scenario.edges().size();

        var graph = constructor.apply(new GraphArguments(scenario.type(), numVertices, numEdges));

        for (Graph.Edge<String> edge : scenario.edges()) {
            graph.addEdge(edge.from(), edge.to(), edge.weight());
        }

        return graph;
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

    public record GraphScenario<V>(List<Graph.Edge<V>> edges, GraphType type) {}
    public record GraphArguments(GraphType type, int vertices, int edges) {}
}
