package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class NetworkBuilder<N, E> extends AbstractGraphBuilder<N> {
    public boolean allowsParallelEdges;
    public ElementOrder<? super E> edgeOrder;
    public Optional<Integer> expectedEdgeCount;

    public NetworkBuilder(boolean z) {
        super(z);
        this.allowsParallelEdges = false;
        this.edgeOrder = ElementOrder.insertion();
        this.expectedEdgeCount = Absent.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N1 extends N, E1 extends E> NetworkBuilder<N1, E1> cast() {
        return this;
    }

    public static NetworkBuilder<Object, Object> directed() {
        return new NetworkBuilder<>(true);
    }

    public static <N, E> NetworkBuilder<N, E> from(Network<N, E> network) {
        return new NetworkBuilder(network.isDirected()).allowsParallelEdges(network.allowsParallelEdges()).allowsSelfLoops(network.allowsSelfLoops()).nodeOrder(network.nodeOrder()).edgeOrder(network.edgeOrder());
    }

    public static NetworkBuilder<Object, Object> undirected() {
        return new NetworkBuilder<>(false);
    }

    public NetworkBuilder<N, E> allowsParallelEdges(boolean z) {
        this.allowsParallelEdges = z;
        return this;
    }

    public NetworkBuilder<N, E> allowsSelfLoops(boolean z) {
        this.allowsSelfLoops = z;
        return this;
    }

    public <N1 extends N, E1 extends E> MutableNetwork<N1, E1> build() {
        return new StandardMutableNetwork(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <E1 extends E> NetworkBuilder<N, E1> edgeOrder(ElementOrder<E1> elementOrder) {
        if (elementOrder == 0) {
            throw new NullPointerException();
        }
        this.edgeOrder = elementOrder;
        return this;
    }

    public NetworkBuilder<N, E> expectedEdgeCount(int i) {
        Graphs.checkNonNegative(i);
        this.expectedEdgeCount = Optional.of(Integer.valueOf(i));
        return this;
    }

    public NetworkBuilder<N, E> expectedNodeCount(int i) {
        Graphs.checkNonNegative(i);
        this.expectedNodeCount = Optional.of(Integer.valueOf(i));
        return this;
    }

    public <N1 extends N, E1 extends E> ImmutableNetwork.Builder<N1, E1> immutable() {
        return new ImmutableNetwork.Builder<>(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> NetworkBuilder<N1, E> nodeOrder(ElementOrder<N1> elementOrder) {
        if (elementOrder == 0) {
            throw new NullPointerException();
        }
        this.nodeOrder = elementOrder;
        return this;
    }
}
