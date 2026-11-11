package coil3.memory;

import android.content.Context;
import coil3.Image;
import coil3.util.Collections_jvmCommonKt;
import coil3.util.ContextsKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MemoryCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0003\u001d\u001e\u001fJ\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0010H¦\u0002J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0014H¦\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0010H&J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u001c\u001a\u00020\u0017H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\u0005\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u0005R\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006 À\u0006\u0001"}, d2 = {"Lcoil3/memory/MemoryCache;", "", ContentDisposition.Parameters.Size, "", "getSize", "()J", "maxSize", "getMaxSize", "setMaxSize", "(J)V", "initialMaxSize", "getInitialMaxSize$annotations", "()V", "getInitialMaxSize", "keys", "", "Lcoil3/memory/MemoryCache$Key;", "getKeys", "()Ljava/util/Set;", "get", "Lcoil3/memory/MemoryCache$Value;", "key", "set", "", ES6Iterator.VALUE_PROPERTY, "remove", "", "trimToSize", "clear", "Key", "Value", "Builder", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface MemoryCache {

    /* JADX INFO: renamed from: coil3.memory.MemoryCache$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: MemoryCache.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void getInitialMaxSize$annotations() {
        }
    }

    void clear();

    Value get(Key key);

    long getInitialMaxSize();

    Set<Key> getKeys();

    long getMaxSize();

    long getSize();

    boolean remove(Key key);

    void set(Key key, Value value);

    void setMaxSize(long j);

    void trimToSize(long size);

    /* JADX INFO: compiled from: MemoryCache.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcoil3/memory/MemoryCache$Key;", "", "key", "", "extras", "", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getKey", "()Ljava/lang/String;", "getExtras", "()Ljava/util/Map;", "copy", "equals", "", "other", "hashCode", "", "toString", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Key {
        private final Map<String, String> extras;
        private final String key;

        /* JADX WARN: Multi-variable type inference failed */
        public Key(String str) {
            this(str, null, 2, 0 == true ? 1 : 0);
        }

        public Key(String str, Map<String, String> map) {
            this.key = str;
            this.extras = Collections_jvmCommonKt.toImmutableMap(map);
        }

        public final String getKey() {
            return this.key;
        }

        public /* synthetic */ Key(String str, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, String> getExtras() {
            return this.extras;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Key copy$default(Key key, String str, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = key.key;
            }
            if ((i & 2) != 0) {
                map = key.extras;
            }
            return key.copy(str, map);
        }

        public final Key copy(String key, Map<String, String> extras) {
            return new Key(key, extras);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }
            Key key = (Key) other;
            return Intrinsics.areEqual(this.key, key.key) && Intrinsics.areEqual(this.extras, key.extras);
        }

        public int hashCode() {
            return (this.key.hashCode() * 31) + this.extras.hashCode();
        }

        public String toString() {
            return "Key(key=" + this.key + ", extras=" + this.extras + ')';
        }
    }

    /* JADX INFO: compiled from: MemoryCache.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ&\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014"}, d2 = {"Lcoil3/memory/MemoryCache$Value;", "", "image", "Lcoil3/Image;", "extras", "", "", "<init>", "(Lcoil3/Image;Ljava/util/Map;)V", "getImage", "()Lcoil3/Image;", "getExtras", "()Ljava/util/Map;", "copy", "equals", "", "other", "hashCode", "", "toString", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Value {
        private final Map<String, Object> extras;
        private final Image image;

        /* JADX WARN: Multi-variable type inference failed */
        public Value(Image image) {
            this(image, null, 2, 0 == true ? 1 : 0);
        }

        public Value(Image image, Map<String, ? extends Object> map) {
            this.image = image;
            this.extras = Collections_jvmCommonKt.toImmutableMap(map);
        }

        public final Image getImage() {
            return this.image;
        }

        public /* synthetic */ Value(Image image, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(image, (i & 2) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, Object> getExtras() {
            return this.extras;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Value copy$default(Value value, Image image, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                image = value.image;
            }
            if ((i & 2) != 0) {
                map = value.extras;
            }
            return value.copy(image, map);
        }

        public final Value copy(Image image, Map<String, ? extends Object> extras) {
            return new Value(image, extras);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Value)) {
                return false;
            }
            Value value = (Value) other;
            return Intrinsics.areEqual(this.image, value.image) && Intrinsics.areEqual(this.extras, value.extras);
        }

        public int hashCode() {
            return (this.image.hashCode() * 31) + this.extras.hashCode();
        }

        public String toString() {
            return "Value(image=" + this.image + ", extras=" + this.extras + ')';
        }
    }

    /* JADX INFO: compiled from: MemoryCache.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0006J\u0014\u0010\n\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J!\u0010\f\u001a\u00020\u00002\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\u0015R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcoil3/memory/MemoryCache$Builder;", "", "<init>", "()V", "maxSizeBytesFactory", "Lkotlin/Function0;", "", "strongReferencesEnabled", "", "weakReferencesEnabled", "maxSizeBytes", ContentDisposition.Parameters.Size, "maxSizePercent", "context", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "percent", "", "(Landroid/content/Context;D)Lcoil3/memory/MemoryCache$Builder;", "enable", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcoil3/memory/MemoryCache;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Builder {
        private Function0<Long> maxSizeBytesFactory;
        private boolean strongReferencesEnabled = true;
        private boolean weakReferencesEnabled = true;

        static final long maxSizeBytes$lambda$1$lambda$0(long j) {
            return j;
        }

        public final Builder maxSizeBytes(final long size) {
            this.maxSizeBytesFactory = new Function0() { // from class: coil3.memory.MemoryCache$Builder$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Long.valueOf(MemoryCache.Builder.maxSizeBytes$lambda$1$lambda$0(size));
                }
            };
            return this;
        }

        public final Builder maxSizeBytes(Function0<Long> size) {
            this.maxSizeBytesFactory = size;
            return this;
        }

        public static /* synthetic */ Builder maxSizePercent$default(Builder builder, Context context, double d, int i, Object obj) {
            if ((i & 2) != 0) {
                d = ContextsKt.defaultMemoryCacheSizePercent(context);
            }
            return builder.maxSizePercent(context, d);
        }

        public final Builder maxSizePercent(final Context context, final double percent) {
            if (0.0d > percent || percent > 1.0d) {
                throw new IllegalArgumentException("percent must be in the range [0.0, 1.0].".toString());
            }
            this.maxSizeBytesFactory = new Function0() { // from class: coil3.memory.MemoryCache$Builder$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Long.valueOf(MemoryCache.Builder.maxSizePercent$lambda$5$lambda$4(percent, context));
                }
            };
            return this;
        }

        static final long maxSizePercent$lambda$5$lambda$4(double d, Context context) {
            return (long) (d * ContextsKt.totalAvailableMemoryBytes(context));
        }

        public final Builder strongReferencesEnabled(boolean enable) {
            this.strongReferencesEnabled = enable;
            return this;
        }

        public final Builder weakReferencesEnabled(boolean enable) {
            this.weakReferencesEnabled = enable;
            return this;
        }

        public final MemoryCache build() {
            EmptyWeakMemoryCache emptyWeakMemoryCache;
            EmptyStrongMemoryCache emptyStrongMemoryCache;
            if (this.weakReferencesEnabled) {
                emptyWeakMemoryCache = new RealWeakMemoryCache();
            } else {
                emptyWeakMemoryCache = new EmptyWeakMemoryCache();
            }
            if (this.strongReferencesEnabled) {
                Function0<Long> function0 = this.maxSizeBytesFactory;
                if (function0 == null) {
                    throw new IllegalStateException("maxSizeBytesFactory == null".toString());
                }
                emptyStrongMemoryCache = new RealStrongMemoryCache(function0.invoke().longValue(), emptyWeakMemoryCache);
            } else {
                emptyStrongMemoryCache = new EmptyStrongMemoryCache(emptyWeakMemoryCache);
            }
            return new RealMemoryCache(emptyStrongMemoryCache, emptyWeakMemoryCache);
        }
    }
}
