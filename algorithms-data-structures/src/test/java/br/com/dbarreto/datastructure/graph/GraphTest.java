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
    void testGraph(Graph<String> graph) {
        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("A", "C"));

        assertEquals(7, graph.vertexCount());
        assertEquals(9, graph.edgeCount());

        assertThat(graph.neighborsOf("A")).containsExactlyInAnyOrder("B", "D", "E");
        assertThat(graph.neighborsOf("B")).contains("E", "C", "G");
        assertThat(graph.neighborsOf("F")).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("graphArguments")
    void testMutableGraph(MutableGraph<String> graph) {
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

    static Stream<Arguments> graphArguments() {
        return Stream.of(
            Arguments.of(GraphScenarios.createAdjacencyListGraph()),
            Arguments.of(GraphScenarios.createAdjacencyMatrixGraph()),
            Arguments.of(GraphScenarios.createIncidenceMatrixGraph())
        );
    }
}