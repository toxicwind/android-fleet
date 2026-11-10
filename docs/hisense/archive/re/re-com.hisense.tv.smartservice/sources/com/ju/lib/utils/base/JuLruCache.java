package com.ju.lib.utils.base;

import android.util.LruCache;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class JuLruCache<K, V> extends LruCache<K, V> implements ICacheMap<K, V> {
    public JuLruCache(int i) {
        super(i);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public void clearCache() {
        evictAll();
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public V getData(K k) {
        return get(k);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public void putData(K k, V v) {
        put(k, v);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public V removeData(K k) {
        return remove(k);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public int sizeOfCache() {
        return size();
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public Map<K, V> snap() {
        return snapshot();
    }
}
