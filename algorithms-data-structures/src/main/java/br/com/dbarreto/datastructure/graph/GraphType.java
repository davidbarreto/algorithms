package br.com.dbarreto.datastructure.graph;

import br.com.dbarreto.datastructure.graph.policy.DirectedEdgePolicy;
import br.com.dbarreto.datastructure.graph.policy.UndirectedEdgePolicy;

public enum GraphType {

    DIRECTED {
        @Override
        public EdgePolicy policy() {
            return new DirectedEdgePolicy();
        }
    },
    UNDIRECTED {
        @Override
        public EdgePolicy policy() {
            return new UndirectedEdgePolicy();
        }
    };

    public abstract EdgePolicy policy();
}
