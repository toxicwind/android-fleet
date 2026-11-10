package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Immutable(containerOf = {"N", MtkTvRatingConvert2Goo.STR_CA_TV_E})
@Beta
public final class ImmutableNetwork<N, E> extends StandardNetwork<N, E> {

    /* JADX INFO: renamed from: com.google.common.graph.ImmutableNetwork$1, reason: invalid class name */
    public class AnonymousClass1 implements Function<E, N> {
        public final /* synthetic */ Network val$network;

        public AnonymousClass1(Network network) {
            this.val$network = network;
        }

        @Override // com.google.common.base.Function
        public N apply(E e2) {
            return this.val$network.incidentNodes(e2).source();
        }
    }

    /* JADX INFO: renamed from: com.google.common.graph.ImmutableNetwork$2, reason: invalid class name */
    public class AnonymousClass2 implements Function<E, N> {
        public final /* synthetic */ Network val$network;

        public AnonymousClass2(Network network) {
            this.val$network = network;
        }

        @Override // com.google.common.base.Function
        public N apply(E e2) {
            return this.val$network.incidentNodes(e2).target();
        }
    }

    /* JADX INFO: renamed from: com.google.common.graph.ImmutableNetwork$3, reason: invalid class name */
    public class AnonymousClass3 implements Function<E, N> {
        public final /* synthetic */ Network val$network;
        public final /* synthetic */ Object val$node;

        public AnonymousClass3(Network network, Object obj) {
            this.val$network = network;
            this.val$node = obj;
        }

        @Override // com.google.common.base.Function
        public N apply(E e2) {
            return this.val$network.incidentNodes(e2).adjacentNode(this.val$node);
        }
    }

    public static class Builder<N, E> {
        public final MutableNetwork<N, E> mutableNetwork;

        public Builder(NetworkBuilder<N, E> networkBuilder) {
            this.mutableNetwork = (MutableNetwork<N, E>) networkBuilder.build();
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addEdge(N n, N n2, E e2) {
            this.mutableNetwork.addEdge(n, n2, e2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addNode(N n) {
            this.mutableNetwork.addNode(n);
            return this;
        }

        public ImmutableNetwork<N, E> build() {
            return ImmutableNetwork.copyOf(this.mutableNetwork);
        }

        @CanIgnoreReturnValue
        public Builder<N, E> addEdge(EndpointPair<N> endpointPair, E e2) {
            this.mutableNetwork.addEdge(endpointPair, e2);
            return this;
        }
    }

    public ImmutableNetwork(Network<N, E> network) {
        super(NetworkBuilder.from(network), getNodeConnections(network), getEdgeToReferenceNode(network));
    }

    public static <N, E> Function<E, N> adjacentNodeFn(Network<N, E> network, N n) {
        return new AnonymousClass3(network, n);
    }

    public static <N, E> NetworkConnections<N, E> connectionsOf(Network<N, E> network, N n) {
        if (!network.isDirected()) {
            Maps.AsMapView asMapView = new Maps.AsMapView(network.incidentEdges(n), new AnonymousClass3(network, n));
            return network.allowsParallelEdges() ? UndirectedMultiNetworkConnections.ofImmutable(asMapView) : UndirectedNetworkConnections.ofImmutable(asMapView);
        }
        Maps.AsMapView asMapView2 = new Maps.AsMapView(network.inEdges(n), new AnonymousClass1(network));
        Maps.AsMapView asMapView3 = new Maps.AsMapView(network.outEdges(n), new AnonymousClass2(network));
        int size = network.edgesConnecting(n, n).size();
        return network.allowsParallelEdges() ? DirectedMultiNetworkConnections.ofImmutable(asMapView2, asMapView3, size) : DirectedNetworkConnections.ofImmutable(asMapView2, asMapView3, size);
    }

    public static <N, E> ImmutableNetwork<N, E> copyOf(Network<N, E> network) {
        return network instanceof ImmutableNetwork ? (ImmutableNetwork) network : new ImmutableNetwork<>(network);
    }

    public static <N, E> Map<E, N> getEdgeToReferenceNode(Network<N, E> network) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (E e2 : network.edges()) {
            builder.put(e2, network.incidentNodes(e2).nodeU());
        }
        return builder.build();
    }

    public static <N, E> Map<N, NetworkConnections<N, E>> getNodeConnections(Network<N, E> network) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N n : network.nodes()) {
            builder.put(n, connectionsOf(network, n));
        }
        return builder.build();
    }

    public static <N, E> Function<E, N> sourceNodeFn(Network<N, E> network) {
        return new AnonymousClass1(network);
    }

    public static <N, E> Function<E, N> targetNodeFn(Network<N, E> network) {
        return new AnonymousClass2(network);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public Set adjacentNodes(Object obj) {
        return checkedConnections(obj).adjacentNodes();
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public ElementOrder edgeOrder() {
        return this.edgeOrder;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set edgesConnecting(Object obj, Object obj2) {
        return super.edgesConnecting(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public Set inEdges(Object obj) {
        return checkedConnections(obj).inEdges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public Set incidentEdges(Object obj) {
        return checkedConnections(obj).incidentEdges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ EndpointPair incidentNodes(Object obj) {
        return super.incidentNodes(obj);
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public ElementOrder nodeOrder() {
        return this.nodeOrder;
    }

    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public /* bridge */ /* synthetic */ Set nodes() {
        return super.nodes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network
    public Set outEdges(Object obj) {
        return checkedConnections(obj).outEdges();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
    public Set predecessors(Object obj) {
        return checkedConnections(obj).predecessors();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.StandardNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
    public Set successors(Object obj) {
        return checkedConnections(obj).successors();
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public ImmutableGraph<N> asGraph() {
        return new ImmutableGraph<>(new AbstractNetwork.AnonymousClass1());
    }

    @Deprecated
    public static <N, E> ImmutableNetwork<N, E> copyOf(ImmutableNetwork<N, E> immutableNetwork) {
        if (immutableNetwork != null) {
            return immutableNetwork;
        }
        throw new NullPointerException();
    }
}
