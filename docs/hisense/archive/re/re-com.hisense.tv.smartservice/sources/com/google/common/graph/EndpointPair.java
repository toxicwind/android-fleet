package com.google.common.graph;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@Immutable(containerOf = {"N"})
@Beta
public abstract class EndpointPair<N> implements Iterable<N> {
    public final N nodeU;
    public final N nodeV;

    private static final class Ordered<N> extends EndpointPair<N> {
        public Ordered(N n, N n2) {
            super(n, n2);
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair endpointPair = (EndpointPair) obj;
            if (isOrdered() != endpointPair.isOrdered()) {
                return false;
            }
            return source().equals(endpointPair.source()) && target().equals(endpointPair.target());
        }

        @Override // com.google.common.graph.EndpointPair
        public int hashCode() {
            return Arrays.hashCode(new Object[]{source(), target()});
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean isOrdered() {
            return true;
        }

        @Override // com.google.common.graph.EndpointPair, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.graph.EndpointPair
        public N source() {
            return nodeU();
        }

        @Override // com.google.common.graph.EndpointPair
        public N target() {
            return nodeV();
        }

        public String toString() {
            String strValueOf = String.valueOf(source());
            String strValueOf2 = String.valueOf(target());
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 6, "<", strValueOf, " -> ", strValueOf2);
            sbA.append(">");
            return sbA.toString();
        }

        public /* synthetic */ Ordered(Object obj, Object obj2, AnonymousClass1 anonymousClass1) {
            super(obj, obj2);
        }
    }

    private static final class Unordered<N> extends EndpointPair<N> {
        public Unordered(N n, N n2) {
            super(n, n2);
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair endpointPair = (EndpointPair) obj;
            if (isOrdered() != endpointPair.isOrdered()) {
                return false;
            }
            if (nodeU().equals(endpointPair.nodeU())) {
                return nodeV().equals(endpointPair.nodeV());
            }
            return nodeU().equals(endpointPair.nodeV()) && nodeV().equals(endpointPair.nodeU());
        }

        @Override // com.google.common.graph.EndpointPair
        public int hashCode() {
            return nodeV().hashCode() + nodeU().hashCode();
        }

        @Override // com.google.common.graph.EndpointPair
        public boolean isOrdered() {
            return false;
        }

        @Override // com.google.common.graph.EndpointPair, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.iterator();
        }

        @Override // com.google.common.graph.EndpointPair
        public N source() {
            throw new UnsupportedOperationException(GraphConstants.NOT_AVAILABLE_ON_UNDIRECTED);
        }

        @Override // com.google.common.graph.EndpointPair
        public N target() {
            throw new UnsupportedOperationException(GraphConstants.NOT_AVAILABLE_ON_UNDIRECTED);
        }

        public String toString() {
            String strValueOf = String.valueOf(nodeU());
            String strValueOf2 = String.valueOf(nodeV());
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 4, "[", strValueOf, ", ", strValueOf2);
            sbA.append("]");
            return sbA.toString();
        }

        public /* synthetic */ Unordered(Object obj, Object obj2, AnonymousClass1 anonymousClass1) {
            super(obj, obj2);
        }
    }

    public static <N> EndpointPair<N> of(Graph<?> graph, N n, N n2) {
        return graph.isDirected() ? ordered(n, n2) : unordered(n, n2);
    }

    public static <N> EndpointPair<N> ordered(N n, N n2) {
        return new Ordered(n, n2, null);
    }

    public static <N> EndpointPair<N> unordered(N n, N n2) {
        return new Unordered(n2, n, null);
    }

    public final N adjacentNode(Object obj) {
        if (obj.equals(this.nodeU)) {
            return this.nodeV;
        }
        if (obj.equals(this.nodeV)) {
            return this.nodeU;
        }
        String strValueOf = String.valueOf(this);
        String strValueOf2 = String.valueOf(obj);
        throw new IllegalArgumentException(a.b(strValueOf2.length() + strValueOf.length() + 36, "EndpointPair ", strValueOf, " does not contain node ", strValueOf2));
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract boolean isOrdered();

    public final N nodeU() {
        return this.nodeU;
    }

    public final N nodeV() {
        return this.nodeV;
    }

    public abstract N source();

    public abstract N target();

    public EndpointPair(N n, N n2) {
        if (n == null) {
            throw new NullPointerException();
        }
        this.nodeU = n;
        if (n2 == null) {
            throw new NullPointerException();
        }
        this.nodeV = n2;
    }

    public static <N> EndpointPair<N> of(Network<?, ?> network, N n, N n2) {
        return network.isDirected() ? ordered(n, n2) : unordered(n, n2);
    }

    @Override // java.lang.Iterable
    public final UnmodifiableIterator<N> iterator() {
        Object[] objArr = {this.nodeU, this.nodeV};
        return Iterators.forArray(objArr, 0, objArr.length, 0);
    }
}
