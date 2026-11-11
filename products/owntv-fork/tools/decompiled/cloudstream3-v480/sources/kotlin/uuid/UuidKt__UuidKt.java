package kotlin.uuid;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.HexExtensionsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Uuid.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\n\u001a\n\u0010\u0003\u001a\u00020\u0004H\u0081\u0080\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0081\u0080\u0004\u001a\u0016\u0010\b\u001a\u00020\t*\u00020\u00072\u0006\u0010\n\u001a\u00020\u0001H\u0080\u0080\u0004\u001a.\u0010\u000b\u001a\u00020\f*\u00020\t2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0081\u0080\u0004\u001a\u001e\u0010\u0011\u001a\u00020\f*\u00020\u00072\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\tH\u0080\u0080\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001af\u0010\u0017\u001a\u00020\f*\u00020\u00152\u0006\u0010\n\u001a\u00020\u00012K\u0010\u0018\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0019H\u0080\u0088\u0004ø\u0001\u0000\u001ab\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152K\u0010\u0018\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u0019H\u0081\u0088\u0004ø\u0001\u0000\u001a\u0012\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020\u0015H\u0081\u0080\u0004\u001ab\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00152K\u0010\u0018\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u001f0\u0019H\u0081\u0088\u0004ø\u0001\u0000\u001a\u001b\u0010$\u001a\u00020\u0015*\u00020\u00152\u0006\u0010%\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b&\u001a\u001b\u0010$\u001a\u00020\u0015*\u00020\u00072\u0006\u0010'\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b&\u001a\"\u0010(\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0001H\u0080\u0080\u0004\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0002\u001a\u00020\u0001X\u0082Ô\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006)"}, d2 = {"UUID_HEX_LENGTH", "", "UUID_HEX_DASH_LENGTH", "secureRandomUuid", "Lkotlin/uuid/Uuid;", "uuidFromRandomBytes", "randomBytes", "", "getLongAtCommonImpl", "", "index", "formatBytesIntoCommonImpl", "", "dst", "dstOffset", "startIndex", "endIndex", "setLongAtCommonImpl", ES6Iterator.VALUE_PROPERTY, "uuidParseHexDashCommonImpl", "hexDashString", "", "uuidParseHexDashOrNullCommonImpl", "uuidCheckHyphenAt", "onError", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "inputString", "errorDescription", "errorPosition", "", "uuidParseHexCommonImpl", "hexString", "uuidParseHexOrNullCommonImpl", "errorIndex", "truncateForErrorMessage", "maxLength", "truncateForErrorMessage$UuidKt__UuidKt", "maxSize", "uuidThrowUnexpectedCharacterException", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/uuid/UuidKt")
class UuidKt__UuidKt extends UuidKt__UuidJVMKt {
    private static final int UUID_HEX_DASH_LENGTH = 36;
    private static final int UUID_HEX_LENGTH = 32;

    public static final Uuid secureRandomUuid() {
        byte[] bArr = new byte[16];
        UuidKt.secureRandomBytes(bArr);
        return UuidKt.uuidFromRandomBytes(bArr);
    }

    public static final Uuid uuidFromRandomBytes(byte[] randomBytes) {
        Intrinsics.checkNotNullParameter(randomBytes, "randomBytes");
        byte b = (byte) (randomBytes[6] & Ascii.SI);
        randomBytes[6] = b;
        randomBytes[6] = (byte) (b | SignedBytes.MAX_POWER_OF_TWO);
        byte b2 = (byte) (randomBytes[8] & 63);
        randomBytes[8] = b2;
        randomBytes[8] = (byte) (b2 | 128);
        return Uuid.Companion.fromByteArray(randomBytes);
    }

