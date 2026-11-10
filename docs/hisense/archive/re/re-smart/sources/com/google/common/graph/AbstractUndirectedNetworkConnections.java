package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractUndirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    public final Map<E, N> incidentEdgeMap;

    public AbstractUndirectedNetworkConnections(Map<E, N> map) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.incidentEdgeMap = map;
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E e2, N n, boolean z) {
        if (z) {
            return;
        }
        addOutEdge(e2, n);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E e2, N n) {
        Preconditions.checkState(this.incidentEdgeMap.put(e2, n) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E e2) {
        N n = this.incidentEdgeMap.get(e2);
        Preconditions.checkNotNull(n);
        return n;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return incidentEdges();
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return Collections.unmodifiableSet(this.incidentEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return incidentEdges();
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> predecessors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E e2, boolean z) {
        if (z) {
            return null;
        }
        return removeOutEdge(e2);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e2) {
        N nRemove = this.incidentEdgeMap.remove(e2);
        Preconditions.checkNotNull(nRemove);
        return nRemove;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> successors() {
        return adjacentNodes();
    }
}
