package com.google.android.exoplayer.util;

import android.util.Pair;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class CodecSpecificDataUtil {
    private static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static byte[] buildAacAudioSpecificConfig(int i, int i2, int i3) {
        return new byte[]{(byte) (((i << 3) & 248) | ((i2 >> 1) & 7)), (byte) (((i2 << 7) & 128) | ((i3 << 3) & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE))};
    }

    public static Pair<Integer, Integer> parseAacAudioSpecificConfig(byte[] bArr) {
        int bits;
        int bits2;
        int[] iArr = AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        int bits3 = parsableBitArray.readBits(5);
        int bits4 = parsableBitArray.readBits(4);
        if (bits4 == 15) {
            bits = parsableBitArray.readBits(24);
        } else {
            Assertions.checkArgument(bits4 < 13);
            bits = iArr[bits4];
        }
        int bits5 = parsableBitArray.readBits(4);
        if (bits3 == 5 || bits3 == 29) {
            int bits6 = parsableBitArray.readBits(4);
            if (bits6 == 15) {
                bits2 = parsableBitArray.readBits(24);
            } else {
                Assertions.checkArgument(bits6 < 13);
                bits2 = iArr[bits6];
            }
            bits = bits2;
            if (parsableBitArray.readBits(5) == 22) {
                bits5 = parsableBitArray.readBits(4);
            }
        }
        int i = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[bits5];
        Assertions.checkArgument(i != -1);
        return Pair.create(Integer.valueOf(bits), Integer.valueOf(i));
    }

    public static byte[] buildAacAudioSpecificConfig(int i, int i2) {
        int i3 = -1;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            int[] iArr = AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE;
            if (i5 >= iArr.length) {
                break;
            }
            if (i == iArr[i5]) {
                i4 = i5;
            }
            i5++;
        }
        int i6 = 0;
        while (true) {
            int[] iArr2 = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE;
            if (i6 >= iArr2.length) {
                return new byte[]{(byte) ((i4 >> 1) | 16), (byte) ((i3 << 3) | ((i4 & 1) << 7))};
            }
            if (i2 == iArr2[i6]) {
                i3 = i6;
            }
            i6++;
        }
    }

    public static byte[] buildNalUnit(byte[] bArr, int i, int i2) {
        byte[] bArr2 = NAL_START_CODE;
        byte[] bArr3 = new byte[bArr2.length + i2];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i, bArr3, bArr2.length, i2);
        return bArr3;
    }

    public static byte[][] splitNalUnits(byte[] bArr) {
        if (!isNalStartCode(bArr, 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iFindNalStartCode = 0;
        do {
            arrayList.add(Integer.valueOf(iFindNalStartCode));
            iFindNalStartCode = findNalStartCode(bArr, iFindNalStartCode + NAL_START_CODE.length);
        } while (iFindNalStartCode != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i = 0;
        while (i < arrayList.size()) {
            int iIntValue = ((Integer) arrayList.get(i)).intValue();
            int iIntValue2 = (i < arrayList.size() + (-1) ? ((Integer) arrayList.get(i + 1)).intValue() : bArr.length) - iIntValue;
            byte[] bArr3 = new byte[iIntValue2];
            System.arraycopy(bArr, iIntValue, bArr3, 0, iIntValue2);
            bArr2[i] = bArr3;
            i++;
        }
        return bArr2;
    }

    private static int findNalStartCode(byte[] bArr, int i) {
        int length = bArr.length - NAL_START_CODE.length;
        while (i <= length) {
            if (isNalStartCode(bArr, i)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static boolean isNalStartCode(byte[] bArr, int i) {
        byte[] bArr2 = NAL_START_CODE;
        if (bArr.length - i <= bArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i + i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }
}