    public static final long getLongAtCommonImpl(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return (((long) bArr[i + 7]) & 255) | ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i + 1]) & 255) << 48) | ((((long) bArr[i + 2]) & 255) << 40) | ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 5]) & 255) << 16) | ((((long) bArr[i + 6]) & 255) << 8);
    }

    public static final void formatBytesIntoCommonImpl(long j, byte[] dst, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(dst, "dst");
        int i4 = 7 - i2;
        int i5 = 8 - i3;
        if (i5 > i4) {
            return;
        }
        while (true) {
            int i6 = HexExtensionsKt.getBYTE_TO_LOWER_CASE_HEX_DIGITS()[(int) ((j >> (i4 << 3)) & 255)];
            int i7 = i + 1;
            dst[i] = (byte) (i6 >> 8);
            i += 2;
            dst[i7] = (byte) i6;
            if (i4 == i5) {
                return;
            } else {
                i4--;
            }
        }
    }

    public static final void setLongAtCommonImpl(byte[] bArr, int i, long j) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i2 = 7;
        while (-1 < i2) {
            bArr[i] = (byte) (j >> (i2 << 3));
            i2--;
            i++;
        }
    }

    public static final void uuidCheckHyphenAt(String str, int i, Function3<? super String, ? super String, ? super Integer, Unit> onError) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (str.charAt(i) != '-') {
            onError.invoke(str, "'-' (hyphen)", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String truncateForErrorMessage$UuidKt__UuidKt(String str, int i) {
        if (str.length() <= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(0, i);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        sb.append(strSubstring);
        sb.append("...");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String truncateForErrorMessage$UuidKt__UuidKt(byte[] bArr, int i) {
        return ArraysKt.joinToString$default(bArr, (CharSequence) null, (CharSequence) "[", (CharSequence) "]", i, (CharSequence) null, (Function1) null, 49, (Object) null);
    }

    public static final Void uuidThrowUnexpectedCharacterException(String inputString, String errorDescription, int i) {
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        Intrinsics.checkNotNullParameter(errorDescription, "errorDescription");
        throw new IllegalArgumentException("Expected " + errorDescription + " at index " + i + ", but was '" + inputString.charAt(i) + '\'');
    }

    public static final Uuid uuidParseHexDashCommonImpl(String hexDashString) {
        Intrinsics.checkNotNullParameter(hexDashString, "hexDashString");
        long j = 0;
        for (int i = 0; i < 8; i++) {
            long j2 = j << 4;
            char cCharAt = hexDashString.charAt(i);
            if ((cCharAt >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt] < 0) {
                UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "a hexadecimal digit", i);
                throw new KotlinNothingValueException();
            }
            j = j2 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
        }
        if (hexDashString.charAt(8) != '-') {
            UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "'-' (hyphen)", 8);
            throw new KotlinNothingValueException();
        }
        long j3 = 0;
        for (int i2 = 9; i2 < 13; i2++) {
            long j4 = j3 << 4;
            char cCharAt2 = hexDashString.charAt(i2);
            if ((cCharAt2 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2] < 0) {
                UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "a hexadecimal digit", i2);
                throw new KotlinNothingValueException();
            }
            j3 = j4 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2];
        }
        if (hexDashString.charAt(13) != '-') {
            UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "'-' (hyphen)", 13);
            throw new KotlinNothingValueException();
        }
        long j5 = 0;
        for (int i3 = 14; i3 < 18; i3++) {
            long j6 = j5 << 4;
            char cCharAt3 = hexDashString.charAt(i3);
            if ((cCharAt3 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt3] < 0) {
                UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "a hexadecimal digit", i3);
                throw new KotlinNothingValueException();
            }
            j5 = j6 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt3];
        }
        if (hexDashString.charAt(18) != '-') {
            UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "'-' (hyphen)", 18);
            throw new KotlinNothingValueException();
        }
        long j7 = 0;
        for (int i4 = 19; i4 < 23; i4++) {
            long j8 = j7 << 4;
            char cCharAt4 = hexDashString.charAt(i4);
            if ((cCharAt4 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt4] < 0) {
                UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "a hexadecimal digit", i4);
                throw new KotlinNothingValueException();
            }
            j7 = j8 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt4];
        }
        if (hexDashString.charAt(23) != '-') {
            UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "'-' (hyphen)", 23);
            throw new KotlinNothingValueException();
        }
        long j9 = 0;
        for (int i5 = 24; i5 < 36; i5++) {
            long j10 = j9 << 4;
            char cCharAt5 = hexDashString.charAt(i5);
            if ((cCharAt5 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt5] < 0) {
                UuidKt.uuidThrowUnexpectedCharacterException(hexDashString, "a hexadecimal digit", i5);
                throw new KotlinNothingValueException();
            }
            j9 = j10 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt5];
        }
        return Uuid.Companion.fromLongs((j << 32) | (j3 << 16) | j5, (j7 << 48) | j9);
    }

    public static final Uuid uuidParseHexDashOrNullCommonImpl(String hexDashString) {
        Intrinsics.checkNotNullParameter(hexDashString, "hexDashString");
        long j = 0;
        for (int i = 0; i < 8; i++) {
            long j2 = j << 4;
            char cCharAt = hexDashString.charAt(i);
            if ((cCharAt >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt] < 0) {
                return null;
            }
            j = j2 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
        }
        if (hexDashString.charAt(8) != '-') {
            return null;
        }
        long j3 = 0;
        for (int i2 = 9; i2 < 13; i2++) {
            long j4 = j3 << 4;
            char cCharAt2 = hexDashString.charAt(i2);
            if ((cCharAt2 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2] < 0) {
                return null;
            }
            j3 = j4 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2];
        }
        if (hexDashString.charAt(13) != '-') {
            return null;
        }
        long j5 = 0;
        for (int i3 = 14; i3 < 18; i3++) {
            long j6 = j5 << 4;
            char cCharAt3 = hexDashString.charAt(i3);
            if ((cCharAt3 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt3] < 0) {
                return null;
            }
            j5 = j6 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt3];
        }
        if (hexDashString.charAt(18) != '-') {
            return null;
        }
        long j7 = 0;
        for (int i4 = 19; i4 < 23; i4++) {
            long j8 = j7 << 4;
            char cCharAt4 = hexDashString.charAt(i4);
            if ((cCharAt4 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt4] < 0) {
                return null;
            }
            j7 = j8 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt4];
        }
        if (hexDashString.charAt(23) != '-') {
            return null;
        }
        long j9 = 0;
        for (int i5 = 24; i5 < 36; i5++) {
            long j10 = j9 << 4;
            char cCharAt5 = hexDashString.charAt(i5);
            if ((cCharAt5 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt5] < 0) {
                return null;
            }
            j9 = j10 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt5];
        }
        return Uuid.Companion.fromLongs((j << 32) | (j3 << 16) | j5, (j7 << 48) | j9);
    }

    public static final Uuid uuidParseHexDashCommonImpl(String hexDashString, Function3 onError) {
        Intrinsics.checkNotNullParameter(hexDashString, "hexDashString");
        Intrinsics.checkNotNullParameter(onError, "onError");
        long j = 0;
        for (int i = 0; i < 8; i++) {
            long j2 = j << 4;
            char cCharAt = hexDashString.charAt(i);
            if ((cCharAt >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt] >= 0) {
                j = j2 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
            } else {
                onError.invoke(hexDashString, "a hexadecimal digit", Integer.valueOf(i));
                throw new KotlinNothingValueException();
            }
        }
        if (hexDashString.charAt(8) != '-') {
            onError.invoke(hexDashString, "'-' (hyphen)", 8);
        }
        long j3 = 0;
        for (int i2 = 9; i2 < 13; i2++) {
            long j4 = j3 << 4;
            char cCharAt2 = hexDashString.charAt(i2);
            if ((cCharAt2 >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2] >= 0) {
                j3 = j4 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2];
            } else {
                onError.invoke(hexDashString, "a hexadecimal digit", Integer.valueOf(i2));
                throw new KotlinNothingValueException();
            }
        }
        if (hexDashString.charAt(13) != '-') {
            onError.invoke(hexDashString, "'-' (hyphen)", 13);
        }
        long j5 = 0;
        for (int i3 = 14; i3 < 18; i3++) {
            long j6 = j5 << 4;
            char cCharAt3 = hexDashString.charAt(i3);
            if ((cCharAt3 >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt3] >= 0) {
                j5 = j6 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt3];
            } else {
                onError.invoke(hexDashString, "a hexadecimal digit", Integer.valueOf(i3));
                throw new KotlinNothingValueException();
            }
        }
        if (hexDashString.charAt(18) != '-') {
            onError.invoke(hexDashString, "'-' (hyphen)", 18);
        }
        long j7 = 0;
        for (int i4 = 19; i4 < 23; i4++) {
            long j8 = j7 << 4;
            char cCharAt4 = hexDashString.charAt(i4);
            if ((cCharAt4 >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt4] >= 0) {
                j7 = j8 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt4];
            } else {
                onError.invoke(hexDashString, "a hexadecimal digit", Integer.valueOf(i4));
                throw new KotlinNothingValueException();
            }
        }
        if (hexDashString.charAt(23) != '-') {
            onError.invoke(hexDashString, "'-' (hyphen)", 23);
        }
        long j9 = 0;
        for (int i5 = 24; i5 < 36; i5++) {
            long j10 = j9 << 4;
            char cCharAt5 = hexDashString.charAt(i5);
            if ((cCharAt5 >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt5] >= 0) {
                j9 = j10 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt5];
            } else {
                onError.invoke(hexDashString, "a hexadecimal digit", Integer.valueOf(i5));
                throw new KotlinNothingValueException();
            }
        }
        return Uuid.Companion.fromLongs((j << 32) | (j3 << 16) | j5, (j7 << 48) | j9);
    }

    public static final Uuid uuidParseHexCommonImpl(String hexString) {
        Intrinsics.checkNotNullParameter(hexString, "hexString");
        int i = 0;
        long j = 0;
        while (true) {
            if (i < 16) {
                long j2 = j << 4;
                char cCharAt = hexString.charAt(i);
                if ((cCharAt >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt] >= 0) {
                    j = j2 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
                    i++;
                } else {
                    UuidKt.uuidThrowUnexpectedCharacterException(hexString, "a hexadecimal digit", i);
                    throw new KotlinNothingValueException();
                }
            } else {
                long j3 = 0;
                for (int i2 = 16; i2 < 32; i2++) {
                    long j4 = j3 << 4;
                    char cCharAt2 = hexString.charAt(i2);
                    if ((cCharAt2 >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2] >= 0) {
                        j3 = j4 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2];
                    } else {
                        UuidKt.uuidThrowUnexpectedCharacterException(hexString, "a hexadecimal digit", i2);
                        throw new KotlinNothingValueException();
                    }
                }
                return Uuid.Companion.fromLongs(j, j3);
            }
        }
    }

    public static final Uuid uuidParseHexOrNullCommonImpl(String hexString) {
        Intrinsics.checkNotNullParameter(hexString, "hexString");
        int i = 0;
        long j = 0;
        while (true) {
            if (i < 16) {
                long j2 = j << 4;
                char cCharAt = hexString.charAt(i);
                if ((cCharAt >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt] < 0) {
                    return null;
                }
                j = j2 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
                i++;
            } else {
                long j3 = 0;
                for (int i2 = 16; i2 < 32; i2++) {
                    long j4 = j3 << 4;
                    char cCharAt2 = hexString.charAt(i2);
                    if ((cCharAt2 >>> '\b') != 0 || HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2] < 0) {
                        return null;
                    }
                    j3 = j4 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2];
                }
                return Uuid.Companion.fromLongs(j, j3);
            }
        }
    }

    public static final Uuid uuidParseHexCommonImpl(String hexString, Function3 onError) {
        Intrinsics.checkNotNullParameter(hexString, "hexString");
        Intrinsics.checkNotNullParameter(onError, "onError");
        int i = 0;
        long j = 0;
        while (true) {
            if (i >= 16) {
                long j2 = 0;
                for (int i2 = 16; i2 < 32; i2++) {
                    long j3 = j2 << 4;
                    char cCharAt = hexString.charAt(i2);
                    if ((cCharAt >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt] >= 0) {
                        j2 = j3 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt];
                    } else {
                        onError.invoke(hexString, "a hexadecimal digit", Integer.valueOf(i2));
                        throw new KotlinNothingValueException();
                    }
                }
                return Uuid.Companion.fromLongs(j, j2);
            }
            long j4 = j << 4;
            char cCharAt2 = hexString.charAt(i);
            if ((cCharAt2 >>> '\b') == 0 && HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2] >= 0) {
                j = j4 | HexExtensionsKt.HEX_DIGITS_TO_LONG_DECIMAL[cCharAt2];
                i++;
            } else {
                onError.invoke(hexString, "a hexadecimal digit", Integer.valueOf(i));
                throw new KotlinNothingValueException();
            }
        }
    }
}
