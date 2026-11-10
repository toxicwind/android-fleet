package com.ju.lib.datalayer.database.cache;

import com.ju.lib.datalayer.database.cache.base.AbstractMapCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class HashMapCache<K, V> extends AbstractMapCache<K, V> {
    public HashMap<K, Reference<V>> hashMap = new HashMap<>(10);

    @Override // com.ju.lib.datalayer.database.cache.base.AbstractMapCache
    public void evictAll() {
        this.hashMap.clear();
    }

    @Override // com.ju.lib.datalayer.database.cache.base.AbstractMapCache
    public V getValue(K k) {
        Reference<V> reference = this.hashMap.get(k);
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    @Override // com.ju.lib.datalayer.database.cache.base.AbstractMapCache
    public void putValue(K k, V v) {
        this.hashMap.put(k, new WeakReference(v));
    }

    @Override // com.ju.lib.datalayer.database.cache.base.IDatabaseCache
    public void removeKey(K k) {
        this.hashMap.remove(k);
    }
}
