package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Immutable(containerOf = {"N"})
@Beta
public class ImmutableGraph<N> extends ForwardingGraph<N> {
    public final BaseGraph<N> backingGraph;

    public static class Builder<N> {
        public final MutableGraph<N> mutableGraph;

        public Builder(GraphBuilder<N> graphBuilder) {
            this.mutableGraph = graphBuilder.copy().incidentEdgeOrder(ElementOrder.stable()).build();
        }

        @CanIgnoreReturnValue
        public Builder<N> addNode(N n) {
            this.mutableGraph.addNode(n);
            return this;
        }

        public ImmutableGraph<N> build() {
            return ImmutableGraph.copyOf(this.mutableGraph);
        }

        @CanIgnoreReturnValue
        public Builder<N> putEdge(N n, N n2) {
            this.mutableGraph.putEdge(n, n2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<N> putEdge(EndpointPair<N> endpointPair) {
            this.mutableGraph.putEdge(endpointPair);
            return this;
        }
    }

    public ImmutableGraph(BaseGraph<N> baseGraph) {
        this.backingGraph = baseGraph;
    }

    public static <N> GraphConnections<N, GraphConstants.Presence> connectionsOf(Graph<N> graph, N n) {
        Functions.ConstantFunction constantFunction = new Functions.ConstantFunction(GraphConstants.Presence.EDGE_EXISTS);
        return graph.isDirected() ? DirectedGraphConnections.ofImmutable(n, graph.incidentEdges(n), constantFunction) : UndirectedGraphConnections.ofImmutable(new Maps.AsMapView(graph.adjacentNodes(n), constantFunction));
    }

    public static <N> ImmutableGraph<N> copyOf(Graph<N> graph) {
        return graph instanceof ImmutableGraph ? (ImmutableGraph) graph : new ImmutableGraph<>(new StandardValueGraph(GraphBuilder.from(graph), getNodeConnections(graph), graph.edges().size()));
    }

    public static <N> ImmutableMap<N, GraphConnections<N, GraphConstants.Presence>> getNodeConnections(Graph<N> graph) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N n : graph.nodes()) {
            builder.put(n, connectionsOf(graph, n));
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set adjacentNodes(Object obj) {
        return delegate().adjacentNodes(obj);
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ boolean allowsSelfLoops() {
        return super.allowsSelfLoops();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public int degree(Object obj) {
        return delegate().degree(obj);
    }

    @Override // com.google.common.graph.ForwardingGraph
    public BaseGraph<N> delegate() {
        return this.backingGraph;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(Object obj, Object obj2) {
        return delegate().hasEdgeConnecting(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public int inDegree(Object obj) {
        return delegate().inDegree(obj);
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.stable();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public Set incidentEdges(Object obj) {
        return delegate().incidentEdges(obj);
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ boolean isDirected() {
        return super.isDirected();
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ ElementOrder nodeOrder() {
        return super.nodeOrder();
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Set nodes() {
        return super.nodes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public int outDegree(Object obj) {
        return delegate().outDegree(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    public Set predecessors(Object obj) {
        return delegate().predecessors(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    public Set successors(Object obj) {
        return delegate().successors(obj);
    }

    @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair endpointPair) {
        return delegate().hasEdgeConnecting(endpointPair);
    }

    @Deprecated
    public static <N> ImmutableGraph<N> copyOf(ImmutableGraph<N> immutableGraph) {
        if (immutableGraph != null) {
            return immutableGraph;
        }
        throw new NullPointerException();
    }
}
