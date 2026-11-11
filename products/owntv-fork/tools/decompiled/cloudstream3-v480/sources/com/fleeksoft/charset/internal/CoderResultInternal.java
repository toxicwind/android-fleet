package com.fleeksoft.charset.internal;

import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoderResultInternal.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000b\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\f\u001a\u00020\rR\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\t\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/charset/internal/CoderResultInternal;", "", "<init>", "()V", "UNDERFLOW", "Ljava/nio/charset/CoderResult;", "Lcom/fleeksoft/charset/CoderResult;", "getUNDERFLOW", "()Ljava/nio/charset/CoderResult;", "OVERFLOW", "getOVERFLOW", "malformedForLength", "length", "", "unmappableForLength", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CoderResultInternal {
    public static final CoderResultInternal INSTANCE = new CoderResultInternal();
    private static final CoderResult OVERFLOW;
    private static final CoderResult UNDERFLOW;

    private CoderResultInternal() {
    }

    static {
        CoderResult UNDERFLOW2 = CoderResult.UNDERFLOW;
        Intrinsics.checkNotNullExpressionValue(UNDERFLOW2, "UNDERFLOW");
        UNDERFLOW = UNDERFLOW2;
        CoderResult OVERFLOW2 = CoderResult.OVERFLOW;
        Intrinsics.checkNotNullExpressionValue(OVERFLOW2, "OVERFLOW");
        OVERFLOW = OVERFLOW2;
    }

    public final CoderResult getUNDERFLOW() {
        return UNDERFLOW;
    }

    public final CoderResult getOVERFLOW() {
        return OVERFLOW;
    }

    public final CoderResult malformedForLength(int length) {
        CoderResult coderResultMalformedForLength = CoderResult.malformedForLength(length);
        Intrinsics.checkNotNullExpressionValue(coderResultMalformedForLength, "malformedForLength(...)");
        return coderResultMalformedForLength;
    }

    public final CoderResult unmappableForLength(int length) {
        CoderResult coderResultUnmappableForLength = CoderResult.unmappableForLength(length);
        Intrinsics.checkNotNullExpressionValue(coderResultUnmappableForLength, "unmappableForLength(...)");
        return coderResultUnmappableForLength;
    }
}
