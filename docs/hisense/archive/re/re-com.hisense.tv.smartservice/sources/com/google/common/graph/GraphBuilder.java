package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: loaded from: classes.dex */
@DoNotMock
@Beta
public final class GraphBuilder<N> extends AbstractGraphBuilder<N> {
    public GraphBuilder(boolean z) {
        super(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N1 extends N> GraphBuilder<N1> cast() {
        return this;
    }

    public static GraphBuilder<Object> directed() {
        return new GraphBuilder<>(true);
    }

    public static <N> GraphBuilder<N> from(Graph<N> graph) {
        return new GraphBuilder(graph.isDirected()).allowsSelfLoops(graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder()).incidentEdgeOrder(graph.incidentEdgeOrder());
    }

    public static GraphBuilder<Object> undirected() {
        return new GraphBuilder<>(false);
    }

    public GraphBuilder<N> allowsSelfLoops(boolean z) {
        this.allowsSelfLoops = z;
        return this;
    }

    public <N1 extends N> MutableGraph<N1> build() {
        return new StandardMutableGraph(this);
    }

    public GraphBuilder<N> copy() {
        GraphBuilder<N> graphBuilder = new GraphBuilder<>(this.directed);
        graphBuilder.allowsSelfLoops = this.allowsSelfLoops;
        graphBuilder.nodeOrder = this.nodeOrder;
        graphBuilder.expectedNodeCount = this.expectedNodeCount;
        graphBuilder.incidentEdgeOrder = this.incidentEdgeOrder;
        return graphBuilder;
    }

    public GraphBuilder<N> expectedNodeCount(int i) {
        Graphs.checkNonNegative(i);
        this.expectedNodeCount = Optional.of(Integer.valueOf(i));
        return this;
    }

    public <N1 extends N> ImmutableGraph.Builder<N1> immutable() {
        return new ImmutableGraph.Builder<>(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> GraphBuilder<N1> incidentEdgeOrder(ElementOrder<N1> elementOrder) {
        Preconditions.checkArgument(elementOrder.type() == ElementOrder.Type.UNORDERED || elementOrder.type() == ElementOrder.Type.STABLE, "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().", elementOrder);
        this.incidentEdgeOrder = elementOrder;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <N1 extends N> GraphBuilder<N1> nodeOrder(ElementOrder<N1> elementOrder) {
        if (elementOrder == 0) {
            throw new NullPointerException();
        }
        this.nodeOrder = elementOrder;
        return this;
    }
}
