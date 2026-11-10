package com.ju.lib.datalayer.database.cache.manager;

import com.ju.lib.datalayer.database.cache.HashMapCache;
import com.ju.lib.datalayer.database.cache.base.IDatabaseCache;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class CacheManager {
    public static boolean mNoCache = true;
    public static HashMap<Class, IDatabaseCache> sIDatabaseCacheMap = new HashMap<>();
    public static Class<? extends IDatabaseCache> sDatabaseCache = HashMapCache.class;

    public static void clearCache(Class<?> cls) {
        IDatabaseCache cache;
        if (mNoCache || (cache = getCache(cls)) == null) {
            return;
        }
        cache.dumpCache();
    }

    public static void evictAll() {
        if (mNoCache) {
            return;
        }
        Iterator<Class> it = sIDatabaseCacheMap.keySet().iterator();
        while (it.hasNext()) {
            sIDatabaseCacheMap.remove(it.next());
        }
    }

    public static IDatabaseCache getCache(Class cls) {
        return sIDatabaseCacheMap.get(cls);
    }

    public static Object getFromCache(Object obj, Class<?> cls) {
        IDatabaseCache cache;
        if (mNoCache || (cache = getCache(cls)) == null) {
            return null;
        }
        return cache.get(obj);
    }

    public static IDatabaseCache getNewCache() {
        if (mNoCache) {
            return null;
        }
        try {
            return sDatabaseCache.newInstance();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void putCache(Class cls, IDatabaseCache iDatabaseCache) {
        sIDatabaseCacheMap.put(cls, iDatabaseCache);
    }

    public static void removeFromCache(Object obj, Class<?> cls) {
        IDatabaseCache cache;
        if (mNoCache || (cache = getCache(cls)) == null) {
            return;
        }
        cache.removeKey(obj);
    }

    public static void saveToCache(Object obj, Object obj2) {
        if (mNoCache || obj2 == null) {
            return;
        }
        IDatabaseCache cache = getCache(obj2.getClass());
        if (cache == null) {
            cache = getNewCache();
            sIDatabaseCacheMap.put(obj2.getClass(), cache);
        }
        if (cache != null) {
            cache.put(obj, obj2);
        }
    }

    public static void setCacheType(Class<? extends IDatabaseCache> cls) {
        sDatabaseCache = cls;
    }
}
