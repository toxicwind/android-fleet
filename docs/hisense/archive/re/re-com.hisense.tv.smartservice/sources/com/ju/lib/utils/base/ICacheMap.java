package com.ju.lib.utils.base;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface ICacheMap<K, V> {
    void clearCache();

    V getData(K k);

    void putData(K k, V v);

    V removeData(K k);

    int sizeOfCache();

    Map<K, V> snap();
}
