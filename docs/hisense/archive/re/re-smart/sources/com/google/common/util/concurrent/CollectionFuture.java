package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {
    public List<Present<V>> values;

    static final class ListFuture<V> extends CollectionFuture<V, List<V>> {
        public ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
            super(immutableCollection, z);
            init();
        }

        @Override // com.google.common.util.concurrent.CollectionFuture
        public List<V> combine(List<Present<V>> list) {
            ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
            Iterator<Present<V>> it = list.iterator();
            while (it.hasNext()) {
                Present<V> next = it.next();
                arrayListNewArrayListWithCapacity.add(next != null ? next.value : null);
            }
            return Collections.unmodifiableList(arrayListNewArrayListWithCapacity);
        }
    }

    private static final class Present<V> {
        public V value;

        public Present(V v) {
            this.value = v;
        }
    }

    public CollectionFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
        super(immutableCollection, z, true);
        List<Present<V>> listOf = immutableCollection.isEmpty() ? ImmutableList.of() : Lists.newArrayListWithCapacity(immutableCollection.size());
        for (int i = 0; i < immutableCollection.size(); i++) {
            listOf.add(null);
        }
        this.values = listOf;
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void collectOneValue(int i, V v) {
        List<Present<V>> list = this.values;
        if (list != null) {
            list.set(i, new Present<>(v));
        }
    }

    public abstract C combine(List<Present<V>> list);

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void handleAllCompleted() {
        List<Present<V>> list = this.values;
        if (list != null) {
            set(combine(list));
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void releaseResources(AggregateFuture.ReleaseResourcesReason releaseResourcesReason) {
        if (releaseResourcesReason == null) {
            throw new NullPointerException();
        }
        this.futures = null;
        this.values = null;
    }
}
