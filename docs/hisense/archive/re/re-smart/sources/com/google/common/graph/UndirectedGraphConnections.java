package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class UndirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    public final Map<N, V> adjacentNodeValues;

    /* JADX INFO: renamed from: com.google.common.graph.UndirectedGraphConnections$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type = new int[ElementOrder.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public UndirectedGraphConnections(Map<N, V> map) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.adjacentNodeValues = map;
    }

    public static <N, V> UndirectedGraphConnections<N, V> of(ElementOrder<N> elementOrder) {
        int iOrdinal = elementOrder.type().ordinal();
        if (iOrdinal == 0) {
            return new UndirectedGraphConnections<>(new HashMap(2, 1.0f));
        }
        if (iOrdinal == 1) {
            return new UndirectedGraphConnections<>(new LinkedHashMap(2, 1.0f));
        }
        throw new AssertionError(elementOrder.type());
    }

    public static <N, V> UndirectedGraphConnections<N, V> ofImmutable(Map<N, V> map) {
        return new UndirectedGraphConnections<>(ImmutableMap.copyOf((Map) map));
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N n, V v) {
        addSuccessor(n, v);
    }

    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N n, V v) {
        return this.adjacentNodeValues.put(n, v);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N n) {
        return new Iterators.AnonymousClass6(this.adjacentNodeValues.keySet().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.UndirectedGraphConnections.1
            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(N n2) {
                return EndpointPair.unordered(n, n2);
            }
        });
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N n) {
        removeSuccessor(n);
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(N n) {
        return this.adjacentNodeValues.remove(n);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.GraphConnections
    public V value(N n) {
        return this.adjacentNodeValues.get(n);
    }
}
