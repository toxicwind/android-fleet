package com.google.common.base;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Preconditions {
    public static String badElementIndex(int i, int i2, String str) {
        if (i < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return Strings.lenientFormat("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IllegalArgumentException(a.a(26, "negative size: ", i2));
    }

    public static String badPositionIndex(int i, int i2, String str) {
        if (i < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IllegalArgumentException(a.a(26, "negative size: ", i2));
    }

    public static String badPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            return badPositionIndex(i, i3, "start index");
        }
        return (i2 < 0 || i2 > i3) ? badPositionIndex(i2, i3, "end index") : Strings.lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i, int i2) {
        checkElementIndex(i, i2, FirebaseAnalytics.Param.INDEX);
        return i;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i, int i2) {
        checkPositionIndex(i, i2, FirebaseAnalytics.Param.INDEX);
        return i;
    }

    public static void checkPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException(badPositionIndexes(i, i2, i3));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i, int i2, String str) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(badElementIndex(i, i2, str));
        }
        return i;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i, int i2, String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(badPositionIndex(i, i2, str));
        }
        return i;
    }

    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, objArr));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, objArr));
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, objArr));
        }
    }

    public static void checkArgument(boolean z, String str, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2)));
    }

    public static void checkState(boolean z, String str, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i)));
    }

    public static void checkState(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j)));
    }

    public static void checkState(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj));
    }

    public static void checkState(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, char c3) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, char c2, char c3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
    }

    public static void checkState(boolean z, String str, char c2, char c3) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, int i) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, char c2, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i)));
    }

    public static void checkState(boolean z, String str, char c2, int i) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, char c2, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j)));
    }

    public static void checkState(boolean z, String str, char c2, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, char c2, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
    }

    public static void checkState(boolean z, String str, char c2, Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
        }
    }

    public static void checkArgument(boolean z, String str, int i, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, int i, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c2)));
    }

    public static void checkState(boolean z, String str, int i, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, int i, int i2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, int i, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static void checkState(boolean z, String str, int i, int i2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, String str, int i, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, int i, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    public static void checkState(boolean z, String str, int i, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, int i, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, int i, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), obj));
    }

    public static void checkState(boolean z, String str, int i, Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), obj));
        }
    }

    public static void checkArgument(boolean z, String str, long j, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, long j, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c2)));
    }

    public static void checkState(boolean z, String str, long j, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, long j, int i) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, long j, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    public static void checkState(boolean z, String str, long j, int i) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, long j, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    public static void checkState(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, String str, long j, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, long j, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), obj));
    }

    public static void checkState(boolean z, String str, long j, Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), obj));
        }
    }

    public static void checkArgument(boolean z, String str, Object obj, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object obj, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
    }

    public static void checkState(boolean z, String str, Object obj, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, Object obj, int i) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Integer.valueOf(i)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object obj, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Integer.valueOf(i)));
    }

    public static void checkState(boolean z, String str, Object obj, int i) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Integer.valueOf(i)));
        }
    }

    public static void checkArgument(boolean z, String str, Object obj, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object obj, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
    }

    public static void checkState(boolean z, String str, Object obj, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object obj, Object obj2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2));
    }

    public static void checkState(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void checkArgument(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object obj, Object obj2, Object obj3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3));
    }

    public static void checkState(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    public static void checkArgument(boolean z, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
    }

    public static void checkState(boolean z, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }
}
