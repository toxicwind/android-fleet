package com.google.common.graph;

import b.a.a.a.a;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class StandardValueGraph<N, V> extends AbstractValueGraph<N, V> {
    public final boolean allowsSelfLoops;
    public long edgeCount;
    public final boolean isDirected;
    public final MapIteratorCache<N, GraphConnections<N, V>> nodeConnections;
    public final ElementOrder<N> nodeOrder;

    /* JADX INFO: renamed from: com.google.common.graph.StandardValueGraph$1, reason: invalid class name */
    public class AnonymousClass1 extends IncidentEdgeSet<N> {
        public final /* synthetic */ GraphConnections val$connections;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StandardValueGraph standardValueGraph, BaseGraph baseGraph, Object obj, GraphConnections graphConnections) {
            super(baseGraph, obj);
            this.val$connections = graphConnections;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<EndpointPair<N>> iterator() {
            return this.val$connections.incidentEdgeIterator(this.node);
        }
    }

    public StandardValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        this(abstractGraphBuilder, abstractGraphBuilder.nodeOrder.createMap(abstractGraphBuilder.expectedNodeCount.or(10).intValue()), 0L);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return checkedConnections(n).adjacentNodes();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    public final GraphConnections<N, V> checkedConnections(N n) {
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n);
        if (graphConnections != null) {
            return graphConnections;
        }
        if (n == null) {
            throw new NullPointerException();
        }
        String strValueOf = String.valueOf(n);
        throw new IllegalArgumentException(a.a(strValueOf.length() + 38, "Node ", strValueOf, " is not an element of this graph."));
    }

    public final boolean containsNode(N n) {
        return this.nodeConnections.containsKey(n);
    }

    @Override // com.google.common.graph.AbstractBaseGraph
    public long edgeCount() {
        return this.edgeCount;
    }

    public V edgeValueOrDefault(N n, N n2, V v) {
        if (n == null) {
            throw new NullPointerException();
        }
        if (n2 != null) {
            return edgeValueOrDefault_internal(n, n2, v);
        }
        throw new NullPointerException();
    }

    public final V edgeValueOrDefault_internal(N n, N n2, V v) {
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n);
        V vValue = graphConnections == null ? null : graphConnections.value(n2);
        return vValue == null ? v : vValue;
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n, N n2) {
        if (n == null) {
            throw new NullPointerException();
        }
        if (n2 != null) {
            return hasEdgeConnecting_internal(n, n2);
        }
        throw new NullPointerException();
    }

    public final boolean hasEdgeConnecting_internal(N n, N n2) {
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n);
        return graphConnections != null && graphConnections.successors().contains(n2);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        return new AnonymousClass1(this, this, n, checkedConnections(n));
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> nodes() {
        return this.nodeConnections.unmodifiableKeySet();
    }

    @Override // com.google.common.graph.PredecessorsFunction
    public Set<N> predecessors(N n) {
        return checkedConnections(n).predecessors();
    }

    @Override // com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n) {
        return checkedConnections(n).successors();
    }

    public V edgeValueOrDefault(EndpointPair<N> endpointPair, V v) {
        validateEndpoints(endpointPair);
        return edgeValueOrDefault_internal(endpointPair.nodeU(), endpointPair.nodeV(), v);
    }

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        if (endpointPair != null) {
            return isOrderingCompatible(endpointPair) && hasEdgeConnecting_internal(endpointPair.nodeU(), endpointPair.nodeV());
        }
        throw new NullPointerException();
    }

    public StandardValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder, Map<N, GraphConnections<N, V>> map, long j) {
        MapIteratorCache<N, GraphConnections<N, V>> mapIteratorCache;
        this.isDirected = abstractGraphBuilder.directed;
        this.allowsSelfLoops = abstractGraphBuilder.allowsSelfLoops;
        this.nodeOrder = (ElementOrder<N>) abstractGraphBuilder.nodeOrder.cast();
        if (map instanceof TreeMap) {
            mapIteratorCache = new MapRetrievalCache<>(map);
        } else {
            mapIteratorCache = new MapIteratorCache<>(map);
        }
        this.nodeConnections = mapIteratorCache;
        Graphs.checkNonNegative(j);
        this.edgeCount = j;
    }
}
