package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import com.lagradost.cloudstream3.CloudStreamApp;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: DataStoreHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\u000e\u001a\u00028\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0086\u0002¢\u0006\u0002\u0010\u0012J.\u0010\u0013\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\b\u0010\u0015\u001a\u0004\u0018\u00018\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UserPreferenceDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "", "key", "", "default", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "Ljava/lang/Object;", "klass", "Lkotlin/reflect/KClass;", "realKey", "getRealKey", "()Ljava/lang/String;", "getValue", "self", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "t", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UserPreferenceDelegate<T> {
    private final T default;
    private final String key;
    private final KClass<? extends T> klass;

    public UserPreferenceDelegate(String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(t, "default");
        this.key = key;
        this.default = t;
        this.klass = Reflection.getOrCreateKotlinClass(t.getClass());
    }

    private final String getRealKey() {
        return DataStoreHelper.INSTANCE.m8635getCurrentAccount() + '/' + this.key;
    }

    public final T getValue(Object self, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        T t = (T) CloudStreamApp.INSTANCE.getKeyClass(getRealKey(), JvmClassMappingKt.getJavaClass((KClass) this.klass));
        return t == null ? this.default : t;
    }

    public final void setValue(Object self, KProperty<?> property, T t) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (t == null) {
            CloudStreamApp.INSTANCE.removeKey(getRealKey());
        } else {
            CloudStreamApp.INSTANCE.setKeyClass(getRealKey(), t);
        }
    }
}
