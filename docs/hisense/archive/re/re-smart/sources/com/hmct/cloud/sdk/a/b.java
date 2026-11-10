package com.hmct.cloud.sdk.a;

/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return Integer.MIN_VALUE;
        }
    }

    public static long b(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return Long.MIN_VALUE;
        }
    }
}
