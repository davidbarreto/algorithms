package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.utils.GraphScenarios;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @ParameterizedTest
    @MethodSource("graphArguments")
    void testDirectedGraph(Graph<String> graph) {
        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("A", "C"));
        assertFalse(graph.hasEdge("B", "A"));

        assertEquals(7, graph.vertexCount());
        assertEquals(9, graph.edgeCount());

        assertThat(graph.neighborsOf("A")).containsExactlyInAnyOrder("B", "D", "E");
        assertThat(graph.neighborsOf("B")).containsExactlyInAnyOrder("E", "C", "G");
        assertThat(graph.neighborsOf("F")).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("graphArguments")
    void testDirectedMutableGraph(MutableGraph<String> graph) {
        // Test remove edge
        graph.removeEdge("A", "B");
        assertFalse(graph.hasEdge("A", "B"));
        assertEquals(8, graph.edgeCount());

        // Test remove vertex
        graph.removeVertex("B");
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.hasEdge("B", "C"));
        assertEquals(6, graph.vertexCount());
        assertEquals(5, graph.edgeCount());
    }

    @ParameterizedTest
    @MethodSource("undirectedGraphArguments")
    void testUndirectedGraph(Graph<String> graph) {
        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "A"));
        assertTrue(graph.hasEdge("B", "C"));
        assertTrue(graph.hasEdge("C", "B"));

        assertEquals(7, graph.vertexCount());
        assertEquals(9, graph.edgeCount());

        assertThat(graph.neighborsOf("A")).containsExactlyInAnyOrder("B", "D", "E");
        assertThat(graph.neighborsOf("B")).containsExactlyInAnyOrder("A", "E", "C", "G");
        assertThat(graph.neighborsOf("F")).containsExactlyInAnyOrder("E");
    }

    @ParameterizedTest
    @MethodSource("undirectedGraphArguments")
    void testUndirectedMutableGraph(MutableGraph<String> graph) {
        // Test remove edge
        graph.removeEdge("A", "B");
        assertFalse(graph.hasEdge("A", "B"));
        assertFalse(graph.hasEdge("B", "A"));
        assertEquals(8, graph.edgeCount());

        // Test remove vertex
        graph.removeVertex("B");
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("C", "B"));
        assertEquals(6, graph.vertexCount());
        assertEquals(5, graph.edgeCount());
    }

    static Stream<Arguments> graphArguments() {
        return Stream.of(
                Arguments.of(GraphScenarios.createAdjacencyListGraph()),
                Arguments.of(GraphScenarios.createAdjacencyMatrixGraph()),
                Arguments.of(GraphScenarios.createIncidenceMatrixGraph()),
                Arguments.of(GraphScenarios.createNodeBasedGraph())
        );
    }

    static Stream<Arguments> undirectedGraphArguments() {
        return Stream.of(
                Arguments.of(GraphScenarios.createUndirectedAdjacencyListGraph()),
                Arguments.of(GraphScenarios.createUndirectedAdjacencyMatrixGraph()),
                Arguments.of(GraphScenarios.createUndirectedIncidenceMatrixGraph()),
                Arguments.of(GraphScenarios.createUndirectedNodeBasedGraph())
        );
    }
}