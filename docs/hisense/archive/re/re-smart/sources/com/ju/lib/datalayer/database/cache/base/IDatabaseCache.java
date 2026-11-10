package com.ju.lib.datalayer.database.cache.base;

/* JADX INFO: loaded from: classes2.dex */
public interface IDatabaseCache<K, V> {
    void dumpCache();

    V get(K k);

    void put(K k, V v);

    void removeKey(K k);
}
