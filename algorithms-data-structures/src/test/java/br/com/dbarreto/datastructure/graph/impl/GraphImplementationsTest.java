package br.com.dbarreto.datastructure.graph.impl;

import br.com.dbarreto.datastructure.graph.MutableGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphImplementationsTest {

    @Test
    void testAdjacencyListGraph() {
        MutableGraph<String> graph = new MutableAdjacencyListGraph<>();

        // Test basic operations
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("A", "C"));

        assertEquals(3, graph.vertexCount());
        assertEquals(2, graph.edgeCount());

        assertTrue(graph.neighborsOf("A").contains("B"));
        assertTrue(graph.neighborsOf("B").contains("C"));
        assertTrue(graph.neighborsOf("C").isEmpty());

        // Test remove edge
        graph.removeEdge("A", "B");
        assertFalse(graph.hasEdge("A", "B"));
        assertEquals(1, graph.edgeCount());

        // Test remove vertex
        graph.removeVertex("B");
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.hasEdge("B", "C"));
        assertEquals(2, graph.vertexCount());
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void testAdjacencyMatrixGraph() {
        MutableGraph<String> graph = new MutableAdjacencyMatrixGraph<>(10);

        // Test basic operations
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("A", "C"));

        assertEquals(3, graph.vertexCount());
        assertEquals(2, graph.edgeCount());

        assertTrue(graph.neighborsOf("A").contains("B"));
        assertTrue(graph.neighborsOf("B").contains("C"));
        assertTrue(graph.neighborsOf("C").isEmpty());

        // Test remove edge
        graph.removeEdge("A", "B");
        assertFalse(graph.hasEdge("A", "B"));
        assertEquals(1, graph.edgeCount());

        // Test remove vertex
        graph.removeVertex("B");
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.hasEdge("B", "C"));
        assertEquals(2, graph.vertexCount());
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void testIncidenceMatrixGraph() {
        MutableGraph<String> graph = new MutableIncidenceMatrixGraph<>(10, 10);

        // Test basic operations
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.hasEdge("A", "B"));
        assertTrue(graph.hasEdge("B", "C"));
        assertFalse(graph.hasEdge("A", "C"));

        assertEquals(3, graph.vertexCount());
        assertEquals(2, graph.edgeCount());

        assertTrue(graph.neighborsOf("A").contains("B"));
        assertTrue(graph.neighborsOf("B").contains("C"));
        assertTrue(graph.neighborsOf("C").isEmpty());

        // Test remove edge
        graph.removeEdge("A", "B");
        assertFalse(graph.hasEdge("A", "B"));
        assertEquals(1, graph.edgeCount());

        // Test remove vertex
        graph.removeVertex("B");
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.hasEdge("B", "C"));
        assertEquals(2, graph.vertexCount());
        assertEquals(0, graph.edgeCount());
    }
}