package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Immutable(containerOf = {"N", MtkTvRatingConvert2Goo.SUB_RATING_STR_V})
@Beta
public final class ImmutableValueGraph<N, V> extends StandardValueGraph<N, V> {

    public static class Builder<N, V> {
        public final MutableValueGraph<N, V> mutableValueGraph;

        public Builder(ValueGraphBuilder<N, V> valueGraphBuilder) {
            this.mutableValueGraph = valueGraphBuilder.copy().incidentEdgeOrder(ElementOrder.stable()).build();
        }

        @CanIgnoreReturnValue
        public Builder<N, V> addNode(N n) {
            this.mutableValueGraph.addNode(n);
            return this;
        }

        public ImmutableValueGraph<N, V> build() {
            return ImmutableValueGraph.copyOf(this.mutableValueGraph);
        }

        @CanIgnoreReturnValue
        public Builder<N, V> putEdgeValue(N n, N n2, V v) {
            this.mutableValueGraph.putEdgeValue(n, n2, v);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<N, V> putEdgeValue(EndpointPair<N> endpointPair, V v) {
            this.mutableValueGraph.putEdgeValue(endpointPair, v);
            return this;
        }
    }

    public ImmutableValueGraph(ValueGraph<N, V> valueGraph) {
        super(ValueGraphBuilder.from(valueGraph), getNodeConnections(valueGraph), valueGraph.edges().size());
    }

    public static <N, V> GraphConnections<N, V> connectionsOf(final ValueGraph<N, V> valueGraph, final N n) {
        Function<N, V> function = new Function<N, V>() { // from class: com.google.common.graph.ImmutableValueGraph.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.base.Function
            public V apply(N n2) {
                return (V) valueGraph.edgeValueOrDefault(n, n2, null);
            }
        };
        return valueGraph.isDirected() ? DirectedGraphConnections.ofImmutable(n, valueGraph.incidentEdges(n), function) : UndirectedGraphConnections.ofImmutable(new Maps.AsMapView(valueGraph.adjacentNodes(n), function));
    }

    public static <N, V> ImmutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        return valueGraph instanceof ImmutableValueGraph ? (ImmutableValueGraph) valueGraph : new ImmutableValueGraph<>(valueGraph);
    }

    public static <N, V> ImmutableMap<N, GraphConnections<N, V>> getNodeConnections(ValueGraph<N, V> valueGraph) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N n : valueGraph.nodes()) {
            builder.put(n, connectionsOf(valueGraph, n));
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set adjacentNodes(Object obj) {
        return checkedConnections(obj).adjacentNodes();
    }

    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.ValueGraph
    public /* bridge */ /* synthetic */ Object edgeValueOrDefault(EndpointPair endpointPair, Object obj) {
        return super.edgeValueOrDefault(endpointPair, obj);
    }

    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(EndpointPair endpointPair) {
        return super.hasEdgeConnecting(endpointPair);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.stable();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public Set incidentEdges(Object obj) {
        return new StandardValueGraph.AnonymousClass1(this, this, obj, checkedConnections(obj));
    }

    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public ElementOrder nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public /* bridge */ /* synthetic */ Set nodes() {
        return super.nodes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
    public Set predecessors(Object obj) {
        return checkedConnections(obj).predecessors();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
    public Set successors(Object obj) {
        return checkedConnections(obj).successors();
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.ValueGraph
    public ImmutableGraph<N> asGraph() {
        return new ImmutableGraph<>(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.ValueGraph
    public Object edgeValueOrDefault(Object obj, Object obj2, Object obj3) {
        if (obj == 0) {
            throw new NullPointerException();
        }
        if (obj2 != 0) {
            return edgeValueOrDefault_internal(obj, obj2, obj3);
        }
        throw new NullPointerException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(Object obj, Object obj2) {
        if (obj == 0) {
            throw new NullPointerException();
        }
        if (obj2 != 0) {
            return hasEdgeConnecting_internal(obj, obj2);
        }
        throw new NullPointerException();
    }

    @Deprecated
    public static <N, V> ImmutableValueGraph<N, V> copyOf(ImmutableValueGraph<N, V> immutableValueGraph) {
        if (immutableValueGraph != null) {
            return immutableValueGraph;
        }
        throw new NullPointerException();
    }
}
