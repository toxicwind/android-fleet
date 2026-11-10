package com.google.common.graph;

import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MapIteratorCache<K, V> {
    public final Map<K, V> backingMap;
    public volatile transient Map.Entry<K, V> cacheEntry;

    public MapIteratorCache(Map<K, V> map) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.backingMap = map;
    }

    public final void clear() {
        clearCache();
        this.backingMap.clear();
    }

    public void clearCache() {
        this.cacheEntry = null;
    }

    public final boolean containsKey(Object obj) {
        return getIfCached(obj) != null || this.backingMap.containsKey(obj);
    }

    public V get(Object obj) {
        V ifCached = getIfCached(obj);
        return ifCached != null ? ifCached : getWithoutCaching(obj);
    }

    public V getIfCached(Object obj) {
        Map.Entry<K, V> entry = this.cacheEntry;
        if (entry == null || entry.getKey() != obj) {
            return null;
        }
        return entry.getValue();
    }

    public final V getWithoutCaching(Object obj) {
        return this.backingMap.get(obj);
    }

    @CanIgnoreReturnValue
    public final V put(K k, V v) {
        clearCache();
        return this.backingMap.put(k, v);
    }

    @CanIgnoreReturnValue
    public final V remove(Object obj) {
        clearCache();
        return this.backingMap.remove(obj);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AbstractSet<K>() { // from class: com.google.common.graph.MapIteratorCache.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return MapIteratorCache.this.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return MapIteratorCache.this.backingMap.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<K> iterator() {
                final Iterator<Map.Entry<K, V>> it = MapIteratorCache.this.backingMap.entrySet().iterator();
                return new UnmodifiableIterator<K>() { // from class: com.google.common.graph.MapIteratorCache.1.1
                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override // java.util.Iterator
                    public K next() {
                        Map.Entry<K, V> entry = (Map.Entry) it.next();
                        MapIteratorCache.this.cacheEntry = entry;
                        return entry.getKey();
                    }
                };
            }
        };
    }
}
