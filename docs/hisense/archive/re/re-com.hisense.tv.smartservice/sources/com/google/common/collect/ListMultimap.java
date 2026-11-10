package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public interface ListMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    boolean equals(Object obj);

    @Override // com.google.common.collect.Multimap
    List<V> get(K k);

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    List<V> removeAll(Object obj);

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    List<V> replaceValues(K k, Iterable<? extends V> iterable);
}
