package coil3.memory;

import io.ktor.http.ContentDisposition;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: RealMemoryCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u0018H\u0096\u0002J\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u001cH\u0096\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010#\u001a\u00020\u001fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000eR\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006$"}, d2 = {"Lcoil3/memory/RealMemoryCache;", "Lcoil3/memory/MemoryCache;", "strongMemoryCache", "Lcoil3/memory/StrongMemoryCache;", "weakMemoryCache", "Lcoil3/memory/WeakMemoryCache;", "<init>", "(Lcoil3/memory/StrongMemoryCache;Lcoil3/memory/WeakMemoryCache;)V", "lock", "", "Lkotlinx/atomicfu/locks/SynchronizedObject;", ContentDisposition.Parameters.Size, "", "getSize", "()J", ES6Iterator.VALUE_PROPERTY, "maxSize", "getMaxSize", "setMaxSize", "(J)V", "initialMaxSize", "getInitialMaxSize", "keys", "", "Lcoil3/memory/MemoryCache$Key;", "getKeys", "()Ljava/util/Set;", "get", "Lcoil3/memory/MemoryCache$Value;", "key", "set", "", "remove", "", "trimToSize", "clear", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RealMemoryCache implements MemoryCache {
    private final Object lock = new Object();
    private final StrongMemoryCache strongMemoryCache;
    private final WeakMemoryCache weakMemoryCache;

    public RealMemoryCache(StrongMemoryCache strongMemoryCache, WeakMemoryCache weakMemoryCache) {
        this.strongMemoryCache = strongMemoryCache;
        this.weakMemoryCache = weakMemoryCache;
    }

    @Override // coil3.memory.MemoryCache
    public long getSize() {
        long size;
        synchronized (this.lock) {
            size = this.strongMemoryCache.getSize();
        }
        return size;
    }

    @Override // coil3.memory.MemoryCache
    public long getMaxSize() {
        long maxSize;
        synchronized (this.lock) {
            maxSize = this.strongMemoryCache.getMaxSize();
        }
        return maxSize;
    }

    @Override // coil3.memory.MemoryCache
    public void setMaxSize(long j) {
        synchronized (this.lock) {
            this.strongMemoryCache.setMaxSize(j);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // coil3.memory.MemoryCache
    public long getInitialMaxSize() {
        long initialMaxSize;
        synchronized (this.lock) {
            initialMaxSize = this.strongMemoryCache.getInitialMaxSize();
        }
        return initialMaxSize;
    }

    @Override // coil3.memory.MemoryCache
    public Set<MemoryCache.Key> getKeys() {
        Set<MemoryCache.Key> setPlus;
        synchronized (this.lock) {
            setPlus = SetsKt.plus((Set) this.strongMemoryCache.getKeys(), (Iterable) this.weakMemoryCache.getKeys());
        }
        return setPlus;
    }

    @Override // coil3.memory.MemoryCache
    public MemoryCache.Value get(MemoryCache.Key key) {
        MemoryCache.Value value;
        synchronized (this.lock) {
            value = this.strongMemoryCache.get(key);
            if (value == null) {
                value = this.weakMemoryCache.get(key);
            }
            if (value != null && !value.getImage().getShareable()) {
                remove(key);
            }
        }
        return value;
    }

    @Override // coil3.memory.MemoryCache
    public void set(MemoryCache.Key key, MemoryCache.Value value) {
        synchronized (this.lock) {
            long size = value.getImage().getSize();
            if (size < 0) {
                throw new IllegalStateException(("Image size must be non-negative: " + size).toString());
            }
            this.strongMemoryCache.set(key, value.getImage(), value.getExtras(), size);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // coil3.memory.MemoryCache
    public boolean remove(MemoryCache.Key key) {
        boolean z;
        synchronized (this.lock) {
            z = this.strongMemoryCache.remove(key) || this.weakMemoryCache.remove(key);
        }
        return z;
    }

    @Override // coil3.memory.MemoryCache
    public void trimToSize(long size) {
        synchronized (this.lock) {
            this.strongMemoryCache.trimToSize(size);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // coil3.memory.MemoryCache
    public void clear() {
        synchronized (this.lock) {
            this.strongMemoryCache.clear();
            this.weakMemoryCache.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
