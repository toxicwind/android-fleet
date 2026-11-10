package com.google.android.exoplayer.util;

import android.util.Log;
import com.mediatek.twoworlds.tv.MtkTvScanCeBase;

/* JADX INFO: loaded from: classes.dex */
public class Ac4Util {
    private static final int[] SAMPLE_COUNT_BY_FRAME_RATE_INDEX_RESAMPLE = {2002, 2000, 1920, 1602, 1600, MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG, 1000, 960, 800, 800, 480, 400, 400, 2048};
    private static int ac4FramerateIndex = -1;

    public static int getAc4SyncframeAudioSampleCount(byte[] bArr) {
        int[] iArr = SAMPLE_COUNT_BY_FRAME_RATE_INDEX_RESAMPLE;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.skipBits(16);
        if (parsableBitArray.readBits(16) == 65535) {
            parsableBitArray.skipBits(24);
        }
        if (parsableBitArray.readBits(2) == 3) {
            do {
                parsableBitArray.skipBits(2);
            } while (parsableBitArray.readBits(1) == 1);
        }
        parsableBitArray.skipBits(10);
        if ((parsableBitArray.readBits(1) == 1) && parsableBitArray.readBits(3) > 0) {
            parsableBitArray.skipBits(2);
        }
        parsableBitArray.skipBits(1);
        int bits = parsableBitArray.readBits(4);
        ac4FramerateIndex = bits;
        if (bits > iArr.length - 1) {
            Log.e("Ac4Util", "ERROR! invalid frameRateIndex (" + bits + ")");
            return 0;
        }
        Log.i("Ac4Util", "AC4 parse frameRateIndex=" + bits);
        return iArr[bits];
    }

    public static int getAc4FramerateIndex(byte[] bArr) {
        if (ac4FramerateIndex != -1) {
            getAc4SyncframeAudioSampleCount(bArr);
        }
        return ac4FramerateIndex;
    }
}
