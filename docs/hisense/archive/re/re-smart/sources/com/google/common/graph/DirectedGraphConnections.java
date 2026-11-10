package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    public static final Object PRED = new Object();
    public final Map<N, Object> adjacentNodeValues;
    public final List<NodeConnection<N>> orderedNodeConnections;
    public int predecessorCount;
    public int successorCount;

    /* JADX INFO: renamed from: com.google.common.graph.DirectedGraphConnections$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
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

    private static abstract class NodeConnection<N> {
        public final N node;

        static final class Pred<N> extends NodeConnection<N> {
            public Pred(N n) {
                super(n);
            }

            public boolean equals(Object obj) {
                if (obj instanceof Pred) {
                    return this.node.equals(((Pred) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return this.node.hashCode() + Pred.class.hashCode();
            }
        }

        static final class Succ<N> extends NodeConnection<N> {
            public Succ(N n) {
                super(n);
            }

            public boolean equals(Object obj) {
                if (obj instanceof Succ) {
                    return this.node.equals(((Succ) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return this.node.hashCode() + Succ.class.hashCode();
            }
        }

        public NodeConnection(N n) {
            if (n == null) {
                throw new NullPointerException();
            }
            this.node = n;
        }
    }

    private static final class PredAndSucc {
        public final Object successorValue;

        public PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    public DirectedGraphConnections(Map<N, Object> map, List<NodeConnection<N>> list, int i, int i2) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.adjacentNodeValues = map;
        this.orderedNodeConnections = list;
        Graphs.checkNonNegative(i);
        this.predecessorCount = i;
        Graphs.checkNonNegative(i2);
        this.successorCount = i2;
        Preconditions.checkState(i <= map.size() && i2 <= map.size());
    }

    public static boolean isPredecessor(Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    public static boolean isSuccessor(Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }

    public static <N, V> DirectedGraphConnections<N, V> of(ElementOrder<N> elementOrder) {
        ArrayList arrayList;
        int iOrdinal = elementOrder.type().ordinal();
        if (iOrdinal == 0) {
            arrayList = null;
        } else {
            if (iOrdinal != 1) {
                throw new AssertionError(elementOrder.type());
            }
            arrayList = new ArrayList();
        }
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), arrayList, 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> DirectedGraphConnections<N, V> ofImmutable(N n, Iterable<EndpointPair<N>> iterable, Function<N, V> function) {
        if (n == null) {
            throw new NullPointerException();
        }
        if (function == null) {
            throw new NullPointerException();
        }
        HashMap map = new HashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        int i2 = 0;
        for (EndpointPair<N> endpointPair : iterable) {
            if (endpointPair.nodeU().equals(n) && endpointPair.nodeV().equals(n)) {
                map.put(n, new PredAndSucc(function.apply(n)));
                builder.add(new NodeConnection.Pred(n));
                builder.add(new NodeConnection.Succ(n));
                i++;
            } else if (endpointPair.nodeV().equals(n)) {
                N nNodeU = endpointPair.nodeU();
                Object objPut = map.put(nNodeU, PRED);
                if (objPut != null) {
                    map.put(nNodeU, new PredAndSucc(objPut));
                }
                builder.add(new NodeConnection.Pred(nNodeU));
                i++;
            } else {
                Preconditions.checkArgument(endpointPair.nodeU().equals(n));
                N nNodeV = endpointPair.nodeV();
                V vApply = function.apply(nNodeV);
                Object objPut2 = map.put(nNodeV, vApply);
                if (objPut2 != null) {
                    Preconditions.checkArgument(objPut2 == PRED);
                    map.put(nNodeV, new PredAndSucc(vApply));
                }
                builder.add(new NodeConnection.Succ(nNodeV));
            }
            i2++;
        }
        return new DirectedGraphConnections<>(map, builder.build(), i, i2);
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N n, V v) {
        Object objPut = this.adjacentNodeValues.put(n, PRED);
        boolean z = false;
        if (objPut == null) {
            z = true;
        } else if (objPut instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n, objPut);
        } else if (objPut != PRED) {
            this.adjacentNodeValues.put(n, new PredAndSucc(objPut));
            z = true;
        }
        if (z) {
            int i = this.predecessorCount + 1;
            this.predecessorCount = i;
            Graphs.checkPositive(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.add(new NodeConnection.Pred(n));
            }
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N n, V v) {
        Object obj = (V) this.adjacentNodeValues.put(n, v);
        if (obj == null) {
            obj = (V) null;
        } else if (obj instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n, new PredAndSucc(v));
            obj = (V) ((PredAndSucc) obj).successorValue;
        } else if (obj == PRED) {
            this.adjacentNodeValues.put(n, new PredAndSucc(v));
            obj = (V) null;
        }
        if (obj == null) {
            int i = this.successorCount + 1;
            this.successorCount = i;
            Graphs.checkPositive(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.add(new NodeConnection.Succ(n));
            }
        }
        return (V) obj;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return this.orderedNodeConnections == null ? Collections.unmodifiableSet(this.adjacentNodeValues.keySet()) : new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                final Iterator it = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                final HashSet hashSet = new HashSet();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it.next();
                            if (hashSet.add(nodeConnection.node)) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N n) {
        if (n == null) {
            throw new NullPointerException();
        }
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        final Iterator itConcat = list == null ? Iterators.concat(new Iterators.AnonymousClass6(predecessors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.4
            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(N n2) {
                return EndpointPair.ordered(n2, n);
            }
        }), new Iterators.AnonymousClass6(successors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.5
            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(N n2) {
                return EndpointPair.ordered(n, n2);
            }
        })) : new Iterators.AnonymousClass6(list.iterator(), new Function<NodeConnection<N>, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.6
            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(NodeConnection<N> nodeConnection) {
                return nodeConnection instanceof NodeConnection.Succ ? EndpointPair.ordered(n, nodeConnection.node) : EndpointPair.ordered(nodeConnection.node, n);
            }
        });
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return new AbstractIterator<EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.7
            @Override // com.google.common.collect.AbstractIterator
            public EndpointPair<N> computeNext() {
                while (itConcat.hasNext()) {
                    EndpointPair<N> endpointPair = (EndpointPair) itConcat.next();
                    if (!endpointPair.nodeU().equals(endpointPair.nodeV()) || !atomicBoolean.getAndSet(true)) {
                        return endpointPair;
                    }
                }
                return endOfData();
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                        @Override // com.google.common.collect.AbstractIterator
                        public N computeNext() {
                            while (it.hasNext()) {
                                Map.Entry entry = (Map.Entry) it.next();
                                if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator it2 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.2
                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it2.next();
                            if (nodeConnection instanceof NodeConnection.Pred) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0025  */
    /* JADX WARN: Code duplicated, block: B:14:0x0031  */
    /* JADX WARN: Code duplicated, block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:19:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N n) {
        boolean z;
        List<NodeConnection<N>> list;
        if (n == null) {
            throw new NullPointerException();
        }
        Object obj = this.adjacentNodeValues.get(n);
        if (obj != PRED) {
            if (obj instanceof PredAndSucc) {
                this.adjacentNodeValues.put(n, ((PredAndSucc) obj).successorValue);
            } else {
                z = false;
            }
            if (z) {
                int i = this.predecessorCount - 1;
                this.predecessorCount = i;
                Graphs.checkNonNegative(i);
                list = this.orderedNodeConnections;
                if (list != null) {
                    list.remove(new NodeConnection.Pred(n));
                }
            }
        }
        this.adjacentNodeValues.remove(n);
        z = true;
        if (z) {
            int i2 = this.predecessorCount - 1;
            this.predecessorCount = i2;
            Graphs.checkNonNegative(i2);
            list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Pred(n));
            }
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(Object obj) {
        Object obj2;
        if (obj == null) {
            throw new NullPointerException();
        }
        Object obj3 = (V) this.adjacentNodeValues.get(obj);
        if (obj3 == null || obj3 == (obj2 = PRED)) {
            obj3 = (V) null;
        } else if (obj3 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            obj3 = (V) ((PredAndSucc) obj3).successorValue;
        } else {
            this.adjacentNodeValues.remove(obj);
        }
        if (obj3 != null) {
            int i = this.successorCount - 1;
            this.successorCount = i;
            Graphs.checkNonNegative(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Succ(obj));
            }
        }
        return (V) obj3;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.3
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.1
                        @Override // com.google.common.collect.AbstractIterator
                        public N computeNext() {
                            while (it.hasNext()) {
                                Map.Entry entry = (Map.Entry) it.next();
                                if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator it2 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.2
                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (it2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it2.next();
                            if (nodeConnection instanceof NodeConnection.Succ) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.GraphConnections
    public V value(N n) {
        if (n == null) {
            throw new NullPointerException();
        }
        V v = (V) this.adjacentNodeValues.get(n);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }
}
