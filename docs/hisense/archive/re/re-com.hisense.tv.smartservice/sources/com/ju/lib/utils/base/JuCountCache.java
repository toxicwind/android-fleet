package com.ju.lib.utils.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class JuCountCache<K, V> implements ICacheMap<K, V> {
    public int mMaxCount;
    public Map<K, V> mMap = new HashMap();
    public List<K> mList = new ArrayList();

    public JuCountCache(int i) {
        this.mMaxCount = 0;
        if (i < 0) {
            return;
        }
        this.mMaxCount = i;
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public void clearCache() {
        for (Map.Entry<K, V> entry : this.mMap.entrySet()) {
            entryRemoved(true, entry.getKey(), entry.getValue(), null);
        }
        this.mMap.clear();
        this.mList.clear();
    }

    public void entryRemoved(boolean z, K k, V v, V v2) {
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public V getData(K k) {
        return this.mMap.get(k);
    }

    public int getMaxCount() {
        return this.mMaxCount;
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public void putData(K k, V v) {
        if (this.mMap.size() > 0 && this.mList.size() > 0 && this.mMap.size() >= this.mMaxCount) {
            entryRemoved(true, k, this.mMap.remove(this.mList.remove(0)), v);
        }
        this.mList.add(k);
        this.mMap.put(k, v);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public V removeData(K k) {
        this.mList.remove(k);
        return this.mMap.remove(k);
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public int sizeOfCache() {
        return this.mMap.size();
    }

    @Override // com.ju.lib.utils.base.ICacheMap
    public Map<K, V> snap() {
        return this.mMap;
    }
}
