package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public interface BiMap<K, V> extends Map<K, V> {
    @CanIgnoreReturnValue
    V forcePut(K k, V v);

    BiMap<V, K> inverse();

    @CanIgnoreReturnValue
    V put(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);

    @Override // java.util.Map
    Set<V> values();
}
