package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBaseGraph<N> implements BaseGraph<N> {

    /* JADX INFO: renamed from: com.google.common.graph.AbstractBaseGraph$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractSet<EndpointPair<N>> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair<?> endpointPair = (EndpointPair) obj;
            return AbstractBaseGraph.this.isOrderingCompatible(endpointPair) && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) && AbstractBaseGraph.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<EndpointPair<N>> iterator() {
            return EndpointPairIterator.of(AbstractBaseGraph.this);
        }
    }

    @Override // com.google.common.graph.BaseGraph
    public int degree(N n) {
        if (isDirected()) {
            return IntMath.saturatedAdd(predecessors((Object) n).size(), successors((Object) n).size());
        }
        Set<N> setAdjacentNodes = adjacentNodes(n);
        return IntMath.saturatedAdd(setAdjacentNodes.size(), (allowsSelfLoops() && setAdjacentNodes.contains(n)) ? 1 : 0);
    }

    public long edgeCount() {
        Iterator<N> it = nodes().iterator();
        long jDegree = 0;
        while (it.hasNext()) {
            jDegree += (long) degree(it.next());
        }
        Preconditions.checkState((1 & jDegree) == 0);
        return jDegree >>> 1;
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> edges() {
        return new AnonymousClass1();
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n, N n2) {
        if (n == null) {
            throw new NullPointerException();
        }
        if (n2 != null) {
            return nodes().contains(n) && successors((Object) n).contains(n2);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.graph.BaseGraph
    public int inDegree(N n) {
        return isDirected() ? predecessors((Object) n).size() : degree(n);
    }

    @Override // com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.unordered();
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        if (n == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(nodes().contains(n), GraphConstants.NODE_NOT_IN_GRAPH, n);
        return new IncidentEdgeSet<N>(this, this, n) { // from class: com.google.common.graph.AbstractBaseGraph.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return this.graph.isDirected() ? Iterators.unmodifiableIterator(Iterators.concat(new Iterators.AnonymousClass6(this.graph.predecessors((Object) this.node).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.1
                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N n2) {
                        return EndpointPair.ordered(n2, AnonymousClass2.this.node);
                    }
                }), new Iterators.AnonymousClass6(Sets.difference(this.graph.successors((Object) this.node), ImmutableSet.of(this.node)).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.2
                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N n2) {
                        return EndpointPair.ordered(AnonymousClass2.this.node, n2);
                    }
                }))) : Iterators.unmodifiableIterator(new Iterators.AnonymousClass6(this.graph.adjacentNodes(this.node).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.3
                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N n2) {
                        return EndpointPair.unordered(AnonymousClass2.this.node, n2);
                    }
                }));
            }
        };
    }

    public final boolean isOrderingCompatible(EndpointPair<?> endpointPair) {
        return endpointPair.isOrdered() || !isDirected();
    }

    @Override // com.google.common.graph.BaseGraph
    public int outDegree(N n) {
        return isDirected() ? successors((Object) n).size() : degree(n);
    }

    public final void validateEndpoints(EndpointPair<?> endpointPair) {
        if (endpointPair == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(isOrderingCompatible(endpointPair), GraphConstants.ENDPOINTS_MISMATCH);
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        if (endpointPair != null) {
            if (!isOrderingCompatible(endpointPair)) {
                return false;
            }
            N nNodeU = endpointPair.nodeU();
            return nodes().contains(nNodeU) && successors((Object) nNodeU).contains(endpointPair.nodeV());
        }
        throw new NullPointerException();
    }
}
