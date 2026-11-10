package com.google.android.gms.dynamic;

import android.os.IBinder;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.RetainForClient;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
@RetainForClient
@KeepForSdk
public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
    public final T zza;

    public ObjectWrapper(T t) {
        this.zza = t;
    }

    @NonNull
    @KeepForSdk
    public static <T> T unwrap(@NonNull IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper instanceof ObjectWrapper) {
            return ((ObjectWrapper) iObjectWrapper).zza;
        }
        IBinder iBinderAsBinder = iObjectWrapper.asBinder();
        Field[] declaredFields = iBinderAsBinder.getClass().getDeclaredFields();
        Field field = null;
        int i = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i++;
                field = field2;
            }
        }
        if (i != 1) {
            throw new IllegalArgumentException(a.a(64, "Unexpected number of IObjectWrapper declared fields: ", declaredFields.length));
        }
        Preconditions.checkNotNull(field);
        if (field.isAccessible()) {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        field.setAccessible(true);
        try {
            return (T) field.get(iBinderAsBinder);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", e2);
        } catch (NullPointerException e3) {
            throw new IllegalArgumentException("Binder object is null.", e3);
        }
    }

    @NonNull
    @KeepForSdk
    public static <T> IObjectWrapper wrap(@NonNull T t) {
        return new ObjectWrapper(t);
    }
}
