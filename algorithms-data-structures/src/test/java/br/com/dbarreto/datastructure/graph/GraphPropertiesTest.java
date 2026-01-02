package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.graph.impl.*;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Set;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphPropertiesTest {

    @Property
    void undirectedEdgesAreSymmetric(
            @ForAll("undirectedGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        assertThat(graph.getType()).isEqualTo(GraphType.UNDIRECTED);

        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());

            assertThat(graph.hasEdge(edge.from(), edge.to())).isTrue();
            assertThat(graph.hasEdge(edge.to(), edge.from())).isTrue();
        }
    }

    @Property
    void addingSameEdgeTwiceDoesNotDuplicate(
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

    @Property
    void edgeCountMatches(
            @ForAll("allGraphImplementations") GraphFactory<Integer> factory,
            @ForAll("edges") Set<Edge<Integer>> edges
    ) {
        MutableGraph<Integer> graph = factory.graph();
        for (Edge<Integer> edge : edges) {
            graph.addEdge(edge.from(), edge.to());
        }

        assertThat(graph.edgeCount()).isEqualTo(edges.size());
    }

    @Provide
    Arbitrary<GraphFactory<Integer>> directedGraphImplementations() {
        return Arbitraries.of(
                new GraphFactory<>(AdjacencyListGraph.class.getSimpleName(), () -> new AdjacencyListGraph<>(AbstractGraph.DIRECTED_GRAPH)),
                new GraphFactory<>(AdjacencyMatrixGraph.class.getSimpleName(), () -> new AdjacencyMatrixGraph<>(100, AbstractGraph.DIRECTED_GRAPH)),
                new GraphFactory<>(IncidenceMatrixGraph.class.getSimpleName(), () -> new IncidenceMatrixGraph<>(100, 200, AbstractGraph.DIRECTED_GRAPH)),
                new GraphFactory<>(NodeBasedGraph.class.getSimpleName(), () -> new NodeBasedGraph<>(AbstractGraph.DIRECTED_GRAPH))
        );
    }

    @Provide
    Arbitrary<GraphFactory<Integer>> undirectedGraphImplementations() {
        return Arbitraries.of(
                new GraphFactory<>(AdjacencyListGraph.class.getSimpleName(), () -> new AdjacencyListGraph<>(AbstractGraph.UNDIRECTED_GRAPH)),
                new GraphFactory<>(AdjacencyMatrixGraph.class.getSimpleName(), () -> new AdjacencyMatrixGraph<>(100, AbstractGraph.UNDIRECTED_GRAPH)),
                new GraphFactory<>(IncidenceMatrixGraph.class.getSimpleName(), () -> new IncidenceMatrixGraph<>(100, 200, AbstractGraph.UNDIRECTED_GRAPH)),
                new GraphFactory<>(NodeBasedGraph.class.getSimpleName(), () -> new NodeBasedGraph<>(AbstractGraph.UNDIRECTED_GRAPH))
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
