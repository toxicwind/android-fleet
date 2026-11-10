package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class JsonStringEncoder {
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private char[] _qbuf() {
        return new char[]{'\\', 0, '0', '0', 0, 0};
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    public char[] quoteAsString(String str) {
        int i_appendNamed;
        char[] cArrFinishCurrentSegment = new char[MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE];
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = str.length();
        TextBuffer textBufferFromInitial = null;
        char[] cArr_qbuf = null;
        int i = 0;
        int i2 = 0;
        loop0: while (i < length2) {
            while (true) {
                char cCharAt = str.charAt(i);
                if (cCharAt >= length || iArr[cCharAt] == 0) {
                    if (i2 >= cArrFinishCurrentSegment.length) {
                        if (textBufferFromInitial == null) {
                            textBufferFromInitial = TextBuffer.fromInitial(cArrFinishCurrentSegment);
                        }
                        cArrFinishCurrentSegment = textBufferFromInitial.finishCurrentSegment();
                        i2 = 0;
                    }
                    int i3 = i2 + 1;
                    cArrFinishCurrentSegment[i2] = cCharAt;
                    i++;
                    if (i >= length2) {
                        i2 = i3;
                        break loop0;
                    }
                    i2 = i3;
                }
            }
            if (cArr_qbuf == null) {
                cArr_qbuf = _qbuf();
            }
            int i4 = i + 1;
            char cCharAt2 = str.charAt(i);
            int i5 = iArr[cCharAt2];
            if (i5 < 0) {
                i_appendNamed = _appendNumeric(cCharAt2, cArr_qbuf);
            } else {
                i_appendNamed = _appendNamed(i5, cArr_qbuf);
            }
            int i6 = i2 + i_appendNamed;
            if (i6 > cArrFinishCurrentSegment.length) {
                int length3 = cArrFinishCurrentSegment.length - i2;
                if (length3 > 0) {
                    System.arraycopy(cArr_qbuf, 0, cArrFinishCurrentSegment, i2, length3);
                }
                if (textBufferFromInitial == null) {
                    textBufferFromInitial = TextBuffer.fromInitial(cArrFinishCurrentSegment);
                }
                cArrFinishCurrentSegment = textBufferFromInitial.finishCurrentSegment();
                int i7 = i_appendNamed - length3;
                System.arraycopy(cArr_qbuf, length3, cArrFinishCurrentSegment, 0, i7);
                i2 = i7;
            } else {
                System.arraycopy(cArr_qbuf, 0, cArrFinishCurrentSegment, i2, i_appendNamed);
                i2 = i6;
            }
            i = i4;
        }
        if (textBufferFromInitial == null) {
            return Arrays.copyOfRange(cArrFinishCurrentSegment, 0, i2);
        }
        textBufferFromInitial.setCurrentLength(i2);
        return textBufferFromInitial.contentsAsArray();
    }

    /* JADX WARN: Code duplicated, block: B:60:0x00fd  */
    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        int i3;
        int length = str.length();
        byte[] currentSegment = new byte[MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR];
        ByteArrayBuilder byteArrayBuilderFromInitial = null;
        int i4 = 0;
        int i_appendByte = 0;
        loop0: while (i4 < length) {
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char cCharAt = str.charAt(i4);
                if (cCharAt > 127 || iArr[cCharAt] != 0) {
                    break;
                }
                if (i_appendByte >= currentSegment.length) {
                    if (byteArrayBuilderFromInitial == null) {
                        byteArrayBuilderFromInitial = ByteArrayBuilder.fromInitial(currentSegment, i_appendByte);
                    }
                    currentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                    i_appendByte = 0;
                }
                int i5 = i_appendByte + 1;
                currentSegment[i_appendByte] = (byte) cCharAt;
                i4++;
                if (i4 >= length) {
                    i_appendByte = i5;
                    break loop0;
                }
                i_appendByte = i5;
            }
            if (byteArrayBuilderFromInitial == null) {
                byteArrayBuilderFromInitial = ByteArrayBuilder.fromInitial(currentSegment, i_appendByte);
            }
            if (i_appendByte >= currentSegment.length) {
                currentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                i_appendByte = 0;
            }
            int i6 = i4 + 1;
            char cCharAt2 = str.charAt(i4);
            if (cCharAt2 <= 127) {
                i_appendByte = _appendByte(cCharAt2, iArr[cCharAt2], byteArrayBuilderFromInitial, i_appendByte);
                currentSegment = byteArrayBuilderFromInitial.getCurrentSegment();
                i4 = i6;
            } else {
                if (cCharAt2 <= 2047) {
                    i2 = i_appendByte + 1;
                    currentSegment[i_appendByte] = (byte) ((cCharAt2 >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
                    i = (cCharAt2 & '?') | 128;
                } else {
                    if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                        int i7 = i_appendByte + 1;
                        currentSegment[i_appendByte] = (byte) ((cCharAt2 >> '\f') | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN);
                        if (i7 >= currentSegment.length) {
                            currentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                            i7 = 0;
                        }
                        currentSegment[i7] = (byte) (((cCharAt2 >> 6) & 63) | 128);
                        i = (cCharAt2 & '?') | 128;
                        i2 = i7 + 1;
                    } else {
                        if (cCharAt2 > 56319) {
                            _illegal(cCharAt2);
                            throw null;
                        }
                        if (i6 >= length) {
                            _illegal(cCharAt2);
                            throw null;
                        }
                        int i8 = i6 + 1;
                        int i_convert = _convert(cCharAt2, str.charAt(i6));
                        if (i_convert > 1114111) {
                            _illegal(i_convert);
                            throw null;
                        }
                        int i9 = i_appendByte + 1;
                        currentSegment[i_appendByte] = (byte) ((i_convert >> 18) | 240);
                        if (i9 >= currentSegment.length) {
                            currentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                            i9 = 0;
                        }
                        int i10 = i9 + 1;
                        currentSegment[i9] = (byte) (((i_convert >> 12) & 63) | 128);
                        if (i10 >= currentSegment.length) {
                            currentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                            i10 = 0;
                        }
                        int i11 = i10 + 1;
                        currentSegment[i10] = (byte) (((i_convert >> 6) & 63) | 128);
                        i3 = (i_convert & 63) | 128;
                        i4 = i8;
                        i2 = i11;
                    }
                    if (i2 >= currentSegment.length) {
                        currentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                        i2 = 0;
                    }
                    currentSegment[i2] = (byte) i3;
                    i_appendByte = i2 + 1;
                }
                i3 = i;
                i4 = i6;
                if (i2 >= currentSegment.length) {
                    currentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                    i2 = 0;
                }
                currentSegment[i2] = (byte) i3;
                i_appendByte = i2 + 1;
            }
        }
        if (byteArrayBuilderFromInitial == null) {
            return Arrays.copyOfRange(currentSegment, 0, i_appendByte);
        }
        return byteArrayBuilderFromInitial.completeAndCoalesce(i_appendByte);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ea A[SYNTHETIC] */
    public byte[] encodeAsUTF8(String str) {
        int i;
        int i2;
        int length = str.length();
        int length2 = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR;
        byte[] bArrFinishCurrentSegment = new byte[MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR];
        ByteArrayBuilder byteArrayBuilderFromInitial = null;
        int i3 = 0;
        int i4 = 0;
        loop0: while (i3 < length) {
            int i5 = i3 + 1;
            char cCharAt = str.charAt(i3);
            while (cCharAt <= 127) {
                if (i4 >= length2) {
                    if (byteArrayBuilderFromInitial == null) {
                        byteArrayBuilderFromInitial = ByteArrayBuilder.fromInitial(bArrFinishCurrentSegment, i4);
                    }
                    byte[] bArrFinishCurrentSegment2 = byteArrayBuilderFromInitial.finishCurrentSegment();
                    i4 = 0;
                    bArrFinishCurrentSegment = bArrFinishCurrentSegment2;
                    length2 = bArrFinishCurrentSegment2.length;
                }
                int i6 = i4 + 1;
                bArrFinishCurrentSegment[i4] = (byte) cCharAt;
                if (i5 >= length) {
                    i4 = i6;
                    break loop0;
                }
                char cCharAt2 = str.charAt(i5);
                i5++;
                cCharAt = cCharAt2;
                i4 = i6;
            }
            if (byteArrayBuilderFromInitial == null) {
                byteArrayBuilderFromInitial = ByteArrayBuilder.fromInitial(bArrFinishCurrentSegment, i4);
            }
            if (i4 >= length2) {
                bArrFinishCurrentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                length2 = bArrFinishCurrentSegment.length;
                i4 = 0;
            }
            if (cCharAt < 2048) {
                i = i4 + 1;
                bArrFinishCurrentSegment[i4] = (byte) ((cCharAt >> 6) | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED);
            } else {
                if (cCharAt < 55296 || cCharAt > 57343) {
                    int i7 = i4 + 1;
                    bArrFinishCurrentSegment[i4] = (byte) ((cCharAt >> '\f') | MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN);
                    if (i7 >= length2) {
                        bArrFinishCurrentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                        length2 = bArrFinishCurrentSegment.length;
                        i7 = 0;
                    }
                    bArrFinishCurrentSegment[i7] = (byte) (((cCharAt >> 6) & 63) | 128);
                    i = i7 + 1;
                } else {
                    if (cCharAt > 56319) {
                        _illegal(cCharAt);
                        throw null;
                    }
                    if (i5 >= length) {
                        _illegal(cCharAt);
                        throw null;
                    }
                    int i8 = i5 + 1;
                    int i_convert = _convert(cCharAt, str.charAt(i5));
                    if (i_convert > 1114111) {
                        _illegal(i_convert);
                        throw null;
                    }
                    int i9 = i4 + 1;
                    bArrFinishCurrentSegment[i4] = (byte) ((i_convert >> 18) | 240);
                    if (i9 >= length2) {
                        bArrFinishCurrentSegment = byteArrayBuilderFromInitial.finishCurrentSegment();
                        length2 = bArrFinishCurrentSegment.length;
                        i9 = 0;
                    }
                    int i10 = i9 + 1;
                    bArrFinishCurrentSegment[i9] = (byte) (((i_convert >> 12) & 63) | 128);
                    if (i10 >= length2) {
                        byte[] bArrFinishCurrentSegment3 = byteArrayBuilderFromInitial.finishCurrentSegment();
                        i10 = 0;
                        bArrFinishCurrentSegment = bArrFinishCurrentSegment3;
                        length2 = bArrFinishCurrentSegment3.length;
                    }
                    int i11 = i10 + 1;
                    bArrFinishCurrentSegment[i10] = (byte) (((i_convert >> 6) & 63) | 128);
                    i2 = i_convert;
                    i3 = i8;
                    i = i11;
                }
                if (i >= length2) {
                    byte[] bArrFinishCurrentSegment4 = byteArrayBuilderFromInitial.finishCurrentSegment();
                    i = 0;
                    bArrFinishCurrentSegment = bArrFinishCurrentSegment4;
                    length2 = bArrFinishCurrentSegment4.length;
                }
                bArrFinishCurrentSegment[i] = (byte) ((i2 & 63) | 128);
                i4 = i + 1;
            }
            i2 = cCharAt;
            i3 = i5;
            if (i >= length2) {
                byte[] bArrFinishCurrentSegment5 = byteArrayBuilderFromInitial.finishCurrentSegment();
                i = 0;
                bArrFinishCurrentSegment = bArrFinishCurrentSegment5;
                length2 = bArrFinishCurrentSegment5.length;
            }
            bArrFinishCurrentSegment[i] = (byte) ((i2 & 63) | 128);
            i4 = i + 1;
        }
        if (byteArrayBuilderFromInitial == null) {
            return Arrays.copyOfRange(bArrFinishCurrentSegment, 0, i4);
        }
        return byteArrayBuilderFromInitial.completeAndCoalesce(i4);
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HB[i4 >> 4]);
                byteArrayBuilder.append(HB[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HB[i >> 4]);
            byteArrayBuilder.append(HB[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private static int _convert(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }
}
