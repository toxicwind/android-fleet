package io.ktor.util;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Attributes.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J(\u0010\u0005\u001a\u00028\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u0007\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H&¢\u0006\u0004\b\u0007\u0010\u0006J\u001c\u0010\t\u001a\u00020\b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H¦\u0002¢\u0006\u0004\b\t\u0010\nJ/\u0010\r\u001a\u00020\f\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u000b\u001a\u00028\u0000H&¢\u0006\u0004\b\r\u0010\u000eJ0\u0010\u000f\u001a\u00020\f\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ'\u0010\u0010\u001a\u00020\f\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H&¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0004\b\u0012\u0010\u0006J)\u0010\u0013\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0004\b\u0013\u0010\u0006J5\u0010\u0016\u001a\u00028\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H&¢\u0006\u0004\b\u0016\u0010\u0017R\u001e\u0010\u001b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001cÀ\u0006\u0003"}, d2 = {"Lio/ktor/util/Attributes;", "", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/util/AttributeKey;", "key", "get", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "getOrNull", "", "contains", "(Lio/ktor/util/AttributeKey;)Z", ES6Iterator.VALUE_PROPERTY, "", "put", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "set", "remove", "(Lio/ktor/util/AttributeKey;)V", "take", "takeOrNull", "Lkotlin/Function0;", "block", "computeIfAbsent", "(Lio/ktor/util/AttributeKey;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "", "getAllKeys", "()Ljava/util/List;", "allKeys", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Attributes {
    <T> T computeIfAbsent(AttributeKey<T> key, Function0<? extends T> block);

    boolean contains(AttributeKey<?> key);

    <T> T get(AttributeKey<T> key);

    List<AttributeKey<?>> getAllKeys();

    <T> T getOrNull(AttributeKey<T> key);

    <T> void put(AttributeKey<T> key, T value);

    <T> void remove(AttributeKey<T> key);

    <T> void set(AttributeKey<T> key, T value);

    <T> T take(AttributeKey<T> key);

    <T> T takeOrNull(AttributeKey<T> key);

    /* JADX INFO: renamed from: io.ktor.util.Attributes$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Attributes.kt */
    public final /* synthetic */ class CC {
        public static Object $default$get(Attributes _this, AttributeKey key) {
            Intrinsics.checkNotNullParameter(key, "key");
            Object orNull = _this.getOrNull(key);
            if (orNull != null) {
                return orNull;
            }
            throw new IllegalStateException("No instance for key " + key);
        }

        public static void $default$set(Attributes _this, AttributeKey key, Object value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            _this.put(key, value);
        }

        public static Object $default$take(Attributes _this, AttributeKey key) {
            Intrinsics.checkNotNullParameter(key, "key");
            Object obj = _this.get(key);
            _this.remove(key);
            return obj;
        }

        public static Object $default$takeOrNull(Attributes _this, AttributeKey key) {
            Intrinsics.checkNotNullParameter(key, "key");
            Object orNull = _this.getOrNull(key);
            _this.remove(key);
            return orNull;
        }
    }

    /* JADX INFO: compiled from: Attributes.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> T get(Attributes attributes, AttributeKey<T> key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return (T) CC.$default$get(attributes, key);
        }

        @Deprecated
        public static <T> void set(Attributes attributes, AttributeKey<T> key, T value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            CC.$default$set(attributes, key, value);
        }

        @Deprecated
        public static <T> T take(Attributes attributes, AttributeKey<T> key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return (T) CC.$default$take(attributes, key);
        }

        @Deprecated
        public static <T> T takeOrNull(Attributes attributes, AttributeKey<T> key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return (T) CC.$default$takeOrNull(attributes, key);
        }
    }
}
