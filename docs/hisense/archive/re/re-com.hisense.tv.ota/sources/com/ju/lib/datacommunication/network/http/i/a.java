package com.ju.lib.datacommunication.network.http.i;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.C0050j;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Map f141a = new ConcurrentHashMap();

    public static C0050j a(File file, long j) {
        String absolutePath = file.getAbsolutePath();
        b.d("CacheUtils", " initDiskCache filePath: ", absolutePath);
        WeakReference weakReference = (WeakReference) f141a.get(absolutePath);
        C0050j c0050j = weakReference != null ? (C0050j) weakReference.get() : null;
        if (c0050j != null) {
            b.d("CacheUtils", " initDiskCache return cached cache ");
            return c0050j;
        }
        C0050j c0050j2 = new C0050j(file, j);
        f141a.put(absolutePath, new WeakReference(c0050j2));
        return c0050j2;
    }
}
