package coil3.memory;

import coil3.Image;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: compiled from: StrongMemoryCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000eH&J4\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u00192\u0006\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u000eH&J\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u001e\u001a\u00020\u0015H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\u0005\"\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001fÀ\u0006\u0001"}, d2 = {"Lcoil3/memory/StrongMemoryCache;", "", ContentDisposition.Parameters.Size, "", "getSize", "()J", "maxSize", "getMaxSize", "setMaxSize", "(J)V", "initialMaxSize", "getInitialMaxSize", "keys", "", "Lcoil3/memory/MemoryCache$Key;", "getKeys", "()Ljava/util/Set;", "get", "Lcoil3/memory/MemoryCache$Value;", "key", "set", "", "image", "Lcoil3/Image;", "extras", "", "", "remove", "", "trimToSize", "clear", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface StrongMemoryCache {
    void clear();

    MemoryCache.Value get(MemoryCache.Key key);

    long getInitialMaxSize();

    Set<MemoryCache.Key> getKeys();

    long getMaxSize();

    long getSize();

    boolean remove(MemoryCache.Key key);

    void set(MemoryCache.Key key, Image image, Map<String, ? extends Object> extras, long size);

    void setMaxSize(long j);

    void trimToSize(long size);
}
