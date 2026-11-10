package com.ju.lib.utils.base;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class JuNormalCache<K, V> implements ICacheMap<K, V> {
    public final LinkedHashMap<K, V> map = new LinkedHashMap<>();

    @Override // com.ju.lib.utils.base.ICacheMap
    public void clearCache() {
        this.map.clear();
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public V getData(K k) {
        return this.map.get(k);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public void putData(K k, V v) {
        this.map.put(k, v);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public V removeData(K k) {
        return this.map.remove(k);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public int sizeOfCache() {
        return this.map.size();
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public Map<K, V> snap() {
        return this.map;
    }
}
