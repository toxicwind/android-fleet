package com.google.common.graph;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Beta
public abstract class AbstractNetwork<N, E> implements Network<N, E> {

    /* JADX INFO: renamed from: com.google.common.graph.AbstractNetwork$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractGraph<N> {
        public AnonymousClass1() {
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public Set<N> adjacentNodes(N n) {
            return AbstractNetwork.this.adjacentNodes(n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public boolean allowsSelfLoops() {
            return AbstractNetwork.this.allowsSelfLoops();
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public Set<EndpointPair<N>> edges() {
            return AbstractNetwork.this.allowsParallelEdges() ? new AbstractBaseGraph.AnonymousClass1() : new AbstractSet<EndpointPair<N>>() { // from class: com.google.common.graph.AbstractNetwork.1.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean contains(Object obj) {
                    if (!(obj instanceof EndpointPair)) {
                        return false;
                    }
                    EndpointPair<?> endpointPair = (EndpointPair) obj;
                    return AnonymousClass1.this.isOrderingCompatible(endpointPair) && AnonymousClass1.this.nodes().contains(endpointPair.nodeU()) && AnonymousClass1.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV());
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<EndpointPair<N>> iterator() {
                    return new Iterators.AnonymousClass6(AbstractNetwork.this.edges().iterator(), new Function<E, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractNetwork.1.1.1
                        @Override // com.google.common.base.Function
                        public EndpointPair<N> apply(E e2) {
                            return AbstractNetwork.this.incidentNodes(e2);
                        }
                    });
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return AbstractNetwork.this.edges().size();
                }
            };
        }

        @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public ElementOrder<N> incidentEdgeOrder() {
            return ElementOrder.unordered();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public boolean isDirected() {
            return AbstractNetwork.this.isDirected();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public ElementOrder<N> nodeOrder() {
            return AbstractNetwork.this.nodeOrder();
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
        public Set<N> nodes() {
            return AbstractNetwork.this.nodes();
        }

        @Override // com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n) {
            return AbstractNetwork.this.predecessors((Object) n);
        }

        @Override // com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n) {
            return AbstractNetwork.this.successors((Object) n);
        }
    }

    /* JADX INFO: renamed from: com.google.common.graph.AbstractNetwork$2, reason: invalid class name */
    public class AnonymousClass2 implements Predicate<E> {
        public final /* synthetic */ Object val$nodePresent;
        public final /* synthetic */ Object val$nodeToCheck;

        public AnonymousClass2(Object obj, Object obj2) {
            this.val$nodePresent = obj;
            this.val$nodeToCheck = obj2;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(E e2) {
            return AbstractNetwork.this.incidentNodes(e2).adjacentNode(this.val$nodePresent).equals(this.val$nodeToCheck);
        }
    }

    private Predicate<E> connectedPredicate(N n, N n2) {
        return new AnonymousClass2(n, n2);
    }

    public static <N, E> Map<E, EndpointPair<N>> edgeIncidentNodesMap(final Network<N, E> network) {
        return new Maps.AsMapView(network.edges(), new Function<E, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractNetwork.3
            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(E e2) {
                return network.incidentNodes(e2);
            }
        });
    }

    @Override // com.google.common.graph.Network
    public Set<E> adjacentEdges(E e2) {
        EndpointPair<N> endpointPairIncidentNodes = incidentNodes(e2);
        return Sets.difference(Sets.union(incidentEdges(endpointPairIncidentNodes.nodeU()), incidentEdges(endpointPairIncidentNodes.nodeV())), ImmutableSet.of((Object) e2));
    }

    @Override // com.google.common.graph.Network
    public Graph<N> asGraph() {
        return new AnonymousClass1();
    }

    @Override // com.google.common.graph.Network
    public int degree(N n) {
        return isDirected() ? IntMath.saturatedAdd(inEdges(n).size(), outEdges(n).size()) : IntMath.saturatedAdd(incidentEdges(n).size(), edgesConnecting(n, n).size());
    }

    @Override // com.google.common.graph.Network
    public E edgeConnectingOrNull(N n, N n2) {
        Set<E> setEdgesConnecting = edgesConnecting(n, n2);
        int size = setEdgesConnecting.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return setEdgesConnecting.iterator().next();
        }
        throw new IllegalArgumentException(String.format(GraphConstants.MULTIPLE_EDGES_CONNECTING, n, n2));
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        Set<E> setOutEdges = outEdges(n);
        Set<E> setInEdges = inEdges(n2);
        return setOutEdges.size() <= setInEdges.size() ? Collections.unmodifiableSet(Sets.filter(setOutEdges, new AnonymousClass2(n, n2))) : Collections.unmodifiableSet(Sets.filter(setInEdges, new AnonymousClass2(n2, n)));
    }

    @Override // com.google.common.graph.Network
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        return isDirected() == network.isDirected() && nodes().equals(network.nodes()) && edgeIncidentNodesMap(this).equals(edgeIncidentNodesMap(network));
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n, N n2) {
        if (n == null) {
            throw new NullPointerException();
        }
        if (n2 != null) {
            return nodes().contains(n) && successors((Object) n).contains(n2);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.graph.Network
    public final int hashCode() {
        return edgeIncidentNodesMap(this).hashCode();
    }

    @Override // com.google.common.graph.Network
    public int inDegree(N n) {
        return isDirected() ? inEdges(n).size() : degree(n);
    }

    public final boolean isOrderingCompatible(EndpointPair<?> endpointPair) {
        return endpointPair.isOrdered() || !isDirected();
    }

    @Override // com.google.common.graph.Network
    public int outDegree(N n) {
        return isDirected() ? outEdges(n).size() : degree(n);
    }

    public String toString() {
        boolean zIsDirected = isDirected();
        boolean zAllowsParallelEdges = allowsParallelEdges();
        boolean zAllowsSelfLoops = allowsSelfLoops();
        String strValueOf = String.valueOf(nodes());
        String strValueOf2 = String.valueOf(edgeIncidentNodesMap(this));
        StringBuilder sb = new StringBuilder(strValueOf2.length() + strValueOf.length() + 87);
        sb.append("isDirected: ");
        sb.append(zIsDirected);
        sb.append(", allowsParallelEdges: ");
        sb.append(zAllowsParallelEdges);
        sb.append(", allowsSelfLoops: ");
        sb.append(zAllowsSelfLoops);
        sb.append(", nodes: ");
        sb.append(strValueOf);
        return a.a(sb, ", edges: ", strValueOf2);
    }

    public final void validateEndpoints(EndpointPair<?> endpointPair) {
        if (endpointPair == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(isOrderingCompatible(endpointPair), GraphConstants.ENDPOINTS_MISMATCH);
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        if (endpointPair != null) {
            if (isOrderingCompatible(endpointPair)) {
                return hasEdgeConnecting(endpointPair.nodeU(), endpointPair.nodeV());
            }
            return false;
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.graph.Network
    public E edgeConnectingOrNull(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return edgeConnectingOrNull(endpointPair.nodeU(), endpointPair.nodeV());
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return edgesConnecting(endpointPair.nodeU(), endpointPair.nodeV());
    }
}
