package kotlin.jvm.internal;

import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ArrayIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ArrayIteratorsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayByteIterator(array);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull char[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayCharIterator(array);
    }

    @NotNull
    public static final ShortIterator iterator(@NotNull short[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayShortIterator(array);
    }

    @NotNull
    public static final IntIterator iterator(@NotNull int[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayIntIterator(array);
    }

    @NotNull
    public static final LongIterator iterator(@NotNull long[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayLongIterator(array);
    }

    @NotNull
    public static final FloatIterator iterator(@NotNull float[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayFloatIterator(array);
    }

    @NotNull
    public static final DoubleIterator iterator(@NotNull double[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayDoubleIterator(array);
    }

    @NotNull
    public static final BooleanIterator iterator(@NotNull boolean[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayBooleanIterator(array);
    }
}
