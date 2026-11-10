package com.google.android.gms.internal.common;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzah {
    public static Object[] zza(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (objArr[i2] == null) {
                throw new NullPointerException(a.a(20, "at index ", i2));
            }
        }
        return objArr;
    }
}
