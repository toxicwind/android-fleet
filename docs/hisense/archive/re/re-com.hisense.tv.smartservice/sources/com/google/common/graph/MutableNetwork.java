package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
@Beta
public interface MutableNetwork<N, E> extends Network<N, E> {
    @CanIgnoreReturnValue
    boolean addEdge(EndpointPair<N> endpointPair, E e2);

    @CanIgnoreReturnValue
    boolean addEdge(N n, N n2, E e2);

    @CanIgnoreReturnValue
    boolean addNode(N n);

    @CanIgnoreReturnValue
    boolean removeEdge(E e2);

    @CanIgnoreReturnValue
    boolean removeNode(N n);
}
