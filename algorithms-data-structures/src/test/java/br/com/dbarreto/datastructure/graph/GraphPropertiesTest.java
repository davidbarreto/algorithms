package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.graph.impl.*;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Property-based tests for {@link Graph} implementations using Jqwik.
 * <p>
 * Verifies invariants that should hold true for any graph instance or sequence of operations,
 * such as symmetry in undirected graphs and consistency of counts.
 * </p>
 */
public class GraphPropertiesTest {

    /**
     * Property: In an undirected graph, if there is an edge from A to B, there must be an edge from B to A.
     */
    @Property
    void shouldHaveSymmetricEdgesInUndirectedGraph(
            @ForAll("undirectedGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        assertThat(graph.type()).isEqualTo(GraphType.UNDIRECTED);

        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());

            assertThat(graph.hasEdge(edge.from(), edge.to())).isTrue();
            assertThat(graph.hasEdge(edge.to(), edge.from())).isTrue();
        }
    }

    /**
     * Property: In a directed graph, an edge from A to B does not imply an edge from B to A.
     */
    @Property
    void shouldNotHaveSymmetricEdgesInDirectedGraph(
            @ForAll("directedGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edge") Edge<Integer> edge
    ) {
        MutableGraph<Integer> graph = factory.graph();
        graph.addEdge(edge.from(), edge.to());

        assertThat(graph.hasEdge(edge.from(), edge.to())).isTrue();
        assertThat(graph.hasEdge(edge.to(), edge.from())).isFalse();
    }

    /**
     * Property: Adding the same edge multiple times should not increase the edge count.
     */
    @Property
    void shouldNotDuplicateEdgesWhenAddedTwice(
            @ForAll("allGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edge") Edge<Integer> edge,
            @ForAll @IntRange(min = 2, max = 100) int repeat
    ) {
        MutableGraph<Integer> graph = factory.graph();
        for (int i = 0; i < repeat; i++) {
            graph.addEdge(edge.from(), edge.to());
        }

        assertThat(graph.edgeCount()).isEqualTo(1);
    }

    /**
     * Property: The edge count of the graph should match the number of unique edges added.
     */
    @Property
    void shouldMatchEdgeCountWithUniqueEdges(
            @ForAll("allGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());
        }

        assertThat(graph.edgeCount()).isEqualTo(edges.size());
    }

    /**
     * Property: The vertex count should match the number of unique nodes present in the added edges.
     */
    @Property
    void shouldMatchVertexCountWithUniqueNodesInEdges(
            @ForAll("allGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        Set<Integer> uniqueNodes = new HashSet<>();
        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());
            uniqueNodes.add(edge.from());
            uniqueNodes.add(edge.to());
        }

        assertThat(graph.vertexCount()).isEqualTo(uniqueNodes.size());
    }

    /**
     * Property: Removing an edge should decrease the edge count by exactly one.
     */
    @Property
    void shouldUpdateCountsWhenRemovingEdge(
            @ForAll("allGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());
        }

        int initialEdgeCount = graph.edgeCount();
        Edge<Integer> edgeToRemove = edges.iterator().next();

        graph.removeEdge(edgeToRemove.from(), edgeToRemove.to());

        assertThat(graph.edgeCount()).isEqualTo(initialEdgeCount - 1);
        assertThat(graph.hasEdge(edgeToRemove.from(), edgeToRemove.to())).isFalse();
    }

    /**
     * Property: Removing a vertex should remove all edges associated with that vertex.
     */
    @Property
    void shouldRemoveAssociatedEdgesWhenRemovingVertex(
            @ForAll("allGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());
        }

        Integer vertexToRemove = edges.iterator().next().from();
        graph.removeVertex(vertexToRemove);

        assertThat(graph.containsVertex(vertexToRemove)).isFalse();

        for (Edge<Integer> edge : edges) {
            if (edge.from().equals(vertexToRemove) || edge.to().equals(vertexToRemove)) {
                assertThat(graph.hasEdge(edge.from(), edge.to())).isFalse();
            }
        }
    }

    /**
     * Property: The neighbors of a vertex should be consistent with the edges defined in the graph.
     */
    @Property
    void shouldHaveConsistentNeighbors(
            @ForAll("allGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        Set<Integer> vertices = new HashSet<>();
        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());
            vertices.add(edge.from());
            vertices.add(edge.to());
        }

        for (Integer v : vertices) {
            for (Integer neighbor : graph.neighborsOf(v)) {
                assertThat(graph.hasEdge(v, neighbor)).isTrue();
                if (graph.type() == GraphType.UNDIRECTED) {
                    assertThat(graph.neighborsOf(neighbor)).contains(v);
                }
            }
        }
    }

    @Provide
    Arbitrary<GraphFactory<Integer>> directedGraphImplementations() {
        return Arbitraries.of(
                new GraphFactory<>(AdjacencyListGraph.class.getSimpleName(), () -> new AdjacencyListGraph<>(GraphType.DIRECTED)),
                new GraphFactory<>(AdjacencyMatrixGraph.class.getSimpleName(), () -> new AdjacencyMatrixGraph<>(100, GraphType.DIRECTED)),
                new GraphFactory<>(IncidenceMatrixGraph.class.getSimpleName(), () -> new IncidenceMatrixGraph<>(100, 200, GraphType.DIRECTED)),
                new GraphFactory<>(NodeBasedGraph.class.getSimpleName(), () -> new NodeBasedGraph<>(GraphType.DIRECTED))
        );
    }

    @Provide
    Arbitrary<GraphFactory<Integer>> undirectedGraphImplementations() {
        return Arbitraries.of(
                new GraphFactory<>(AdjacencyListGraph.class.getSimpleName(), () -> new AdjacencyListGraph<>(GraphType.UNDIRECTED)),
                new GraphFactory<>(AdjacencyMatrixGraph.class.getSimpleName(), () -> new AdjacencyMatrixGraph<>(100, GraphType.UNDIRECTED)),
                new GraphFactory<>(IncidenceMatrixGraph.class.getSimpleName(), () -> new IncidenceMatrixGraph<>(100, 200, GraphType.UNDIRECTED)),
                new GraphFactory<>(NodeBasedGraph.class.getSimpleName(), () -> new NodeBasedGraph<>(GraphType.UNDIRECTED))
        );
    }

    @Provide
    Arbitrary<GraphFactory<Integer>> allGraphImplementations() {
        return Arbitraries.oneOf(directedGraphImplementations(),
                undirectedGraphImplementations());
    }

    @Provide
    Arbitrary<Edge<Integer>> edge() {
        Arbitrary<Integer> nodes = Arbitraries.integers().between(0, 50);
        return Combinators.combine(nodes, nodes)
                .as((a, b) -> a < b ? new Edge<>(a, b) : new Edge<>(b, a))
                .filter(e -> !e.from().equals(e.to()));
    }

    @Provide
    Arbitrary<Set<Edge<Integer>>> edges() {
        return edge()
                .set()
                .ofMinSize(1)
                .ofMaxSize(50);
    }

    record Edge<T>(T from, T to) {}

    record GraphFactory<V>(String name, Supplier<MutableGraph<V>> supplier) {
        MutableGraph<V> graph() {
            return supplier.get();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
