package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.graph.policy.DirectedEdgePolicy;
import br.com.dbarreto.datastructure.graph.policy.UndirectedEdgePolicy;

/**
 * Enumeration representing the type of a graph: Directed or Undirected.
 * Each type defines a specific {@link EdgePolicy} that governs how edges are handled.
 */
public enum GraphType {

    /**
     * Represents a directed graph where edges have a specific direction (from source to target).
     */
    DIRECTED {
        @Override
        public EdgePolicy policy() {
            return new DirectedEdgePolicy();
        }
    },

    /**
     * Represents an undirected graph where edges have no direction (connection is bidirectional).
     */
    UNDIRECTED {
        @Override
        public EdgePolicy policy() {
            return new UndirectedEdgePolicy();
        }
    };

    /**
     * Returns the edge policy associated with this graph type.
     *
     * @return the {@link EdgePolicy} implementation
     */
    public abstract EdgePolicy policy();
}
