package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
    public static final long serialVersionUID = 0;
    public final K key;
    public final V value;

    public ImmutableEntry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    public final K getKey() {
        return this.key;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }
}
