package io.ktor.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bytes.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "readShort", "([BI)S", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BytesKt {
    public static final short readShort(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }
}
