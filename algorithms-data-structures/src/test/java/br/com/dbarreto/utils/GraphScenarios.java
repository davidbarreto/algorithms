package br.com.dbarreto.utils;

import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.graph.GraphType;
import br.com.dbarreto.datastructure.graph.MutableGraph;
import br.com.dbarreto.datastructure.graph.impl.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for creating various graph scenarios for testing purposes.
 * <p>
 * Provides factory methods to generate pre-populated graphs of different implementations
 * (Adjacency List, Adjacency Matrix, etc.) and types (Directed, Undirected).
 * </p>
 */
public class GraphScenarios {

    /**
     * Creates a directed {@link AdjacencyListGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createAdjacencyListGraph() {
        return populateGraph(new AdjacencyListGraph<>());
    }

    /**
     * Creates an undirected {@link AdjacencyListGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createUndirectedAdjacencyListGraph() {
        return populateGraph(new AdjacencyListGraph<>(GraphType.UNDIRECTED));
    }

    /**
     * Creates a directed {@link AdjacencyMatrixGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createAdjacencyMatrixGraph() {
        return populateGraph(new AdjacencyMatrixGraph<>(10));
    }

    /**
     * Creates an undirected {@link AdjacencyMatrixGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createUndirectedAdjacencyMatrixGraph() {
        return populateGraph(new AdjacencyMatrixGraph<>(10, GraphType.UNDIRECTED));
    }

    /**
     * Creates a directed {@link IncidenceMatrixGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createIncidenceMatrixGraph() {
        return populateGraph(new IncidenceMatrixGraph<>(10, 20));
    }

    /**
     * Creates an undirected {@link IncidenceMatrixGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createUndirectedIncidenceMatrixGraph() {
        return populateGraph(new IncidenceMatrixGraph<>(10, 20, GraphType.UNDIRECTED));
    }

    /**
     * Creates a directed {@link NodeBasedGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createNodeBasedGraph() {
        return populateGraph(new NodeBasedGraph<>());
    }

    /**
     * Creates an undirected {@link NodeBasedGraph} populated with default data.
     *
     * @return a populated mutable graph
     */
    public static MutableGraph<String> createUndirectedNodeBasedGraph() {
        return populateGraph(new NodeBasedGraph<>(GraphType.UNDIRECTED));
    }

    /**
     * Returns a list of constructor functions for all supported graph implementations.
     * <p>
     * This is useful for parameterized tests that need to run against multiple graph types.
     * </p>
     *
     * @return a list of functions, each creating a {@link MutableGraph}
     */
    public static List<Function<GraphArguments, MutableGraph<String>>> graphImplementations() {
        return List.of(
                a -> new AdjacencyListGraph<>(a.type()),
                a -> new AdjacencyMatrixGraph<>(a.vertices(), a.type()),
                a -> new IncidenceMatrixGraph<>(a.vertices(), a.edges(), a.type()),
                a -> new NodeBasedGraph<>(a.type())
        );
    }

    /**
     * Creates and populates a graph using a specific constructor and scenario data.
     *
     * @param constructor the graph implementation constructor
     * @param scenario    the scenario containing edges and graph type
     * @return a populated {@link Graph}
     */
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

    /**
     * Populates a given graph with a predefined set of edges.
     *
     * @param mutableGraph the graph to populate
     * @return the same graph instance, now populated
     */
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

    /**
     * Represents a graph testing scenario.
     *
     * @param edges the list of edges to create the graph with
     * @param type  the type of the graph (Directed or Undirected)
     * @param <V>   the type of the vertices
     */
    public record GraphScenario<V>(List<Graph.Edge<V>> edges, GraphType type) {}

    /**
     * Represents the arguments required to construct a graph.
     *
     * @param type     the type of the graph
     * @param vertices the number of vertices
     * @param edges    the number of edges
     */
    public record GraphArguments(GraphType type, int vertices, int edges) {}
}
