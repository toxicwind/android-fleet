package io.ktor.util.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MapDelegates.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\tR \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lio/ktor/util/collections/StringMapDelegate;", "Lio/ktor/util/collections/StringMap;", "", "key", ES6Iterator.VALUE_PROPERTY, "", "set", "(Ljava/lang/String;Ljava/lang/String;)V", "get", "(Ljava/lang/String;)Ljava/lang/String;", "remove", "", "getMap", "()Ljava/util/Map;", "map", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface StringMapDelegate extends StringMap {
    @Override // io.ktor.util.collections.StringMap
    String get(String key);

    Map<String, String> getMap();

    @Override // io.ktor.util.collections.StringMap
    String remove(String key);

    @Override // io.ktor.util.collections.StringMap
    void set(String key, String value);

    /* JADX INFO: renamed from: io.ktor.util.collections.StringMapDelegate$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: MapDelegates.kt */
    public final /* synthetic */ class CC {
        public static void $default$set(StringMapDelegate _this, String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            _this.getMap().put(key, value);
        }

        public static String $default$get(StringMapDelegate _this, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return _this.getMap().get(key);
        }

        public static String $default$remove(StringMapDelegate _this, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return _this.getMap().remove(key);
        }
    }

    /* JADX INFO: compiled from: MapDelegates.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void set(StringMapDelegate stringMapDelegate, String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            CC.$default$set(stringMapDelegate, key, value);
        }

        @Deprecated
        public static String get(StringMapDelegate stringMapDelegate, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return CC.$default$get(stringMapDelegate, key);
        }

        @Deprecated
        public static String remove(StringMapDelegate stringMapDelegate, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return CC.$default$remove(stringMapDelegate, key);
        }
    }
}
