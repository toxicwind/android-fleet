package kotlinx.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.base.Ascii;
import io.ktor.http.ContentDisposition;
import io.ktor.util.date.GMTDateParser;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: -Util.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0080\u0080\u0004\u001a\"\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0080\u0088\u0004\u001a\"\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0080\u0080\u0004\u001a\u0012\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0007H\u0080\u0088\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u0010*\u00020\u0010H\u0080\u0088\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u000b*\u00020\u000bH\u0080\u0088\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u0007*\u00020\u0007H\u0080\u0088\u0004\u001a\u0016\u0010\u0011\u001a\u00020\u000b*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0080\u008c\u0004\u001a\u0016\u0010\u0014\u001a\u00020\u000b*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0080\u008c\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u000b*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0080\u008c\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0007*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0080\u008c\u0004\u001a\u0016\u0010\u0016\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0080\u008c\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0007*\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0007H\u0080\u008c\u0004\u001a\u001a\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u000bH\u0080\u0088\u0004\u001a\u001a\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0007H\u0080\u0088\u0004\u001a\u000e\u0010\u001a\u001a\u00020\u001b*\u00020\u0012H\u0080\u0080\u0004\u001a\u000e\u0010\u001a\u001a\u00020\u001b*\u00020\u000bH\u0080\u0080\u0004\u001a\u000e\u0010\u001a\u001a\u00020\u001b*\u00020\u0007H\u0080\u0080\u0004\u001a\u0012\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\u0088\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u001e"}, d2 = {"HEX_DIGIT_CHARS", "", "getHEX_DIGIT_CHARS", "()[C", "checkOffsetAndCount", "", ContentDisposition.Parameters.Size, "", TypedValues.CycleType.S_WAVE_OFFSET, "byteCount", "checkBounds", "", "startIndex", "endIndex", "checkByteCount", "reverseBytesCommon", "", "shr", "", "other", "shl", "and", "xor", "minOf", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "toHexString", "", "hexNumberLength", "v", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class _UtilKt {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f'};

    public static final int and(byte b, int i) {
        return b & i;
    }

    public static final long and(byte b, long j) {
        return j & ((long) b);
    }

    public static final long and(int i, long j) {
        return j & ((long) i);
    }

    public static final int reverseBytesCommon(int i) {
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    public static final long reverseBytesCommon(long j) {
        return ((j & 255) << 56) | (((-72057594037927936L) & j) >>> 56) | ((71776119061217280L & j) >>> 40) | ((280375465082880L & j) >>> 24) | ((1095216660480L & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((65280 & j) << 40);
    }

    public static final short reverseBytesCommon(short s) {
        return (short) (((s & 255) << 8) | ((65280 & s) >>> 8));
    }

    public static final int shl(byte b, int i) {
        return b << i;
    }

    public static final int shr(byte b, int i) {
        return b >> i;
    }

    public static final byte xor(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if (j2 < 0 || j2 > j || j - j2 < j3 || j3 < 0) {
            throw new IllegalArgumentException("offset (" + j2 + ") and byteCount (" + j3 + ") are not within the range [0..size(" + j + "))");
        }
    }

    public static final void checkBounds(int i, int i2, int i3) {
        checkBounds(i, i2, i3);
    }

    public static final void checkBounds(long j, long j2, long j3) {
        if (j2 < 0 || j3 > j) {
            throw new IndexOutOfBoundsException("startIndex (" + j2 + ") and endIndex (" + j3 + ") are not within the range [0..size(" + j + "))");
        }
        if (j2 <= j3) {
            return;
        }
        throw new IllegalArgumentException("startIndex (" + j2 + ") > endIndex (" + j3 + ')');
    }

    public static final void checkByteCount(long j) {
        if (j >= 0) {
            return;
        }
        throw new IllegalArgumentException(("byteCount (" + j + ") < 0").toString());
    }

    public static final long minOf(long j, int i) {
        return Math.min(j, i);
    }

    public static final long minOf(int i, long j) {
        return Math.min(i, j);
    }

    public static final String toHexString(byte b) {
        char[] cArr = HEX_DIGIT_CHARS;
        return StringsKt.concatToString(new char[]{cArr[(b >> 4) & 15], cArr[b & Ascii.SI]});
    }

    public static final String toHexString(int i) {
        if (i == 0) {
            return "0";
        }
        char[] cArr = HEX_DIGIT_CHARS;
        int i2 = 0;
        char[] cArr2 = {cArr[(i >> 28) & 15], cArr[(i >> 24) & 15], cArr[(i >> 20) & 15], cArr[(i >> 16) & 15], cArr[(i >> 12) & 15], cArr[(i >> 8) & 15], cArr[(i >> 4) & 15], cArr[i & 15]};
        while (i2 < 8 && cArr2[i2] == '0') {
            i2++;
        }
        return StringsKt.concatToString(cArr2, i2, 8);
    }

    public static final String toHexString(long j) {
        if (j == 0) {
            return "0";
        }
        char[] cArr = HEX_DIGIT_CHARS;
        int i = 0;
        char[] cArr2 = {cArr[(int) ((j >> 60) & 15)], cArr[(int) ((j >> 56) & 15)], cArr[(int) ((j >> 52) & 15)], cArr[(int) ((j >> 48) & 15)], cArr[(int) ((j >> 44) & 15)], cArr[(int) ((j >> 40) & 15)], cArr[(int) ((j >> 36) & 15)], cArr[(int) ((j >> 32) & 15)], cArr[(int) ((j >> 28) & 15)], cArr[(int) ((j >> 24) & 15)], cArr[(int) ((j >> 20) & 15)], cArr[(int) ((j >> 16) & 15)], cArr[(int) ((j >> 12) & 15)], cArr[(int) ((j >> 8) & 15)], cArr[(int) ((j >> 4) & 15)], cArr[(int) (j & 15)]};
        while (i < 16 && cArr2[i] == '0') {
            i++;
        }
        return StringsKt.concatToString(cArr2, i, 16);
    }

    public static final int hexNumberLength(long j) {
        if (j == 0) {
            return 1;
        }
        return (67 - Long.numberOfLeadingZeros(j)) / 4;
    }
}
