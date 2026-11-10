package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class ForwardingListMultimap<K, V> extends ForwardingMultimap<K, V> implements ListMultimap<K, V> {
    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
    public abstract ListMultimap<K, V> delegate();

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
    public List<V> get(K k) {
        return delegate().get((Object) k);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public List<V> removeAll(Object obj) {
        return delegate().removeAll(obj);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return delegate().replaceValues((Object) k, (Iterable) iterable);
    }
}
