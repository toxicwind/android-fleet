package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public abstract class HashMultimapGwtSerializationDependencies<K, V> extends AbstractSetMultimap<K, V> {
    public HashMultimapGwtSerializationDependencies(Map<K, Collection<V>> map) {
        super(map);
    }
}
