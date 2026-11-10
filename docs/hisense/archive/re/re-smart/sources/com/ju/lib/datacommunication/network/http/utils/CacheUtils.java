package com.ju.lib.datacommunication.network.http.utils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Cache;
import okhttp3.internal.io.FileSystem;

/* JADX INFO: loaded from: classes2.dex */
public class CacheUtils {
    public static Map<String, WeakReference<Cache>> cacheHashMap = new ConcurrentHashMap();

    public static Cache initDiskCache(File file, long j) {
        String absolutePath = file.getAbsolutePath();
        HttpLog.i("CacheUtils", " initDiskCache filePath: ", absolutePath);
        WeakReference<Cache> weakReference = cacheHashMap.get(absolutePath);
        Cache cache = weakReference != null ? weakReference.get() : null;
        if (cache != null) {
            HttpLog.i("CacheUtils", " initDiskCache return cached cache ");
            return cache;
        }
        Cache cache2 = new Cache(file, j, FileSystem.SYSTEM);
        cacheHashMap.put(absolutePath, new WeakReference<>(cache2));
        return cache2;
    }
}
