package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface NetworkConnections<N, E> {
    void addInEdge(E e2, N n, boolean z);

    void addOutEdge(E e2, N n);

    N adjacentNode(E e2);

    Set<N> adjacentNodes();

    Set<E> edgesConnecting(N n);

    Set<E> inEdges();

    Set<E> incidentEdges();

    Set<E> outEdges();

    Set<N> predecessors();

    @CanIgnoreReturnValue
    N removeInEdge(E e2, boolean z);

    @CanIgnoreReturnValue
    N removeOutEdge(E e2);

    Set<N> successors();
}
