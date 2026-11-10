package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class EndpointPairIterator<N> extends AbstractIterator<EndpointPair<N>> {
    public final BaseGraph<N> graph;
    public N node;
    public final Iterator<N> nodeIterator;
    public Iterator<N> successorIterator;

    private static final class Directed<N> extends EndpointPairIterator<N> {
        public Directed(BaseGraph<N> baseGraph) {
            super(baseGraph);
        }

        public /* synthetic */ Directed(BaseGraph baseGraph, AnonymousClass1 anonymousClass1) {
            super(baseGraph);
        }

        @Override // com.google.common.collect.AbstractIterator
        public EndpointPair<N> computeNext() {
            while (!this.successorIterator.hasNext()) {
                if (!advance()) {
                    return endOfData();
                }
            }
            return EndpointPair.ordered(this.node, this.successorIterator.next());
        }
    }

    private static final class Undirected<N> extends EndpointPairIterator<N> {
        public Set<N> visitedNodes;

        public Undirected(BaseGraph<N> baseGraph) {
            super(baseGraph);
            this.visitedNodes = Sets.newHashSetWithExpectedSize(baseGraph.nodes().size());
        }

        @Override // com.google.common.collect.AbstractIterator
        public EndpointPair<N> computeNext() {
            while (true) {
                if (this.successorIterator.hasNext()) {
                    N next = this.successorIterator.next();
                    if (!this.visitedNodes.contains(next)) {
                        return EndpointPair.unordered(this.node, next);
                    }
                } else {
                    this.visitedNodes.add(this.node);
                    if (!advance()) {
                        this.visitedNodes = null;
                        return endOfData();
                    }
                }
            }
        }
    }

    public static <N> EndpointPairIterator<N> of(BaseGraph<N> baseGraph) {
        return baseGraph.isDirected() ? new Directed(baseGraph, null) : new Undirected(baseGraph);
    }

    public final boolean advance() {
        Preconditions.checkState(!this.successorIterator.hasNext());
        if (!this.nodeIterator.hasNext()) {
            return false;
        }
        this.node = this.nodeIterator.next();
        this.successorIterator = this.graph.successors((Object) this.node).iterator();
        return true;
    }

    public EndpointPairIterator(BaseGraph<N> baseGraph) {
        this.node = null;
        this.successorIterator = ImmutableSet.of().iterator();
        this.graph = baseGraph;
        this.nodeIterator = baseGraph.nodes().iterator();
    }
}
