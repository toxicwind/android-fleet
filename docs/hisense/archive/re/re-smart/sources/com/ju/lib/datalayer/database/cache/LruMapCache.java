package com.ju.lib.datalayer.database.cache;

import android.util.LruCache;
import com.ju.lib.datalayer.database.cache.base.AbstractMapCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class LruMapCache<K, V> extends AbstractMapCache<K, V> {
    public LruCache<K, Reference<V>> lruCache = new LruCache<>(10);

    @Override // com.ju.lib.datalayer.database.cache.base.AbstractMapCache
    public void evictAll() {
        this.lruCache.evictAll();
    }

    @Override // com.ju.lib.datalayer.database.cache.base.AbstractMapCache
    public V getValue(K k) {
        Reference<V> reference = this.lruCache.get(k);
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    @Override // com.ju.lib.datalayer.database.cache.base.AbstractMapCache
    public void putValue(K k, V v) {
        this.lruCache.put(k, new WeakReference(v));
    }

    @Override // com.ju.lib.datalayer.database.cache.base.IDatabaseCache
    public void removeKey(K k) {
        this.lruCache.remove(k);
    }
}
