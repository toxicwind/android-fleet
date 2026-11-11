package kotlinx.io.internal;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: -Utf8.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0080\u0080\u0004\u001a\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0080\u0088\u0004\u001a\u0012\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\u0080\u0088\u0004\u001a5\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u0013H\u0080\u0088\u0004ø\u0001\u0000\u001a5\u0010\u0016\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00110\u0013H\u0080\u0088\u0004ø\u0001\u0000\u001a5\u0010\u0018\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u0013H\u0080\u0088\u0004ø\u0001\u0000\u001a5\u0010\u001a\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u0013H\u0080\u0088\u0004ø\u0001\u0000\u001a5\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u0013H\u0080\u0088\u0004ø\u0001\u0000\"\u000f\u0010\u0006\u001a\u00020\u0007X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\b\u001a\u00020\tX\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\n\u001a\u00020\u0004X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0014\u001a\u00020\u0004X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0015\u001a\u00020\u0004X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0017\u001a\u00020\u0004X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0019\u001a\u00020\u0004X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u001b\u001a\u00020\u0004X\u0080Ô\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"commonToUtf8String", "", "", "beginIndex", "", "endIndex", "REPLACEMENT_BYTE", "", "REPLACEMENT_CHARACTER", "", "REPLACEMENT_CODE_POINT", "isIsoControl", "", "codePoint", "isUtf8Continuation", "byte", "processUtf8CodePoints", "", "yield", "Lkotlin/Function1;", "HIGH_SURROGATE_HEADER", "LOG_SURROGATE_HEADER", "processUtf16Chars", "MASK_2BYTES", "process2Utf8Bytes", "MASK_3BYTES", "process3Utf8Bytes", "MASK_4BYTES", "process4Utf8Bytes", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class _Utf8Kt {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = 65533;
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int i) {
        if (i < 0 || i >= 32) {
            return 127 <= i && i < 160;
        }
        return true;
    }

    public static final boolean isUtf8Continuation(byte b) {
        return (b & 192) == 128;
    }

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
            throw new IndexOutOfBoundsException("size=" + bArr.length + " beginIndex=" + i8 + " endIndex=" + i2);
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

    /* JADX WARN: Code duplicated, block: B:16:0x0042  */
    public static final void processUtf8CodePoints(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        while (i < i2) {
            byte b = bArr[i];
            if (b >= 0) {
                yield.invoke(Integer.valueOf(b));
                i++;
                while (i < i2) {
                    byte b2 = bArr[i];
                    if (b2 < 0) {
                        break;
                    }
                    i++;
                    yield.invoke(Integer.valueOf(b2));
                }
            } else {
                int i3 = 2;
                if ((b >> 5) == -2) {
                    int i4 = i + 1;
                    if (i2 > i4) {
                        byte b3 = bArr[i4];
                        if ((b3 & 192) == 128) {
                            int i5 = (b << 6) ^ (b3 ^ 3968);
                            yield.invoke(i5 < 128 ? 65533 : Integer.valueOf(i5));
                            Unit unit = Unit.INSTANCE;
                        }
                        i += i3;
                    }
                    yield.invoke(65533);
                    Unit unit2 = Unit.INSTANCE;
                    i3 = 1;
                    i += i3;
                } else if ((b >> 4) == -2) {
                    int i6 = i + 2;
                    if (i2 <= i6) {
                        yield.invoke(65533);
                        Unit unit3 = Unit.INSTANCE;
                        int i7 = i + 1;
                        if (i2 <= i7 || (bArr[i7] & 192) != 128) {
                            i3 = 1;
                        }
                    } else {
                        byte b4 = bArr[i + 1];
                        if ((b4 & 192) == 128) {
                            byte b5 = bArr[i6];
                            if ((b5 & 192) == 128) {
                                int i8 = (b << Ascii.FF) ^ ((b5 ^ (-123008)) ^ (b4 << 6));
                                yield.invoke((i8 >= 2048 && (55296 > i8 || i8 >= 57344)) ? Integer.valueOf(i8) : 65533);
                                Unit unit4 = Unit.INSTANCE;
                                i3 = 3;
                            } else {
                                yield.invoke(65533);
                                Unit unit5 = Unit.INSTANCE;
                            }
                        } else {
                            yield.invoke(65533);
                            Unit unit6 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else if ((b >> 3) == -2) {
                    int i9 = i + 3;
                    if (i2 <= i9) {
                        yield.invoke(65533);
                        Unit unit7 = Unit.INSTANCE;
                        int i10 = i + 1;
                        if (i2 <= i10 || (bArr[i10] & 192) != 128) {
                            i3 = 1;
                        } else {
                            int i11 = i + 2;
                            if (i2 > i11 && (bArr[i11] & 192) == 128) {
                                i3 = 3;
                            }
                        }
                    } else {
                        byte b6 = bArr[i + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i + 2];
                            if ((b7 & 192) == 128) {
                                byte b8 = bArr[i9];
                                if ((b8 & 192) == 128) {
                                    int i12 = (b << Ascii.DC2) ^ (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << Ascii.FF));
                                    yield.invoke((i12 <= 1114111 && (55296 > i12 || i12 >= 57344) && i12 >= 65536) ? Integer.valueOf(i12) : 65533);
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = 4;
                                } else {
                                    yield.invoke(65533);
                                    Unit unit9 = Unit.INSTANCE;
                                    i3 = 3;
                                }
                            } else {
                                yield.invoke(65533);
                                Unit unit10 = Unit.INSTANCE;
                            }
                        } else {
                            yield.invoke(65533);
                            Unit unit11 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else {
                    yield.invoke(65533);
                    i++;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0044  */
    public static final void processUtf16Chars(byte[] bArr, int i, int i2, Function1<? super Character, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        while (i < i2) {
            byte b = bArr[i];
            if (b >= 0) {
                yield.invoke(Character.valueOf((char) b));
                i++;
                while (i < i2) {
                    byte b2 = bArr[i];
                    if (b2 < 0) {
                        break;
                    }
                    i++;
                    yield.invoke(Character.valueOf((char) b2));
                }
            } else {
                int i3 = 2;
                if ((b >> 5) == -2) {
                    int i4 = i + 1;
                    if (i2 > i4) {
                        byte b3 = bArr[i4];
                        if ((b3 & 192) == 128) {
                            int i5 = (b << 6) ^ (b3 ^ 3968);
                            yield.invoke(i5 < 128 ? (char) 65533 : Character.valueOf((char) i5));
                            Unit unit = Unit.INSTANCE;
                        }
                        i += i3;
                    }
                    yield.invoke((char) 65533);
                    Unit unit2 = Unit.INSTANCE;
                    i3 = 1;
                    i += i3;
                } else if ((b >> 4) == -2) {
                    int i6 = i + 2;
                    if (i2 <= i6) {
                        yield.invoke((char) 65533);
                        Unit unit3 = Unit.INSTANCE;
                        int i7 = i + 1;
                        if (i2 <= i7 || (bArr[i7] & 192) != 128) {
                            i3 = 1;
                        }
                    } else {
                        byte b4 = bArr[i + 1];
                        if ((b4 & 192) == 128) {
                            byte b5 = bArr[i6];
                            if ((b5 & 192) == 128) {
                                int i8 = (b << Ascii.FF) ^ ((b5 ^ (-123008)) ^ (b4 << 6));
                                yield.invoke((i8 >= 2048 && (55296 > i8 || i8 >= 57344)) ? Character.valueOf((char) i8) : (char) 65533);
                                Unit unit4 = Unit.INSTANCE;
                                i3 = 3;
                            } else {
                                yield.invoke((char) 65533);
                                Unit unit5 = Unit.INSTANCE;
                            }
                        } else {
                            yield.invoke((char) 65533);
                            Unit unit6 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else if ((b >> 3) == -2) {
                    int i9 = i + 3;
                    if (i2 <= i9) {
                        yield.invoke((char) 65533);
                        Unit unit7 = Unit.INSTANCE;
                        int i10 = i + 1;
                        if (i2 <= i10 || (bArr[i10] & 192) != 128) {
                            i3 = 1;
                        } else {
                            int i11 = i + 2;
                            if (i2 > i11 && (bArr[i11] & 192) == 128) {
                                i3 = 3;
                            }
                        }
                    } else {
                        byte b6 = bArr[i + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i + 2];
                            if ((b7 & 192) == 128) {
                                byte b8 = bArr[i9];
                                if ((b8 & 192) == 128) {
                                    int i12 = (b << Ascii.DC2) ^ (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << Ascii.FF));
                                    if (i12 <= 1114111 && ((55296 > i12 || i12 >= 57344) && i12 >= 65536 && i12 != 65533)) {
                                        yield.invoke(Character.valueOf((char) ((i12 >>> 10) + 55232)));
                                        yield.invoke(Character.valueOf((char) ((i12 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320)));
                                    } else {
                                        yield.invoke((char) 65533);
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                    i3 = 4;
                                } else {
                                    yield.invoke((char) 65533);
                                    Unit unit9 = Unit.INSTANCE;
                                    i3 = 3;
                                }
                            } else {
                                yield.invoke((char) 65533);
                                Unit unit10 = Unit.INSTANCE;
                            }
                        } else {
                            yield.invoke((char) 65533);
                            Unit unit11 = Unit.INSTANCE;
                            i3 = 1;
                        }
                    }
                    i += i3;
                } else {
                    yield.invoke((char) 65533);
                    i++;
                }
            }
        }
    }

    public static final int process2Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i3 = i + 1;
        if (i2 <= i3) {
            yield.invoke(65533);
            return 1;
        }
        byte b = bArr[i];
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            yield.invoke(65533);
            return 1;
        }
        int i4 = (b2 ^ 3968) ^ (b << 6);
        if (i4 < 128) {
            yield.invoke(65533);
            return 2;
        }
        yield.invoke(Integer.valueOf(i4));
        return 2;
    }

    public static final int process3Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i3 = i + 2;
        if (i2 <= i3) {
            yield.invoke(65533);
            int i4 = i + 1;
            return (i2 <= i4 || (bArr[i4] & 192) != 128) ? 1 : 2;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if ((b2 & 192) != 128) {
            yield.invoke(65533);
            return 1;
        }
        byte b3 = bArr[i3];
        if ((b3 & 192) != 128) {
            yield.invoke(65533);
            return 2;
        }
        int i5 = ((b3 ^ (-123008)) ^ (b2 << 6)) ^ (b << Ascii.FF);
        if (i5 < 2048) {
            yield.invoke(65533);
            return 3;
        }
        if (55296 <= i5 && i5 < 57344) {
            yield.invoke(65533);
            return 3;
        }
        yield.invoke(Integer.valueOf(i5));
        return 3;
    }

    public static final int process4Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i3 = i + 3;
        if (i2 <= i3) {
            yield.invoke(65533);
            int i4 = i + 1;
            if (i2 <= i4 || (bArr[i4] & 192) != 128) {
                return 1;
            }
            int i5 = i + 2;
            return (i2 <= i5 || (bArr[i5] & 192) != 128) ? 2 : 3;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if ((b2 & 192) != 128) {
            yield.invoke(65533);
            return 1;
        }
        byte b3 = bArr[i + 2];
        if ((b3 & 192) != 128) {
            yield.invoke(65533);
            return 2;
        }
        byte b4 = bArr[i3];
        if ((b4 & 192) != 128) {
            yield.invoke(65533);
            return 3;
        }
        int i6 = (((b4 ^ 3678080) ^ (b3 << 6)) ^ (b2 << Ascii.FF)) ^ (b << Ascii.DC2);
        if (i6 > 1114111) {
            yield.invoke(65533);
            return 4;
        }
        if (55296 <= i6 && i6 < 57344) {
            yield.invoke(65533);
            return 4;
        }
        if (i6 < 65536) {
            yield.invoke(65533);
            return 4;
        }
        yield.invoke(Integer.valueOf(i6));
        return 4;
    }
}
