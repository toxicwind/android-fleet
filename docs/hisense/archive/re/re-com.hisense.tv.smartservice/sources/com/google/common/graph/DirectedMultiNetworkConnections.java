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
public final class DirectedMultiNetworkConnections<N, E> extends AbstractDirectedNetworkConnections<N, E> {

    @LazyInit
    public transient Reference<Multiset<N>> predecessorsReference;

    @LazyInit
    public transient Reference<Multiset<N>> successorsReference;

    public DirectedMultiNetworkConnections(Map<E, N> map, Map<E, N> map2, int i) {
        super(map, map2, i);
    }

    public static <T> T getReference(Reference<T> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public static <N, E> DirectedMultiNetworkConnections<N, E> of() {
        return new DirectedMultiNetworkConnections<>(new HashMap(2, 1.0f), new HashMap(2, 1.0f), 0);
    }

    public static <N, E> DirectedMultiNetworkConnections<N, E> ofImmutable(Map<E, N> map, Map<E, N> map2, int i) {
        return new DirectedMultiNetworkConnections<>(ImmutableMap.copyOf((Map) map), ImmutableMap.copyOf((Map) map2), i);
    }

    private Multiset<N> predecessorsMultiset() {
        Multiset<N> multiset = (Multiset) getReference(this.predecessorsReference);
        if (multiset != null) {
            return multiset;
        }
        HashMultiset hashMultisetCreate = HashMultiset.create(this.inEdgeMap.values());
        this.predecessorsReference = new SoftReference(hashMultisetCreate);
        return hashMultisetCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset<N> successorsMultiset() {
        Multiset<N> multiset = (Multiset) getReference(this.successorsReference);
        if (multiset != null) {
            return multiset;
        }
        HashMultiset hashMultisetCreate = HashMultiset.create(this.outEdgeMap.values());
        this.successorsReference = new SoftReference(hashMultisetCreate);
        return hashMultisetCreate;
    }

    @Override // com.google.common.graph.AbstractDirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public void addInEdge(E e2, N n, boolean z) {
        super.addInEdge(e2, n, z);
        Multiset multiset = (Multiset) getReference(this.predecessorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    @Override // com.google.common.graph.AbstractDirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public void addOutEdge(E e2, N n) {
        super.addOutEdge(e2, n);
        Multiset multiset = (Multiset) getReference(this.successorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(final N n) {
        return new MultiEdgesConnecting<E>(this.outEdgeMap, n) { // from class: com.google.common.graph.DirectedMultiNetworkConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedMultiNetworkConnections.this.successorsMultiset().count(n);
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> predecessors() {
        return Collections.unmodifiableSet(predecessorsMultiset().elementSet());
    }

    @Override // com.google.common.graph.AbstractDirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public N removeInEdge(E e2, boolean z) {
        N n = (N) super.removeInEdge(e2, z);
        Multiset multiset = (Multiset) getReference(this.predecessorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(n));
        }
        return n;
    }

    @Override // com.google.common.graph.AbstractDirectedNetworkConnections, com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e2) {
        N nRemove = this.outEdgeMap.remove(e2);
        Preconditions.checkNotNull(nRemove);
        Multiset multiset = (Multiset) getReference(this.successorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(nRemove));
        }
        return nRemove;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> successors() {
        return Collections.unmodifiableSet(successorsMultiset().elementSet());
    }
}
