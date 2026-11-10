package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Verify {
    public static void verify(boolean z) {
        if (!z) {
            throw new VerifyException();
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(T t) {
        verifyNotNull(t, "expected a non-null reference", new Object[0]);
        return t;
    }

    public static void verify(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, objArr));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(T t, String str, Object... objArr) {
        verify(t != null, str, objArr);
        return t;
    }

    public static void verify(boolean z, String str, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, int i) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i)));
        }
    }

    public static void verify(boolean z, String str, long j) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j)));
        }
    }

    public static void verify(boolean z, String str, Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj));
        }
    }

    public static void verify(boolean z, String str, char c2, char c3) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    public static void verify(boolean z, String str, int i, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, long j, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, Object obj, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, char c2, int i) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i)));
        }
    }

    public static void verify(boolean z, String str, int i, int i2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, String str, long j, int i) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
        }
    }

    public static void verify(boolean z, String str, Object obj, int i) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, Integer.valueOf(i)));
        }
    }

    public static void verify(boolean z, String str, char c2, long j) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j)));
        }
    }

    public static void verify(boolean z, String str, int i, long j) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
        }
    }

    public static void verify(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, String str, Object obj, long j) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
        }
    }

    public static void verify(boolean z, String str, char c2, Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
        }
    }

    public static void verify(boolean z, String str, int i, Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i), obj));
        }
    }

    public static void verify(boolean z, String str, long j, Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j), obj));
        }
    }

    public static void verify(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void verify(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    public static void verify(boolean z, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }
}
