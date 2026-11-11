package coil3.memory;

import coil3.Image;
import coil3.util.LruCache;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: StrongMemoryCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000U\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\n\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u000b\b\u0000\u0018\u00002\u00020\u0001:\u0001(B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J4\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!2\u0006\u0010\r\u001a\u00020\u0003H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\b\u0010&\u001a\u00020\u001dH\u0016J\u0010\u0010'\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR$\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\t\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lcoil3/memory/RealStrongMemoryCache;", "Lcoil3/memory/StrongMemoryCache;", "initialMaxSize", "", "weakMemoryCache", "Lcoil3/memory/WeakMemoryCache;", "<init>", "(JLcoil3/memory/WeakMemoryCache;)V", "getInitialMaxSize", "()J", "cache", "coil3/memory/RealStrongMemoryCache$cache$1", "Lcoil3/memory/RealStrongMemoryCache$cache$1;", ContentDisposition.Parameters.Size, "getSize", ES6Iterator.VALUE_PROPERTY, "maxSize", "getMaxSize", "setMaxSize", "(J)V", "keys", "", "Lcoil3/memory/MemoryCache$Key;", "getKeys", "()Ljava/util/Set;", "get", "Lcoil3/memory/MemoryCache$Value;", "key", "set", "", "image", "Lcoil3/Image;", "extras", "", "", "", "remove", "", "clear", "trimToSize", "InternalValue", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RealStrongMemoryCache implements StrongMemoryCache {
    private final RealStrongMemoryCache$cache$1 cache;
    private final long initialMaxSize;
    private final WeakMemoryCache weakMemoryCache;

    /* JADX WARN: Type inference failed for: r3v1, types: [coil3.memory.RealStrongMemoryCache$cache$1] */
    public RealStrongMemoryCache(long j, WeakMemoryCache weakMemoryCache) {
        this.initialMaxSize = j;
        this.weakMemoryCache = weakMemoryCache;
        final long initialMaxSize = getInitialMaxSize();
        this.cache = new LruCache<MemoryCache.Key, InternalValue>(initialMaxSize) { // from class: coil3.memory.RealStrongMemoryCache$cache$1
            @Override // coil3.util.LruCache
            public long sizeOf(MemoryCache.Key key, RealStrongMemoryCache.InternalValue value) {
                return value.getSize();
            }

            @Override // coil3.util.LruCache
            public void entryRemoved(MemoryCache.Key key, RealStrongMemoryCache.InternalValue oldValue, RealStrongMemoryCache.InternalValue newValue) {
                this.this$0.weakMemoryCache.set(key, oldValue.getImage(), oldValue.getExtras(), oldValue.getSize());
            }
        };
    }

    @Override // coil3.memory.StrongMemoryCache
    public long getInitialMaxSize() {
        return this.initialMaxSize;
    }

    @Override // coil3.memory.StrongMemoryCache
    public long getSize() {
        return getSize();
    }

    @Override // coil3.memory.StrongMemoryCache
    public long getMaxSize() {
        return getMaxSize();
    }

    @Override // coil3.memory.StrongMemoryCache
    public void setMaxSize(long j) {
        setMaxSize(j);
    }

    @Override // coil3.memory.StrongMemoryCache
    public Set<MemoryCache.Key> getKeys() {
        return getKeys();
    }

    @Override // coil3.memory.StrongMemoryCache
    public MemoryCache.Value get(MemoryCache.Key key) {
        InternalValue internalValue = get(key);
        if (internalValue != null) {
            return new MemoryCache.Value(internalValue.getImage(), internalValue.getExtras());
        }
        return null;
    }

    @Override // coil3.memory.StrongMemoryCache
    public void set(MemoryCache.Key key, Image image, Map<String, ? extends Object> extras, long size) {
        long maxSize = getMaxSize();
        RealStrongMemoryCache$cache$1 realStrongMemoryCache$cache$1 = this.cache;
        if (size <= maxSize) {
            realStrongMemoryCache$cache$1.put(key, new InternalValue(image, extras, size));
        } else {
            realStrongMemoryCache$cache$1.remove(key);
            this.weakMemoryCache.set(key, image, extras, size);
        }
    }

    @Override // coil3.memory.StrongMemoryCache
    public boolean remove(MemoryCache.Key key) {
        return remove(key) != null;
    }

    @Override // coil3.memory.StrongMemoryCache
    public void clear() {
        clear();
    }

    @Override // coil3.memory.StrongMemoryCache
    public void trimToSize(long size) {
        trimToSize(size);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: StrongMemoryCache.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcoil3/memory/RealStrongMemoryCache$InternalValue;", "", "image", "Lcoil3/Image;", "extras", "", "", ContentDisposition.Parameters.Size, "", "<init>", "(Lcoil3/Image;Ljava/util/Map;J)V", "getImage", "()Lcoil3/Image;", "getExtras", "()Ljava/util/Map;", "getSize", "()J", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class InternalValue {
        private final Map<String, Object> extras;
        private final Image image;
        private final long size;

        public InternalValue(Image image, Map<String, ? extends Object> map, long j) {
            this.image = image;
            this.extras = map;
            this.size = j;
        }

        public final Image getImage() {
            return this.image;
        }

        public final Map<String, Object> getExtras() {
            return this.extras;
        }

        public final long getSize() {
            return this.size;
        }
    }
}
