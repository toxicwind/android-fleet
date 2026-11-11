package okio.internal;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.common.base.Ascii;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: -Utf8.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u001a\n\u0010\u0006\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0007"}, d2 = {"commonToUtf8String", "", "", "beginIndex", "", "endIndex", "commonAsUtf8ToByteArray", "okio"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class _Utf8Kt {
    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return commonToUtf8String(bArr, i, i2);
    }

    public static final String commonToUtf8String(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = i;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i8 < 0 || i2 > bArr.length || i8 > i2) {
            throw new ArrayIndexOutOfBoundsException("size=" + bArr.length + " beginIndex=" + i8 + " endIndex=" + i2);
        }
        char[] cArr = new char[i2 - i8];
        int i9 = 0;
        while (i8 < i2) {
            byte b = bArr[i8];
            if (b >= 0) {
                int i10 = i9 + 1;
                cArr[i9] = (char) b;
                i8++;
                while (i8 < i2) {
                    byte b2 = bArr[i8];
                    if (b2 < 0) {
                        break;
                    }
                    i8++;
                    cArr[i10] = (char) b2;
                    i10++;
                }
                Unit unit = Unit.INSTANCE;
                i9 = i10;
            } else {
                if ((b >> 5) == -2) {
                    int i11 = i8 + 1;
                    if (i2 <= i11) {
                        i3 = i9 + 1;
                        cArr[i9] = 65533;
                    } else {
                        byte b3 = bArr[i11];
                        if ((b3 & 192) == 128) {
                            int i12 = (b << 6) ^ (b3 ^ 3968);
                            if (i12 < 128) {
                                i3 = i9 + 1;
                                cArr[i9] = 65533;
                                Unit unit2 = Unit.INSTANCE;
                            } else {
                                int i13 = i9 + 1;
                                cArr[i9] = (char) i12;
                                Unit unit3 = Unit.INSTANCE;
                                i3 = i13;
                            }
                            i7 = 2;
                        } else {
                            i3 = i9 + 1;
                            cArr[i9] = 65533;
                        }
                        i8 += i7;
                        Unit unit4 = Unit.INSTANCE;
                    }
                    Unit unit5 = Unit.INSTANCE;
                    i7 = 1;
                    i8 += i7;
                    Unit unit6 = Unit.INSTANCE;
                } else if ((b >> 4) == -2) {
                    int i14 = i8 + 2;
                    if (i2 <= i14) {
                        i3 = i9 + 1;
                        cArr[i9] = 65533;
                        Unit unit7 = Unit.INSTANCE;
                        int i15 = i8 + 1;
                        i6 = (i2 <= i15 || (bArr[i15] & 192) != 128) ? 1 : 2;
                    } else {
                        byte b4 = bArr[i8 + 1];
                        if ((b4 & 192) == 128) {
                            byte b5 = bArr[i14];
                            if ((b5 & 192) == 128) {
                                int i16 = (b << Ascii.FF) ^ ((b5 ^ (-123008)) ^ (b4 << 6));
                                if (i16 < 2048) {
                                    i3 = i9 + 1;
                                    cArr[i9] = 65533;
                                } else {
                                    if (55296 > i16 || i16 >= 57344) {
                                        int i17 = i9 + 1;
                                        cArr[i9] = (char) i16;
                                        Unit unit8 = Unit.INSTANCE;
                                        i3 = i17;
                                    } else {
                                        i3 = i9 + 1;
                                        cArr[i9] = 65533;
                                    }
                                    i6 = 3;
                                }
                                Unit unit9 = Unit.INSTANCE;
                                i6 = 3;
                            } else {
                                i3 = i9 + 1;
                                cArr[i9] = 65533;
                                Unit unit10 = Unit.INSTANCE;
                            }
                        } else {
                            i3 = i9 + 1;
                            cArr[i9] = 65533;
                            Unit unit11 = Unit.INSTANCE;
                        }
                    }
                    i8 += i6;
                    Unit unit12 = Unit.INSTANCE;
                } else if ((b >> 3) == -2) {
                    int i18 = i8 + 3;
                    if (i2 <= i18) {
                        i3 = i9 + 1;
                        cArr[i9] = 65533;
                        Unit unit13 = Unit.INSTANCE;
                        int i19 = i8 + 1;
                        if (i2 <= i19 || (bArr[i19] & 192) != 128) {
                            i5 = 1;
                        } else {
                            int i20 = i8 + 2;
                            i5 = (i2 <= i20 || (bArr[i20] & 192) != 128) ? 2 : 3;
                        }
                    } else {
                        byte b6 = bArr[i8 + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i8 + 2];
                            if ((b7 & 192) == 128) {
                                byte b8 = bArr[i18];
                                if ((b8 & 192) == 128) {
                                    int i21 = (b << Ascii.DC2) ^ (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << Ascii.FF));
                                    if (i21 > 1114111) {
                                        i3 = i9 + 1;
                                        cArr[i9] = 65533;
                                    } else {
                                        if ((55296 > i21 || i21 >= 57344) && i21 >= 65536) {
                                            if (i21 != 65533) {
                                                cArr[i9] = (char) ((i21 >>> 10) + 55232);
                                                i4 = i9 + 2;
                                                cArr[i9 + 1] = (char) ((i21 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
                                            } else {
                                                cArr[i9] = 65533;
                                                i4 = i9 + 1;
                                            }
                                            Unit unit14 = Unit.INSTANCE;
                                            i3 = i4;
                                        } else {
                                            i3 = i9 + 1;
                                            cArr[i9] = 65533;
                                        }
                                        i5 = 4;
                                    }
                                    Unit unit15 = Unit.INSTANCE;
                                    i5 = 4;
                                } else {
                                    i3 = i9 + 1;
                                    cArr[i9] = 65533;
                                    Unit unit16 = Unit.INSTANCE;
                                }
                            } else {
                                i3 = i9 + 1;
                                cArr[i9] = 65533;
                                Unit unit17 = Unit.INSTANCE;
                            }
                        } else {
                            i3 = i9 + 1;
                            cArr[i9] = 65533;
                            Unit unit18 = Unit.INSTANCE;
                            i5 = 1;
                        }
                    }
                    i8 += i5;
                    Unit unit19 = Unit.INSTANCE;
                } else {
                    i3 = i9 + 1;
                    cArr[i9] = 65533;
                    Integer.valueOf(i8);
                    i8++;
                }
                i9 = i3;
            }
        }
        return StringsKt.concatToString(cArr, 0, i9);
    }

    public static final byte[] commonAsUtf8ToByteArray(String str) {
        int i;
        char cCharAt;
        Intrinsics.checkNotNullParameter(str, "<this>");
        byte[] bArr = new byte[str.length() * 4];
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char cCharAt2 = str.charAt(i2);
            if (Intrinsics.compare((int) cCharAt2, 128) >= 0) {
                int length2 = str.length();
                int i3 = i2;
                while (i2 < length2) {
                    char cCharAt3 = str.charAt(i2);
                    if (Intrinsics.compare((int) cCharAt3, 128) < 0) {
                        int i4 = i3 + 1;
                        bArr[i3] = (byte) cCharAt3;
                        i2++;
                        while (true) {
                            i3 = i4;
                            if (i2 >= length2 || Intrinsics.compare((int) str.charAt(i2), 128) >= 0) {
                                break;
                            }
                            i4 = i3 + 1;
                            bArr[i3] = (byte) str.charAt(i2);
                            i2++;
                        }
                    } else {
                        if (Intrinsics.compare((int) cCharAt3, 2048) < 0) {
                            bArr[i3] = (byte) ((cCharAt3 >> 6) | 192);
                            i3 += 2;
                            bArr[i3 + 1] = (byte) ((cCharAt3 & '?') | 128);
                        } else if (55296 > cCharAt3 || cCharAt3 >= 57344) {
                            bArr[i3] = (byte) ((cCharAt3 >> '\f') | 224);
                            bArr[i3 + 1] = (byte) (((cCharAt3 >> 6) & 63) | 128);
                            i3 += 3;
                            bArr[i3 + 2] = (byte) ((cCharAt3 & '?') | 128);
                        } else if (Intrinsics.compare((int) cCharAt3, GeneratorBase.SURR1_LAST) > 0 || length2 <= (i = i2 + 1) || 56320 > (cCharAt = str.charAt(i)) || cCharAt >= 57344) {
                            bArr[i3] = 63;
                            i2++;
                            i3++;
                        } else {
                            int iCharAt = ((cCharAt3 << '\n') + str.charAt(i)) - 56613888;
                            bArr[i3] = (byte) ((iCharAt >> 18) | 240);
                            bArr[i3 + 1] = (byte) (((iCharAt >> 12) & 63) | 128);
                            bArr[i3 + 2] = (byte) (((iCharAt >> 6) & 63) | 128);
                            i3 += 4;
                            bArr[i3 + 3] = (byte) ((iCharAt & 63) | 128);
                            i2 += 2;
                        }
                        i2++;
                    }
                }
                byte[] bArrCopyOf = Arrays.copyOf(bArr, i3);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                return bArrCopyOf;
            }
            bArr[i2] = (byte) cCharAt2;
            i2++;
        }
        byte[] bArrCopyOf2 = Arrays.copyOf(bArr, str.length());
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf2, "copyOf(...)");
        return bArrCopyOf2;
    }
}
