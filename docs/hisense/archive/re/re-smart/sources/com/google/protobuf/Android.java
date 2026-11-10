package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public final class Android {
    public static boolean ASSUME_ANDROID;
    public static final boolean IS_ROBOLECTRIC;
    public static final Class<?> MEMORY_CLASS = getClassForName("libcore.io.Memory");

    static {
        IS_ROBOLECTRIC = (ASSUME_ANDROID || getClassForName("org.robolectric.Robolectric") == null) ? false : true;
    }

    public static <T> Class<T> getClassForName(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Class<?> getMemoryClass() {
        return MEMORY_CLASS;
    }

    public static boolean isOnAndroidDevice() {
        return ASSUME_ANDROID || !(MEMORY_CLASS == null || IS_ROBOLECTRIC);
    }
}
