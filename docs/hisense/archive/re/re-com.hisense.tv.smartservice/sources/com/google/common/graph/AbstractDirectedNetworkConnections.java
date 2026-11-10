package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    public final Map<E, N> inEdgeMap;
    public final Map<E, N> outEdgeMap;
    public int selfLoopCount;

    public AbstractDirectedNetworkConnections(Map<E, N> map, Map<E, N> map2, int i) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.inEdgeMap = map;
        if (map2 == null) {
            throw new NullPointerException();
        }
        this.outEdgeMap = map2;
        Graphs.checkNonNegative(i);
        this.selfLoopCount = i;
        Preconditions.checkState(i <= map.size() && i <= map2.size());
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E e2, N n, boolean z) {
        if (e2 == null) {
            throw new NullPointerException();
        }
        if (n == null) {
            throw new NullPointerException();
        }
        if (z) {
            int i = this.selfLoopCount + 1;
            this.selfLoopCount = i;
            Graphs.checkPositive(i);
        }
        Preconditions.checkState(this.inEdgeMap.put(e2, n) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E e2, N n) {
        if (e2 == null) {
            throw new NullPointerException();
        }
        if (n == null) {
            throw new NullPointerException();
        }
        Preconditions.checkState(this.outEdgeMap.put(e2, n) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E e2) {
        N n = this.outEdgeMap.get(e2);
        Preconditions.checkNotNull(n);
        return n;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.inEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return new AbstractSet<E>() { // from class: com.google.common.graph.AbstractDirectedNetworkConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                AbstractDirectedNetworkConnections abstractDirectedNetworkConnections = AbstractDirectedNetworkConnections.this;
                return Iterators.unmodifiableIterator((abstractDirectedNetworkConnections.selfLoopCount == 0 ? FluentIterable.concat(abstractDirectedNetworkConnections.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet()) : Sets.union(abstractDirectedNetworkConnections.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet())).iterator());
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.outEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E e2, boolean z) {
        if (z) {
            int i = this.selfLoopCount - 1;
            this.selfLoopCount = i;
            Graphs.checkNonNegative(i);
        }
        N nRemove = this.inEdgeMap.remove(e2);
        Preconditions.checkNotNull(nRemove);
        return nRemove;
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e2) {
        N nRemove = this.outEdgeMap.remove(e2);
        Preconditions.checkNotNull(nRemove);
        return nRemove;
    }
}
