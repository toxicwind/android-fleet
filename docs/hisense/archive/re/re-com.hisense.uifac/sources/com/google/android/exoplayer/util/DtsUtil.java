package com.google.android.exoplayer.util;

import com.google.android.exoplayer.MediaFormat;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class DtsUtil {
    private static final int[] CHANNELS_BY_AMODE = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] SAMPLE_RATE_BY_SFREQ = {-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE = {64, 112, 128, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
    private static final ParsableBitArray SCRATCH_BITS = new ParsableBitArray();

    private enum BitstreamType {
        rawBigEndian,
        rawLittleEndian,
        _14BitsBigEndian,
        _14BitsLittleEndian
    }

    public static MediaFormat parseDtsFormat(byte[] bArr, String str, long j, String str2) {
        ParsableBitArray parsableBitArray = SCRATCH_BITS;
        parsableBitArray.reset(bArr);
        parsableBitArray.skipBits(60);
        int i = CHANNELS_BY_AMODE[parsableBitArray.readBits(6)];
        int i2 = SAMPLE_RATE_BY_SFREQ[parsableBitArray.readBits(4)];
        int bits = parsableBitArray.readBits(5);
        int[] iArr = TWICE_BITRATE_KBPS_BY_RATE;
        int i3 = bits >= iArr.length ? -1 : (iArr[bits] * 1000) / 2;
        parsableBitArray.skipBits(10);
        return MediaFormat.createAudioFormat(str, "audio/vnd.dts", i3, -1, j, i + (parsableBitArray.readBits(2) > 0 ? 1 : 0), i2, null, str2);
    }

    public static int parseDtsAudioSampleCount(byte[] bArr) {
        return ((((bArr[5] & 252) >> 2) | ((bArr[4] & 1) << 6)) + 1) * 32;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int iPosition = byteBuffer.position();
        BitstreamType bitstreamType = BitstreamType.rawBigEndian;
        while (true) {
            i = iPosition + 4;
            if (i < byteBuffer.limit()) {
                if (byteBuffer.get(iPosition) == 127 && (byteBuffer.get(iPosition + 1) & 255) == 254 && (byteBuffer.get(iPosition + 2) & 255) == 128 && byteBuffer.get(iPosition + 3) == 1) {
                    bitstreamType = BitstreamType.rawBigEndian;
                    break;
                }
                if ((byteBuffer.get(iPosition) & 255) == 255 && byteBuffer.get(iPosition + 1) == 31 && byteBuffer.get(iPosition + 2) == 0 && (byteBuffer.get(iPosition + 3) & 255) == 232) {
                    bitstreamType = BitstreamType._14BitsLittleEndian;
                    break;
                }
                if ((byteBuffer.get(iPosition) & 255) == 254 && byteBuffer.get(iPosition + 1) == 127 && byteBuffer.get(iPosition + 2) == 1 && (byteBuffer.get(iPosition + 3) & 255) == 128) {
                    bitstreamType = BitstreamType.rawLittleEndian;
                    break;
                }
                iPosition++;
            } else {
                break;
            }
        }
        int i3 = 0;
        if (bitstreamType == BitstreamType._14BitsLittleEndian) {
            int[] iArr = {0, 0};
            int i4 = 32;
            while (iPosition < byteBuffer.limit()) {
                int i5 = iPosition + 1;
                int i6 = i5 + 1;
                int i7 = ((byteBuffer.get(iPosition) & 255) | ((byteBuffer.get(i5) & 255) << 8)) & 16383;
                if (i4 < 14) {
                    int i8 = 14 - i4;
                    iArr[i3] = (iArr[i3] << i4) | (i7 >>> i8);
                    i3++;
                    if (i3 >= 2) {
                        break;
                    }
                    iArr[i3] = i7 & (16383 >> i4);
                    i4 = 32 - i8;
                } else {
                    iArr[i3] = i7 | (iArr[i3] << 14);
                    i4 -= 14;
                }
                iPosition = i6;
            }
            i2 = (iArr[1] & 33292288) >> 18;
        } else if (bitstreamType == BitstreamType.rawLittleEndian) {
            i2 = ((byteBuffer.get(i) & 252) >> 2) | ((byteBuffer.get(iPosition + 5) & 1) << 6);
        } else {
            int i9 = iPosition + 5;
            if (i9 >= byteBuffer.limit()) {
                return 0;
            }
            i2 = ((byteBuffer.get(i9) & 252) >> 2) | ((byteBuffer.get(i) & 1) << 6);
        }
        return (i2 + 1) * 32;
    }

    public static int parseDtsExtensionAudioSampleCount(ByteBuffer byteBuffer, int i) {
        int iPosition = byteBuffer.position() + 4 + 1;
        int i2 = (byteBuffer.get(iPosition) & 32) == 0 ? iPosition + 3 : iPosition + 4;
        if ((byteBuffer.get(i2) & 16) <= 0) {
            return 0;
        }
        int i3 = (byteBuffer.get(i2) & 12) >> 2;
        int i4 = (((byteBuffer.get(i2 + 1) & ByteCompanionObject.MIN_VALUE) >> 7) | ((byteBuffer.get(i2) & 3) << 1)) + 1;
        if (i3 == 0) {
            return ((i4 * 512) * i) / 32000;
        }
        if (i3 == 1) {
            return ((i4 * 512) * i) / 44100;
        }
        if (i3 != 2) {
            return 0;
        }
        return ((i4 * 512) * i) / 48000;
    }

    public static int getDtsFrameSize(byte[] bArr) {
        return (((bArr[7] & 240) >> 4) | ((bArr[5] & 2) << 12) | ((bArr[6] & 255) << 4)) + 1;
    }

    public static MediaFormat parseDtsFormatSubStream(byte[] bArr, String str, long j, String str2) {
        return MediaFormat.createAudioFormat(str, "audio/vnd.dts", -1, -1, j, 8, 48000, null, str2);
    }

    public static int getDtsFrameSizeSubStream(byte[] bArr) {
        int i;
        int i2;
        ParsableBitArray parsableBitArray = SCRATCH_BITS;
        parsableBitArray.reset(bArr);
        parsableBitArray.skipBits(42);
        if (parsableBitArray.readBits(1) == 0) {
            i = 8;
            i2 = 16;
        } else {
            i = 12;
            i2 = 20;
        }
        parsableBitArray.readBits(i);
        return parsableBitArray.readBits(i2) + 1;
    }
}
