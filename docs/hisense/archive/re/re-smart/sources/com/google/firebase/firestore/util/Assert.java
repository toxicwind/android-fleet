package com.google.firebase.firestore.util;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class Assert {
    public static AssertionError fail(String str, Object... objArr) {
        throw new AssertionError(format(str, objArr));
    }

    public static String format(String str, Object... objArr) {
        StringBuilder sbA = a.a("INTERNAL ASSERTION FAILED: ");
        sbA.append(String.format(str, objArr));
        return sbA.toString();
    }

    public static void hardAssert(boolean z, String str, Object... objArr) {
        if (z) {
            return;
        }
        fail(str, objArr);
        throw null;
    }

    public static AssertionError fail(Throwable th, String str, Object... objArr) {
        throw ApiUtil.newAssertionError(format(str, objArr), th);
    }
}
