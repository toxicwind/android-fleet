package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class UndirectedMultiNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {

    @LazyInit
    public transient Reference<Multiset<N>> adjacentNodesReference;

    public UndirectedMultiNetworkConnections(Map<E, N> map) {
        super(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset<N> adjacentNodesMultiset() {
        Multiset<N> multiset = (Multiset) getReference(this.adjacentNodesReference);
        if (multiset != null) {
            return multiset;
        }
        HashMultiset hashMultisetCreate = HashMultiset.create(this.incidentEdgeMap.values());
        this.adjacentNodesReference = new SoftReference(hashMultisetCreate);
        return hashMultisetCreate;
    }

    public static <T> T getReference(Reference<T> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public static <N, E> UndirectedMultiNetworkConnections<N, E> of() {
        return new UndirectedMultiNetworkConnections<>(new HashMap(2, 1.0f));
    }

    public static <N, E> UndirectedMultiNetworkConnections<N, E> ofImmutable(Map<E, N> map) {
        return new UndirectedMultiNetworkConnections<>(ImmutableMap.copyOf((Map) map));
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public void addInEdge(E e2, N n, boolean z) {
        if (z) {
            return;
        }
        addOutEdge(e2, n);
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public void addOutEdge(E e2, N n) {
        super.addOutEdge(e2, n);
        Multiset multiset = (Multiset) getReference(this.adjacentNodesReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(adjacentNodesMultiset().elementSet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(final N n) {
        return new MultiEdgesConnecting<E>(this.incidentEdgeMap, n) { // from class: com.google.common.graph.UndirectedMultiNetworkConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return UndirectedMultiNetworkConnections.this.adjacentNodesMultiset().count(n);
            }
        };
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public N removeInEdge(E e2, boolean z) {
        if (z) {
            return null;
        }
        return removeOutEdge(e2);
    }

    @Override // com.google.common.graph.AbstractUndirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e2) {
        N nRemove = this.incidentEdgeMap.remove(e2);
        Preconditions.checkNotNull(nRemove);
        Multiset multiset = (Multiset) getReference(this.adjacentNodesReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(nRemove));
        }
        return nRemove;
    }
}
