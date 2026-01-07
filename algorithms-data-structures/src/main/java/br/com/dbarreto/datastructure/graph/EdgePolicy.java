package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.tuple.Pair;

import java.util.Collection;

/**
 * Defines a strategy for handling edges in a graph, allowing for different behaviors for
 * directed and undirected graphs. This interface provides a way to abstract the underlying
 * representation of edges from their logical meaning.
 * <p>
 * An {@code EdgePolicy} is responsible for:
 * <ul>
 *     <li>Determining the physical edge(s) that represent a logical connection between two vertices.</li>
 *     <li>Converting between the number of physical edges (stored in the graph) and logical edges (exposed to the user).</li>
 *     <li>Identifying which physical edges should be considered as logical edges.</li>
 * </ul>
 */
public interface EdgePolicy {

    /**
     * Returns a collection of physical edges that represent the logical connection between two
     * vertices.
     * <p>
     * For a directed graph, this will typically be a single edge from the source to the target.
     * For an undirected graph, this may be a pair of edges, one in each direction, to represent
     * a two-way connection.
     *
     * @param from the source vertex of the logical connection
     * @param to   the target vertex of the logical connection
     * @param <V>  the type of the vertices
     * @return a {@link Collection} of {@link Pair}s, each representing a physical edge to be managed
     */
    <V> Collection<Pair<V, V>> edgePairs(V from, V to);

    /**
     * Converts the count of physical edges (i.e., the number of edges actually stored in the
     * graph's data structure) to the count of logical edges.
     * <p>
     * For example, in an undirected graph, two physical edges might represent a single logical
     * edge, so this method would return {@code physicalEdgeCount / 2}.
     *
     * @param physicalEdgeCount the total number of physical edges stored in the graph
     * @return the number of logical edges
     */
    int logicalEdgeCount(int physicalEdgeCount);

    /**
     * Converts the count of logical edges to the count of physical edges that need to be stored.
     * <p>
     * For example, in an undirected graph, {@code logicalEdgeCount} logical edges would require
     * {@code logicalEdgeCount * 2} physical edges to be stored.
     *
     * @param logicalEdgeCount the total number of logical edges
     * @return the corresponding number of physical edges
     */
    int physicalEdgeCount(int logicalEdgeCount);

    /**
     * Determines whether a given physical edge (from source to target) should be considered a
     * logical edge. This is useful for iterating over logical edges without duplication.
     * <p>
     * For a directed graph, every physical edge is a logical edge. For an undirected graph, to
     * avoid representing the same edge twice (e.g., as {@code (u, v)} and {@code (v, u)}), this
     * method can be implemented to designate only one of the two physical edges as the logical one
     * (e.g., based on a consistent ordering of vertices).
     *
     * @param from the source vertex of the physical edge
     * @param to   the target vertex of the physical edge
     * @param <V>  the type of the vertices
     * @return {@code true} if the physical edge from {@code from} to {@code to} is a logical edge,
     *         {@code false} otherwise
     */
    <V> boolean isLogicalEdge(V from, V to);
}
