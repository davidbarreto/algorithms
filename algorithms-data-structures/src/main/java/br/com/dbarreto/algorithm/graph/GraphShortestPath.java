package br.com.dbarreto.algorithm.graph;

import br.com.dbarreto.datastructure.graph.Graph;
import br.com.dbarreto.datastructure.tuple.Pair;
import br.com.dbarreto.datastructure.tuple.impl.SimplePair;

import java.util.*;

public class GraphShortestPath {

    private GraphShortestPath() {}

    public static <V> Map<V, Double> dijkstra(Graph<V> graph, V from) {

        Collection<V> vertices = graph.vertices();
        PriorityQueue<Pair<V, Double>> queue = new PriorityQueue<>(Comparator.comparing(Pair::second));
        Map<V, Double> distance = new HashMap<>();

        for (V v : vertices) {
            distance.put(v, Double.POSITIVE_INFINITY);
        }

        queue.add(new SimplePair<>(from, 0.0));
        distance.put(from, 0.0);

        while (!queue.isEmpty()) {
            V minimumDistanceVertex = queue.poll().first();
            for (V neighbor : graph.neighborsOf(minimumDistanceVertex)) {

                double weight = graph.weight(minimumDistanceVertex, neighbor)
                        .orElseThrow(() -> new IllegalStateException("There is no edge between "
                                + minimumDistanceVertex + " and " + neighbor + "."));

                double newDistance = distance.get(minimumDistanceVertex) + weight;
                if (newDistance < distance.get(neighbor)) {
                    distance.put(neighbor, newDistance);
                    queue.add(new SimplePair<>(neighbor, newDistance));
                }
            }

        }
        return distance;
    }

    public static <V> Map<V, Double> bellmanFord(Graph<V> graph, V from) {

        Collection<V> vertices = graph.vertices();
        Map<V, Double> distance = new HashMap<>();

        for (V v : vertices) {
            distance.put(v, Double.POSITIVE_INFINITY);
        }
        distance.put(from, 0.0);

        var edges = graph.physicalEdges();
        for (int i = 1; i < vertices.size(); i++) {
            for (var edge : edges) {
                if (distance.get(edge.from()) + edge.weight() < distance.get(edge.to())) {
                    distance.put(edge.to(), distance.get(edge.from()) + edge.weight());
                }
            }
        }

        for (var edge : edges) {
            if (distance.get(edge.from()) + edge.weight() < distance.get(edge.to())) {
                throw new IllegalArgumentException("Graph contains a negative-weight cycle");
            }
        }

        return distance;
    }

    public static <V> Map<V, Double> bfsBased(Graph<V> graph, V from) {

        var queue = new ArrayDeque<V>();
        var distance = new HashMap<V, Double>();
        var vertices = graph.vertices();
        for (var v : vertices) {
            distance.put(v, Double.POSITIVE_INFINITY);
        }
        distance.put(from, 0.0);
        queue.add(from);

        while (!queue.isEmpty()) {
            V vertex = queue.poll();

            for (V neighbor : graph.neighborsOf(vertex)) {
                if (distance.get(neighbor) == Double.POSITIVE_INFINITY) {
                    distance.put(neighbor, distance.get(vertex) + 1);
                    queue.add(neighbor);
                }
            }
        }

        return distance;
    }
}
