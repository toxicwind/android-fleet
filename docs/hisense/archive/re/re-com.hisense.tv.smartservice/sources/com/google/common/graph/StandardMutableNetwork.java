package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class StandardMutableNetwork<N, E> extends StandardNetwork<N, E> implements MutableNetwork<N, E> {
    public StandardMutableNetwork(NetworkBuilder<? super N, ? super E> networkBuilder) {
        super(networkBuilder);
    }

    @CanIgnoreReturnValue
    private NetworkConnections<N, E> addNodeInternal(N n) {
        NetworkConnections<N, E> networkConnectionsNewConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(n, networkConnectionsNewConnections) == null);
        return networkConnectionsNewConnections;
    }

    private NetworkConnections<N, E> newConnections() {
        if (isDirected()) {
            return allowsParallelEdges() ? DirectedMultiNetworkConnections.of() : DirectedNetworkConnections.of();
        }
        return allowsParallelEdges() ? UndirectedMultiNetworkConnections.of() : UndirectedNetworkConnections.of();
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(N n, N n2, E e2) {
        Preconditions.checkNotNull(n, "nodeU");
        Preconditions.checkNotNull(n2, "nodeV");
        Preconditions.checkNotNull(e2, "edge");
        if (containsEdge(e2)) {
            EndpointPair<N> endpointPairIncidentNodes = incidentNodes(e2);
            EndpointPair endpointPairOf = EndpointPair.of(this, n, n2);
            Preconditions.checkArgument(endpointPairIncidentNodes.equals(endpointPairOf), GraphConstants.REUSING_EDGE, e2, endpointPairIncidentNodes, endpointPairOf);
            return false;
        }
        NetworkConnections<N, E> networkConnectionsAddNodeInternal = this.nodeConnections.get(n);
        if (!allowsParallelEdges()) {
            Preconditions.checkArgument(networkConnectionsAddNodeInternal == null || !networkConnectionsAddNodeInternal.successors().contains(n2), GraphConstants.PARALLEL_EDGES_NOT_ALLOWED, n, n2);
        }
        boolean zEquals = n.equals(n2);
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!zEquals, GraphConstants.SELF_LOOPS_NOT_ALLOWED, n);
        }
        if (networkConnectionsAddNodeInternal == null) {
            networkConnectionsAddNodeInternal = addNodeInternal(n);
        }
        networkConnectionsAddNodeInternal.addOutEdge(e2, n2);
        NetworkConnections<N, E> networkConnectionsAddNodeInternal2 = this.nodeConnections.get(n2);
        if (networkConnectionsAddNodeInternal2 == null) {
            networkConnectionsAddNodeInternal2 = addNodeInternal(n2);
        }
        networkConnectionsAddNodeInternal2.addInEdge(e2, n, zEquals);
        this.edgeToReferenceNode.put(e2, n);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addNode(N n) {
        Preconditions.checkNotNull(n, "node");
        if (containsNode(n)) {
            return false;
        }
        addNodeInternal(n);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeEdge(E e2) {
        Preconditions.checkNotNull(e2, "edge");
        N n = this.edgeToReferenceNode.get(e2);
        boolean z = false;
        if (n == null) {
            return false;
        }
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(n);
        N nAdjacentNode = networkConnections.adjacentNode(e2);
        NetworkConnections<N, E> networkConnections2 = this.nodeConnections.get(nAdjacentNode);
        networkConnections.removeOutEdge(e2);
        if (allowsSelfLoops() && n.equals(nAdjacentNode)) {
            z = true;
        }
        networkConnections2.removeInEdge(e2, z);
        this.edgeToReferenceNode.remove(e2);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean removeNode(N n) {
        Preconditions.checkNotNull(n, "node");
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(n);
        if (networkConnections == null) {
            return false;
        }
        UnmodifiableIterator<E> it = ImmutableList.copyOf((Collection) networkConnections.incidentEdges()).iterator();
        while (it.hasNext()) {
            removeEdge(it.next());
        }
        this.nodeConnections.remove(n);
        return true;
    }

    @Override // com.google.common.graph.MutableNetwork
    @CanIgnoreReturnValue
    public boolean addEdge(EndpointPair<N> endpointPair, E e2) {
        validateEndpoints(endpointPair);
        return addEdge(endpointPair.nodeU(), endpointPair.nodeV(), e2);
    }
}
