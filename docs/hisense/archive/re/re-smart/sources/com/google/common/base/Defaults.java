package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class Defaults {
    public static final Double DOUBLE_DEFAULT = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    public static final Float FLOAT_DEFAULT = Float.valueOf(0.0f);

    public static <T> T defaultValue(Class<T> cls) {
        if (cls == null) {
            throw new NullPointerException();
        }
        if (cls == Boolean.TYPE) {
            return (T) Boolean.FALSE;
        }
        if (cls == Character.TYPE) {
            return (T) (char) 0;
        }
        if (cls == Byte.TYPE) {
            return (T) (byte) 0;
        }
        if (cls == Short.TYPE) {
            return (T) (short) 0;
        }
        if (cls == Integer.TYPE) {
            return (T) 0;
        }
        if (cls == Long.TYPE) {
            return (T) 0L;
        }
        if (cls == Float.TYPE) {
            return (T) FLOAT_DEFAULT;
        }
        if (cls == Double.TYPE) {
            return (T) DOUBLE_DEFAULT;
        }
        return null;
    }
}
