package org.mozilla.javascript;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public class ClassCache implements Serializable {
    private static final Object AKEY = "ClassCache";
    private static final long serialVersionUID = -8866246036237312215L;
    private Scriptable associatedScope;
    private volatile boolean cachingIsEnabled = true;
    private transient Map<JavaAdapter.JavaAdapterSignature, Class<?>> classAdapterCache;
    private transient Map<CacheKey, JavaMembers> classTable;
    private int generatedClassSerial;
    private transient Map<Class<?>, Object> interfaceAdapterCache;

    @Deprecated
    public boolean isInvokerOptimizationEnabled() {
        return false;
    }

    static class CacheKey {
        final Class<?> cls;
        final Object sec;

        public CacheKey(Class<?> cls, Object obj) {
            this.cls = cls;
            this.sec = obj;
        }

        public int hashCode() {
            int iHashCode = this.cls.hashCode();
            Object obj = this.sec;
            return obj != null ? obj.hashCode() * 31 : iHashCode;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CacheKey)) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) obj;
            return Objects.equals(this.cls, cacheKey.cls) && Objects.equals(this.sec, cacheKey.sec);
        }
    }

    public static ClassCache get(Scriptable scriptable) {
        ClassCache classCache = (ClassCache) ScriptableObject.getTopScopeValue(scriptable, AKEY);
        if (classCache != null) {
            return classCache;
        }
        throw new RuntimeException("Can't find top level scope for ClassCache.get");
    }

    public boolean associate(ScriptableObject scriptableObject) {
        if (scriptableObject.getParentScope() != null) {
            throw new IllegalArgumentException();
        }
        if (this != scriptableObject.associateValue(AKEY, this)) {
            return false;
        }
        this.associatedScope = scriptableObject;
        return true;
    }

    public synchronized void clearCaches() {
        this.classTable = null;
        this.classAdapterCache = null;
        this.interfaceAdapterCache = null;
    }

    public final boolean isCachingEnabled() {
        return this.cachingIsEnabled;
    }

    public synchronized void setCachingEnabled(boolean z) {
        if (z == this.cachingIsEnabled) {
            return;
        }
        if (!z) {
            clearCaches();
        }
        this.cachingIsEnabled = z;
    }

    Map<CacheKey, JavaMembers> getClassCacheMap() {
        if (this.classTable == null) {
            this.classTable = new ConcurrentHashMap(16, 0.75f, 1);
        }
        return this.classTable;
    }

    Map<JavaAdapter.JavaAdapterSignature, Class<?>> getInterfaceAdapterCacheMap() {
        if (this.classAdapterCache == null) {
            this.classAdapterCache = new ConcurrentHashMap(16, 0.75f, 1);
        }
        return this.classAdapterCache;
    }

    @Deprecated
    public synchronized void setInvokerOptimizationEnabled(boolean z) {
    }

    public final synchronized int newClassSerialNumber() {
        int i;
        i = this.generatedClassSerial + 1;
        this.generatedClassSerial = i;
        return i;
    }

    Object getInterfaceAdapter(Class<?> cls) {
        Map<Class<?>, Object> map = this.interfaceAdapterCache;
        if (map == null) {
            return null;
        }
        return map.get(cls);
    }

    synchronized void cacheInterfaceAdapter(Class<?> cls, Object obj) {
        if (this.cachingIsEnabled) {
            if (this.interfaceAdapterCache == null) {
                this.interfaceAdapterCache = new ConcurrentHashMap(16, 0.75f, 1);
            }
            this.interfaceAdapterCache.put(cls, obj);
        }
    }

    Scriptable getAssociatedScope() {
        return this.associatedScope;
    }
}
