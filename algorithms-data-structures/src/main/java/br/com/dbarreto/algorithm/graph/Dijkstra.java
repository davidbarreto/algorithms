package br.com.dbarreto.algorithm.graph;

import br.com.dbarreto.datastructure.graph.Graph;

import java.util.*;

public class Dijkstra {

    private Dijkstra() {}

    public static <V> Map<V, Double> shortestPath(Graph<V> graph, V from) {

        Collection<V> vertices = graph.vertices();
        Map<V, Double> distance = new HashMap<>();
        Set<V> visited = new HashSet<>();

        for (V v : vertices) {
            distance.put(v, Double.POSITIVE_INFINITY);
        }
        distance.put(from, 0.0);

        for (int i = 0; i < vertices.size(); i++) {
            V minimumDistanceVertex = minimumDistance(distance, visited);
            visited.add(minimumDistanceVertex);
            for (V neighbor : graph.neighborsOf(minimumDistanceVertex)) {
                if (!visited.contains(neighbor)) {
                    double newDistance = distance.get(minimumDistanceVertex)
                            + graph.weight(minimumDistanceVertex, neighbor);
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                    }
                }
            }
        }
        return distance;
    }

    private static <V> V minimumDistance(Map<V, Double> distance, Set<V> visited) {
        double minimum = Double.POSITIVE_INFINITY;
        V vertice = null;
        for (var entry : distance.entrySet()) {
            if (!visited.contains(entry.getKey()) && distance.get(entry.getKey()) <= minimum) {
                minimum = distance.get(entry.getKey());
                vertice = entry.getKey();
            }
        }
        return vertice;
    }
}
