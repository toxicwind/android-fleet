package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import com.lagradost.cloudstream3.CloudStreamApp;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: DataStore.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\u0010\u001a\u00028\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0086\u0002¢\u0006\u0002\u0010\u0014J.\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\b\u0010\u0017\u001a\u0004\u0018\u00018\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0018R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/PreferenceDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "", "key", "", "default", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/String;", "getDefault", "()Ljava/lang/Object;", "Ljava/lang/Object;", "klass", "Lkotlin/reflect/KClass;", "cache", "getValue", "self", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "t", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PreferenceDelegate<T> {
    private T cache;
    private final T default;
    private final String key;
    private final KClass<? extends T> klass;

    public PreferenceDelegate(String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(t, "default");
        this.key = key;
        this.default = t;
        this.klass = Reflection.getOrCreateKotlinClass(t.getClass());
    }

    public final T getDefault() {
        return this.default;
    }

    public final String getKey() {
        return this.key;
    }

    public final T getValue(Object self, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        T t = this.cache;
        if (t == null) {
            t = (T) CloudStreamApp.INSTANCE.getKeyClass(this.key, JvmClassMappingKt.getJavaClass((KClass) this.klass));
            this.cache = t;
            if (t == null) {
                return this.default;
            }
        }
        return t;
    }

    public final void setValue(Object self, KProperty<?> property, T t) {
        Intrinsics.checkNotNullParameter(property, "property");
        this.cache = t;
        if (t == null) {
            CloudStreamApp.INSTANCE.removeKey(this.key);
        } else {
            CloudStreamApp.INSTANCE.setKeyClass(this.key, t);
        }
    }
}
