package com.google.common.graph;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class MultiEdgesConnecting<E> extends AbstractSet<E> {
    public final Map<E, ?> outEdgeToNode;
    public final Object targetNode;

    public MultiEdgesConnecting(Map<E, ?> map, Object obj) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.outEdgeToNode = map;
        if (obj == null) {
            throw new NullPointerException();
        }
        this.targetNode = obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.targetNode.equals(this.outEdgeToNode.get(obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public UnmodifiableIterator<E> iterator() {
        final Iterator<Map.Entry<E, ?>> it = this.outEdgeToNode.entrySet().iterator();
        return new AbstractIterator<E>() { // from class: com.google.common.graph.MultiEdgesConnecting.1
            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (MultiEdgesConnecting.this.targetNode.equals(entry.getValue())) {
                        return (E) entry.getKey();
                    }
                }
                return endOfData();
            }
        };
    }
}
