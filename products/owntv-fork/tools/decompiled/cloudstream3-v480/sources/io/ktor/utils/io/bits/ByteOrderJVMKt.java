package io.ktor.utils.io.bits;

import kotlin.Metadata;

/* JADX INFO: compiled from: ByteOrderJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a\u0014\u0010\u0001\u001a\u00020\u0000*\u00020\u0000H\u0086\b¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0014\u0010\u0001\u001a\u00020\u0003*\u00020\u0003H\u0086\b¢\u0006\u0004\b\u0001\u0010\u0004\u001a\u0014\u0010\u0001\u001a\u00020\u0005*\u00020\u0005H\u0086\b¢\u0006\u0004\b\u0001\u0010\u0006\u001a\u0014\u0010\u0001\u001a\u00020\u0007*\u00020\u0007H\u0086\b¢\u0006\u0004\b\u0001\u0010\b\u001a\u0014\u0010\u0001\u001a\u00020\t*\u00020\tH\u0086\b¢\u0006\u0004\b\u0001\u0010\n¨\u0006\u000b"}, d2 = {"", "reverseByteOrder", "(S)S", "", "(I)I", "", "(J)J", "", "(F)F", "", "(D)D", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteOrderJVMKt {
    public static final short reverseByteOrder(short s) {
        return Short.reverseBytes(s);
    }

    public static final int reverseByteOrder(int i) {
        return Integer.reverseBytes(i);
    }

    public static final long reverseByteOrder(long j) {
        return Long.reverseBytes(j);
    }

    public static final float reverseByteOrder(float f) {
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f)));
    }

    public static final double reverseByteOrder(double d) {
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(d)));
    }
}
